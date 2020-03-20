package com.ui;

import java.util.Date;
import java.util.Scanner;

import com.dao.InvalidUser;
import com.dao.OrderNotExists;
import com.dao.UpdateException;
import com.entity.RawMaterialOrder;
import com.service.OrderService;
import com.service.OrderServiceI;

public class MainUi {

	static RawMaterialOrder bean=new RawMaterialOrder();
	static OrderServiceI os=new OrderService();
	static Date d=new Date();
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		// TODO Auto-generated method stub
		
		
		try
		{
			System.out.println("         Welcome to Drink and Delight");
			System.out.println("-----------------------------------------------");
			System.out.println("User Login");
			System.out.println("enter the user id");
			int userid=sc.nextInt();
			System.out.println("enter password");
			String pass=sc.next();
			boolean status= os.validateUser(userid, pass);
			if(status)
			{
				System.out.println("Login succes\n");
				while(true)
				{
					System.out.println("1.place an order\n2.diplay orders\n3.track an order\n4.does order exits\n5.update an order\n6.supplier details\npress any to exit");
					System.out.println("enter your choice");
					int option=sc.nextInt(); 
					switch(option)
					{
						case 1://create an order
							bean.setUserid(userid);
							System.out.println("diplay");
							System.out.println("rawmaterials available");
							os.rawMaterialSpecs();
							System.out.println("enter item name");
							bean.setName(sc.next());
							System.out.println("select a supplier id of your choice");
							os.fetchAllSuplierDetails();
							System.out.println("\nenter the supplierid");
							bean.setSupplierid(sc.nextInt());
							System.out.println("select a warehouse if of your choice");
							os.fetchWareHouseIds();
							System.out.println("enter the warehosueid");
							bean.setWarehouseid((sc.nextInt()));
							System.out.println("enter the quanvalue");
							bean.setQuanvalue(sc.nextInt());
						    bean.setDateoford(d);	
						    os.placeOrder(bean);
						    break;
						case 2://display orders of user
							
							os.displayOrders(userid);
							break;
						case 3://track order
						//	System.out.println("orderid\t" +" "+"name\tquanvalue\tdateoford\tsupplierid\twarehouseid");
							//os.displayOrders(userid);
							System.out.println("enter the orderid you want to track");
							int oid=sc.nextInt();
							try
							{
								os.trackOrder(userid, oid);
							}
							catch( InvalidUser e)
							{
								System.out.println(e.getMessage());
							}
							catch( OrderNotExists e)
							{
								System.out.println(e.getMessage());
							}
							
							break;
						case 4://doesorderexists
							System.out.println("enter ordrid");
							boolean status1=os.doesRawMaterialOrderIdExists(sc.nextInt());
				            if(status1==true)
				            {
				            	System.out.println("order exists");
				            }
				            else
				            	System.out.println("order doesnot exists");
				            break;
						case 5://updateorder
							System.out.println("order details");
				         	os.displayOrders(userid);
							System.out.println("Enter the order id for which you want to update");
							int oid1=sc.nextInt();
							System.out.println("enter the quantity to update");
							int quanvalue=sc.nextInt();
							try 
							{
							boolean status2=os.updateOrder(oid1,quanvalue);
							if(status2)
							{
								System.out.println("update successful");
							}
							
							}
							catch(UpdateException e)
							{
								System.out.println(e.getMessage());
							}
							break;
						case 6: //supplierdetails
							System.out.println("enter the supllierid");
							try
							{
								os.fetchSupplierDetails(sc.nextInt());
							}
							catch(InvalidUser e)
							{
								System.out.println(e.getMessage());
							}
							break;
						default :
							sc.close();
							System.exit(0);
							
					}
					

				}


			}
			else
				throw new InvalidUser("login denied");
			
		}
		catch(InvalidUser e)
		{
			System.out.println(e.getMessage());
		}
		
		catch(Exception e)
		{
			System.out.println("Input mismatch\nplease enter valid Inputs");
		}
}
}
