package com.admin.vo;

/**
 * @author darwin_he
 * @date 2019/5/30 0:47
 */
public class AdminSystemMsg {
	//对应于adminmsg中的字段
	private int id;
	private String adminAccount;
	private String state;
	//对应于systemmsg表中的字段
	private int msgId;
	private String msg;
	private String msgType;
	private String createTime;

public String getAdminAccount() {
	return adminAccount;
}

public void setAdminAccount(String adminAccount) {
	this.adminAccount = adminAccount;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

public int getMsgId() {
	return msgId;
}

public void setMsgId(int msgId) {
	this.msgId = msgId;
}

public String getMsg() {
	return msg;
}

public void setMsg(String msg) {
	this.msg = msg;
}

public String getMsgType() {
	return msgType;
}

public void setMsgType(String msgType) {
	this.msgType = msgType;
}

public String getCreateTime() {
	return createTime;
}

public void setCreateTime(String createTime) {
	this.createTime = createTime;
}
}
