package com.admin.model;

/**
 * @author darwin_he
 * @date 2019/5/18 22:37
 */
public class SystemMsg {
	private int id;
	private String title;
	private String msg;
	private String msgType;
	private String createTime;

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
