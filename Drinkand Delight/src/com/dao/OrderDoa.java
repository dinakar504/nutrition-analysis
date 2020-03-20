package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import com.entity.RawMaterialOrder;

public class OrderDoa implements OrderDaoI{
	Connection con=null;
	int orderid;
	@Override
	public void  placeOrder(RawMaterialOrder obean) throws Exception
{
	con=ClassDb.getConnection1();
	String q1="select price_p_u,expirydate,manudate from rawmaterialspecs where rmname=?";
	PreparedStatement p=con.prepareStatement(q1);
	p.setString(1, obean.getName());
	ResultSet r1=p.executeQuery();
	r1.next();
	obean.setTotalprice(r1.getInt(1)*obean.getQuanvalue());
	//System.out.println(r1.getInt(1));
System.out.println("hello");
	/*order*/
	String str="insert into rawmaterialorder values(orderid_1.nextval,?,?,?,?,?,?,?,?,?,?,?)";
	//System.out.println("hello");
	PreparedStatement p1=con.prepareStatement(str);
	p1.setString(1, obean.getName());
	p1.setInt(2, obean.getSupplierid());
	p1.setInt(3,obean.getQuanvalue());
	p1.setString(4, obean.getQuanunit());
	Date dateofOrder=obean.getDateoford();
	java.sql.Date dateOfOrder1=new java.sql.Date(dateofOrder.getTime());
	p1.setDate(5,dateOfOrder1);
	//aa
	Calendar cal = Calendar.getInstance();
	cal.setTime(dateofOrder);
	cal.add(Calendar.DATE, 4);
	Date modifiedDate = cal.getTime();
	
	//Date dateofdel=obean.getDateofdel();
	java.sql.Date dateOfdel=new java.sql.Date(modifiedDate.getTime());
	p1.setDate(6,dateOfdel);
	p1.setInt(7, r1.getInt(1));
	p1.setInt(8, obean.getTotalprice());
	p1.setString(9, "notdisp");
	p1.setInt(10,obean.getWarehouseid());
	p1.setInt(11, obean.getUserid());
	int a1=p1.executeUpdate();
	//System.out.println(a1);
	
    /*curval*/
	String q2="select orderid_1.currval from  dual";
	PreparedStatement p2=con.prepareStatement(q2);
    ResultSet r2=p2.executeQuery();
    r2.next();
   
    orderid=r2.getInt(1);
    System.out.println(orderid);
    
    /* rawmaterialstock inersting*/
    if(a1>0)
    {
    	String q3="insert into rawstock values(?,?,?,?,?,?,?,?)";
        PreparedStatement p3=con.prepareStatement(q3);
        p3.setInt(1, orderid);
        p3.setString(2, obean.getName());
        p3.setInt(3, obean.getQuanvalue());
        p3.setInt(4, obean.getTotalprice());
        p3.setInt(5,obean.getWarehouseid());
        p3.setDate(6, dateOfdel);
        p3.setDate(7,r1.getDate(3) );
        p3.setDate(8,r1.getDate(2));       
        p3.executeQuery();
    }
}
	@Override
public	void displayorders(int id) throws Exception
	{
	    con=ClassDb.getConnection1();
	    String  str="select orderid,name,quanvalue,totalprice,dateoford,dateofdel,supplierid,warehouseid from rawmaterialorder where userid=?";
		PreparedStatement p1=con.prepareStatement(str);
		p1.setInt(1, id);
		ResultSet s=p1.executeQuery();
		System.out.println("orderid\t" +" "+"name\tquanvalue\ttotal\tdateoford\tdateofdel\tsupplierid\twarehouseid");
		while(s.next())
		{
			System.out.print(s.getInt(1)+"\t");
			System.out.print(s.getString(2)+"\t");
			System.out.print(s.getInt(3)+"\t");
			System.out.print(s.getInt(4)+"\t");
			System.out.print(s.getDate(5)+"\t");
			System.out.print(s.getDate(6)+"\t");
			System.out.print(s.getInt(7)+"\t"+"   ");
			System.out.println("\t"+s.getInt(8)+"\t");
			
			
		}
	}
	@Override
public void trackorder(int uid,int orderid) throws OrderNotExists, ClassNotFoundException, SQLException
{
	con=ClassDb.getConnection1();
	String str="select dateoford,dateofdel from rawmaterialorder where userid=? and orderid=?";
	PreparedStatement p=con.prepareStatement(str);
	p.setInt(1, uid);
	p.setInt(2, orderid);
	ResultSet result =p.executeQuery();
	if(result.next())
	{
		System.out.println("Date of Order: "+result.getDate(1)+"   "+"Date of Deliver:  "+result.getDate(2));
	}
	else 
		throw new OrderNotExists("Orders  does not exits");
	
}

@Override
public boolean doesRawMaterialOrderExists( int orderid) throws OrderNotExists 
{
	con=ClassDb.getConnection1();
	String str="select dateoford,dateofdel from rawmaterialorder where orderid=?";
    PreparedStatement p;

		try {
			p = con.prepareStatement(str);
			 p.setInt(1, orderid);
			    ResultSet r=p.executeQuery();
			    if(r.next())
				   return true;
			    else 
			    	throw new OrderNotExists("order does not exists ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return false;
   
}
@Override
public boolean updateorder(int oid,int quanvalue) throws ClassNotFoundException, SQLException, UpdateException 
{
	con=ClassDb.getConnection1();
	//for gettingh the rawmaterial name
	String str3="select name from  rawmaterialorder where orderid=?";
	PreparedStatement p3=con.prepareStatement(str3);
	p3.setInt(1, oid);
	try
	{
		ResultSet re=p3.executeQuery();
		re.next();
		if(re.getString(1) != null)
		{
			//for getting raw matrerial price per unit
			String q1="select price_p_u from rawmaterialspecs where rmname=?";
			PreparedStatement p=con.prepareStatement(q1);
			p.setString(1,re.getString(1));
			ResultSet r1=p.executeQuery();
			r1.next();
			
			String str="update rawmaterialorder set quanvalue=?,totalprice=? where orderid=?";
			PreparedStatement p1=con.prepareStatement(str);
			p1.setInt(1, quanvalue);
			p1.setInt(2, quanvalue*r1.getInt(1));
			p1.setInt(3, oid);
			int status=p1.executeUpdate();
			
			
		    String str1="update rawstock set quanval=?,price=? where orderid=?";
		    PreparedStatement p2=con.prepareStatement(str1);
		    p2.setInt(1, quanvalue);
		    p2.setInt(2, quanvalue*r1.getInt(1));
		    p2.setInt(3, oid);
		    int status1=p2.executeUpdate();

			if(status>0 && status1>0)
			{
			    return true;
			}
			else
				throw new UpdateException("update not successful");
			}
		else
		return false;
	}
	catch(Exception e)
	{
		System.out.println("Order Id does not exists");
	}
	
	return false;
	}
	
    @Override
    
    public void fetchSupplierIds(int sid) throws ClassNotFoundException, SQLException, InvalidUser 
    {
    	con=ClassDb.getConnection1();
    	String str="select * from supplier where supplierid=?";
    	PreparedStatement p=con.prepareStatement(str);
    	p.setInt(1, sid);
    	ResultSet result=p.executeQuery();
    	
    	if(result.next())
    	{System.out.println("Supplierid\tsupname\tsupadd\tphonenum");
    		System.out.print(result.getInt(1));
    		System.out.print(result.getString(2));
    	    System.out.print(result.getString(3));
    	    System.out.print(result.getInt(4));
    	}
    	else 
    		throw new InvalidUser("supplier does not exists");
    	
    }
    @Override
    public void fetchAllSupplierIds() throws Exception
    {
    	con=ClassDb.getConnection1();
    	String str="select * from supplier";
    	PreparedStatement p=con.prepareStatement(str);
    	ResultSet result=p.executeQuery();
    	
    	if(result.next())
    	{System.out.println("Supplierid\tsupname\tsupadd\tphonenum");
    		System.out.print("\t"+result.getInt(1));
    		System.out.print("\t"+result.getString(2));
    	    System.out.print("\t"+result.getString(3));
    	    System.out.println("\t"+result.getInt(4));
    	}
    	else 
    		throw new InvalidUser("there are no supplier that exists");
    	
    }
    @Override
    public void fetchAllWarehouseIds() throws Exception
    {
    	con=ClassDb.getConnection1();
    	String str="select * from warehouse";
    	PreparedStatement p=con.prepareStatement(str);
    	ResultSet result=p.executeQuery();
    	
    	if(result.next())
    	{System.out.println("warehouseid\twhousename\twareadd\tphonenum");
    		System.out.print("\t"+result.getInt(1));
    		System.out.print("\t"+result.getString(2));
    	    System.out.print("\t"+result.getString(3));
    	    System.out.println("\t"+result.getInt(4));
    	}
    	else 
    		throw new InvalidUser("there are no supplier that exists");	
    }
    @Override
    public void rawMaterialSpecs() throws Exception
    {
    	con=ClassDb.getConnection1();
    	String str="select * from rawmaterialspecs";
    	PreparedStatement p=con.prepareStatement(str);
    	ResultSet result =p.executeQuery();
    	System.out.println("Rmsid\tRMname\t\tprice_p_u\tdescription\texpdate\t\tmanudate");
    	while(result.next())
    	{
    		System.out.print(result.getInt(1));
    		System.out.print("\t"+result.getString(2));
    	    System.out.print("\t"+result.getInt(3));
    	    System.out.print("\t\t"+result.getString(4)+"\t");  
    	    System.out.print("\t"+result.getDate(5));
    	    System.out.println("\t"+result.getDate(6));
    	    }
    	
    }
    @Override
    public boolean validate(int uid,String pass) throws ClassNotFoundException, SQLException
    {
    	con=ClassDb.getConnection1();
    	String str="select * from user1 where userid=? and upass=?";
    	PreparedStatement p=con.prepareStatement(str);
    	p.setInt(1, uid);
    	p.setString(2, pass);
    	ResultSet result=p.executeQuery();
    	if(result.next())
    	{
    		return true;
    	}
    	else
		return false;
    	
    	
    }
    
}
