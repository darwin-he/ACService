package com.admin.ov;

/**
 * @author darwin_he
 * @date 2019/5/7 19:29
 */
public class TableData {
private int code = 0;
private String msg = "请求成功";
private int totalCount=0;
private Object data = "";

public TableData(int code, String msg, int totalCount, Object data) {
	this.code = code;
	this.msg = msg;
	this.totalCount=totalCount;
	this.data = data;
}

public TableData(int code, String msg){
	this.code=code;
	this.msg=msg;
}

public int getTotalCount() {
	return totalCount;
}

public void setTotalCount(int totalCount) {
	this.totalCount = totalCount;
}

public int getCode() {
	return code;
}
public void setCode(int code) {
	this.code = code;
}
public String getMsg() {
	return msg;
}
public void setMsg(String msg) {
	this.msg = msg;
}
public Object getData() {
	return data;
}
public void setData(Object data) {
	this.data = data;
}
}
