package com.admin.model;

/**
 * @author darwin_he
 * @date 2019/5/30 0:45
 */
public class AdminMsg {
	private int id;
	private String adminAccount;
	private String title;
	private int msgId;
	private String state;
	private String createTime;

public String getCreateTime() {
	return createTime;
}

public void setCreateTime(String createTime) {
	this.createTime = createTime;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getAdminAccount() {
	return adminAccount;
}

public void setAdminAccount(String adminAccount) {
	this.adminAccount = adminAccount;
}

public int getMsgId() {
	return msgId;
}

public void setMsgId(int msgId) {
	this.msgId = msgId;
}

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}
}
