package com.dao;

import java.sql.SQLException;

import com.entity.RawMaterialOrder;

public interface OrderDaoI {

public	void placeOrder(RawMaterialOrder obean) throws Exception;

public void displayorders(int id) throws Exception;

public boolean doesRawMaterialOrderExists(int orderid) throws OrderNotExists ;

public boolean updateorder(int oid,int quanvalue) throws Exception;

public void fetchSupplierIds(int sid) throws SQLException, Exception;

public void fetchAllSupplierIds() throws Exception;

public void fetchAllWarehouseIds() throws Exception;

public void rawMaterialSpecs() throws Exception;

public void trackorder(int uid, int orderid) throws OrderNotExists,SQLException,ClassNotFoundException;

public boolean validate(int uid, String pass) throws ClassNotFoundException, SQLException;

}
