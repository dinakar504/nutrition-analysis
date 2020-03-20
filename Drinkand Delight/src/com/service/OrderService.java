package com.service;

import java.sql.SQLException;

import com.dao.OrderDaoI;
import com.dao.OrderDoa;
import com.entity.RawMaterialOrder;

public class OrderService implements OrderServiceI{
	 static OrderDaoI dao=new OrderDoa();
	 @Override
	public void placeOrder(RawMaterialOrder bean) throws Exception
{
	dao.placeOrder(bean);
}
	 @Override
 public void displayOrders(int userid) throws Exception
 {
	 dao.displayorders(userid);
 }
	 @Override
	public void trackOrder(int uid,int oid) throws Exception
	{
		dao.trackorder(uid, oid);
	}
	 @Override
	 public boolean doesRawMaterialOrderIdExists(int orderid) throws Exception
	 {
		 return dao.doesRawMaterialOrderExists(orderid);
	 }
	 @Override
	 public boolean updateOrder(int oid,int quanvalue) throws Exception
	 {
		 return dao.updateorder(oid,quanvalue);
	 }
	 @Override
	 public void fetchSupplierDetails(int supid) throws SQLException, Exception
	 {
		 dao.fetchSupplierIds(supid);
	 }
	 @Override
	 public void fetchAllSuplierDetails( ) throws Exception
	 {
		 dao.fetchAllSupplierIds();
	 }
	 @Override
	 public void fetchWareHouseIds() throws Exception
	 {
		 dao.fetchAllWarehouseIds();
	 }
	 @Override
	 public void rawMaterialSpecs() throws Exception
	 {
		 dao.rawMaterialSpecs();
	 }
	 @Override
	 public boolean validateUser(int uid,String pass) throws ClassNotFoundException, SQLException 
	 {
		 return dao.validate(uid, pass);
	 }
}
