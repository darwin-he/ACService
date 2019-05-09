package com.admin.model;

/**
 * @author darwin_he
 * @date 2019/5/2 1:56
 */
public class Record {
	private int passId;
	private String userCount;
	private String userName;
	private String deviceNumber;
	private String deviceName;
	private char state;
	private String time;

	public int getPassId() {
		return passId;
	}
	
	public void setPassId(int passId) {
		this.passId = passId;
	}

	public String getUserCount() {
		return userCount;
	}
	
	public void setUserCount(String userCount) {
		this.userCount = userCount;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
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
	
	public char getState() {
		return state;
	}
	
	public void setState(char state) {
		this.state = state;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
}
