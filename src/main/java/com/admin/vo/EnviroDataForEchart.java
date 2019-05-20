package com.admin.vo;

import java.util.List;

/**
 * @author darwin_he
 * @date 2019/5/19 3:43
 */
public class EnviroDataForEchart {
	
	private List<Float> temperatureData;
	
	private List<Float> humidityData;
	
	private List<Integer> lightData;

	private List<String> timeList;

public List<String> getTimeList() {
	return timeList;
}

public void setTimeList(List<String> timeList) {
	this.timeList = timeList;
}

public List<Float> getTemperatureData() {
	return temperatureData;
}

public void setTemperatureData(List<Float> temperatureData) {
	this.temperatureData = temperatureData;
}


public List<Float> getHumidityData() {
	return humidityData;
}

public void setHumidityData(List<Float> humidityData) {
	this.humidityData = humidityData;
}


public List<Integer> getLightData() {
	return lightData;
}

public void setLightData(List<Integer> lightData) {
	this.lightData = lightData;
}

}
