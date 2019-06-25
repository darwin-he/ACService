package com.admin.model;

/**
 * @author darwin_he
 * @date 2019/5/7 0:35
 */
public class Admin {
	private int id;
	private String account;
	private String name;
	private String passWord;
	private String deviceNumber;
	private String deviceName;
	private String deviceNikeName;
	private String type;
	private String state;
	private String registerTime;
	public String getDeviceNikeName() {
		return deviceNikeName;
	}
	
	public void setDeviceNikeName(String deviceNikeName) {
		this.deviceNikeName = deviceNikeName;
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAccount() {
		return account;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
	
	public String getDeviceName() {
		return deviceName;
	}
	
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getRegisterTime() {
		return registerTime;
	}
	
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
}
