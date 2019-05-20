package com.admin.model;

/**
 * @author darwin_he
 * @date 2019/5/18 22:32
 */
public class EnviroData {
	private int id;
	private String deviceNumber;
	private String deviceName;
	private float temperature;
	private float humidity;
	private int lightIntensity;
	private String time;

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

public float getTemperature() {
	return temperature;
}

public void setTemperature(float temperature) {
	this.temperature = temperature;
}

public float getHumidity() {
	return humidity;
}

public void setHumidity(float humidity) {
	this.humidity = humidity;
}

public int getLightIntensity() {
	return lightIntensity;
}

public void setLightIntensity(int lightIntensity) {
	this.lightIntensity = lightIntensity;
}

public String getTime() {
	return time;
}

public void setTime(String time) {
	this.time = time;
}
}
