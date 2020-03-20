package com.entity;

import java.util.Date;

public class RawMaterialOrder {
int orderid;
int userid;
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
String name;
int supplierid;
int quanvalue;
String quanunit;
Date dateoford;
Date dateofdel;
int totalprice;
String deliveryStus;
int warehouseid;
public int getOrderid() {
	return orderid;
}
public void setOrderid(int orderid) {
	this.orderid = orderid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getSupplierid() {
	return supplierid;
}
public void setSupplierid(int supplierid) {
	this.supplierid = supplierid;
}
public int getQuanvalue() {
	return quanvalue;
}
public void setQuanvalue(int quanvalue) {
	this.quanvalue = quanvalue;
}
public String getQuanunit() {
	return quanunit;
}
public void setQuanunit(String quanunit) {
	this.quanunit = quanunit;
}
public Date getDateoford() {
	return dateoford;
}
public void setDateoford(Date dateoford) {
	this.dateoford = dateoford;
}
public Date getDateofdel() {
	return dateofdel;
}
public void setDateofdel(Date dateofdel) {
	this.dateofdel = dateofdel;
}
public int getTotalprice() {
	return totalprice;
}
public void setTotalprice(int totalprice) {
	this.totalprice = totalprice;
}
public String getDeliveryStus() {
	return deliveryStus;
}
public void setDeliveryStus(String deliveryStus) {
	this.deliveryStus = deliveryStus;
}
public int getWarehouseid() {
	return warehouseid;
}
public void setWarehouseid(int warehouseid) {
	this.warehouseid = warehouseid;
}
}
