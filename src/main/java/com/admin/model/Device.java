package com.admin.model;

/**
 * @author darwin_he
 * @date 2019/5/28 19:32
 */
public class Device {
    private int id;
    private String deviceNumber;
    private String deviceName;
    private String deviceNikeName;
    private String state;
    private String registerTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }
}
