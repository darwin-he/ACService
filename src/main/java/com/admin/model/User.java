package com.admin.model;


/**
 * @author darwin_he
 * @date 2019/5/2 0:39
 */
public class User {
	private int userId;
	private String userCount;
	private String userName;
	private String passWord;
	private String userCard;
	private String province;
	private String city;
	private String county;
	private String community;
	private char state;
	private String registerTime;
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
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
	
	public String getPassWord() {
		return passWord;
	}
	
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	public String getUserCard() {
		return userCard;
	}
	
	public void setUserCard(String userCard) {
		this.userCard = userCard;
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
