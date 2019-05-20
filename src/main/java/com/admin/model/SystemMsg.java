package com.admin.model;

/**
 * @author darwin_he
 * @date 2019/5/18 22:37
 */
public class SystemMsg {
	private int id;
	private String msg;
	private char msgType;
	private String createTime;

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
}
