package com.admin.model;

/**
 * @author darwin_he
 * @date 2019/5/7 0:35
 */
public class Admin {
private int adminId;
private String adminCount;
private String adminName;
private String passWord;
private String deviceNumber;
private String province;
private String city;
private String county;
private String community;
private char state;
private String registerTime;

public int getAdminId() {
	return adminId;
}

public void setAdminId(int adminId) {
	this.adminId = adminId;
}

public String getAdminCount() {
	return adminCount;
}

public void setAdminCount(String adminCount) {
	this.adminCount = adminCount;
}

public String getAdminName() {
	return adminName;
}

public void setAdminName(String adminName) {
	this.adminName = adminName;
}

public String getPassWord() {
	return passWord;
}

public void setPassWord(String passWord) {
	this.passWord = passWord;
}

public String getDeviceNumber() {
	return deviceNumber;
}

public void setDeviceNumber(String deviceNumber) {
	this.deviceNumber = deviceNumber;
}

public String getProvince() {
	return province;
}

public void setProvince(String province) {
	this.province = province;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public String getCounty() {
	return county;
}

public void setCounty(String county) {
	this.county = county;
}

public String getCommunity() {
	return community;
}

public void setCommunity(String community) {
	this.community = community;
}

public char getState() {
	return state;
}

public void setState(char state) {
	this.state = state;
}

public String getRegisterTime() {
	return registerTime;
}

public void setRegisterTime(String registerTime) {
	this.registerTime = registerTime;
}
}
