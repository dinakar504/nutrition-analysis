package com.service;

import java.sql.SQLException;

import com.entity.RawMaterialOrder;

public interface OrderServiceI {

	public void placeOrder(RawMaterialOrder bean) throws Exception;

	public void displayOrders(int userid) throws Exception;

	//public void trackRawMaterialOrder(int uid) throws Exception;

	public boolean doesRawMaterialOrderIdExists(int orderid) throws Exception;

	public boolean updateOrder(int oid,int quanvalue) throws Exception;

	public void fetchSupplierDetails(int supid) throws SQLException, Exception;

	public void fetchAllSuplierDetails() throws Exception;

	public void fetchWareHouseIds() throws Exception;

	public void rawMaterialSpecs() throws Exception;

	void trackOrder(int uid, int oid) throws Exception;

	public boolean validateUser(int uid, String pass) throws ClassNotFoundException, SQLException;
	

}
