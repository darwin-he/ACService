package com.admin.model;

/**
 * @author darwin_he
 * @date 2019/5/18 22:34
 */
public class AdverMsg {
	private int id;
	private String msg;
	private char msgType;
	private String createTime;
	private String startTime;
	private String endTime;
	private char state;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getMsg() {
	return msg;
}

public void setMsg(String msg) {
	this.msg = msg;
}

public char getMsgType() {
	return msgType;
}

public void setMsgType(char msgType) {
	this.msgType = msgType;
}

public String getCreateTime() {
	return createTime;
}

public void setCreateTime(String createTime) {
	this.createTime = createTime;
}

public String getStartTime() {
	return startTime;
}

public void setStartTime(String startTime) {
	this.startTime = startTime;
}

public String getEndTime() {
	return endTime;
}

public void setEndTime(String endTime) {
	this.endTime = endTime;
}

public char getState() {
	return state;
}

public void setState(char state) {
	this.state = state;
}
}
