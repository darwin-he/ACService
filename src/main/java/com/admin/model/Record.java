package com.admin.model;

/**
 * @author darwin_he
 * @date 2019/5/2 1:56
 */
public class Record {
    private int id;
    private String userAccount;
    private String userName;
    private String deviceNumber;
    private String deviceNikeName;
    private String state;
    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
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

    public String getDeviceNikeName() {
        return deviceNikeName;
    }

    public void setDeviceNikeName(String deviceNikeName) {
        this.deviceNikeName = deviceNikeName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
