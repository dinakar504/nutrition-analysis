package com.dao;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

public class Test1 {

	static OrderDaoI dao=new OrderDoa();
	@Test
	 public void updateOrder() throws Exception
	 {
		//test for orderid that exists
		 boolean  status=dao.updateorder(9, 7);
		 //test for orderid that does not exists
		 assertEquals(true,status );
		 
	     boolean status1=dao.updateorder(1222,10);
	     assertEquals(false,status1);
	 }
	@Test
	public void doesRawMaterialOrderExists() 
	{
		boolean status;
		try {
			status = dao.doesRawMaterialOrderExists(9);
			assertEquals(true, status);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean status1;
		try {
			status1 = dao.doesRawMaterialOrderExists(20);
			assertEquals(false, status1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void validate() throws ClassNotFoundException, SQLException
	{   //test for user that exists
		boolean status =dao.validate(1,"dinakar1" );
		assertEquals(true, status);
		boolean status1=dao.validate(1, "dinakar");
		assertEquals(false, status1);
	}
}
