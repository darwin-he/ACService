package com.admin.controller;

import com.admin.service.EnviroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author darwin_he
 * @date 2019/5/20 15:59
 */
@RestController
public class EnviroController {
	@Autowired
	private EnviroService enviroService;
	
	
	@RequestMapping(value = "/getAllTemperatureByDeviceNumber",method = RequestMethod.GET)
	public Object getAllTemperatureByDeviceNumber(String deviceNumber,int page,int limit){
		return null;
	}
	
	@RequestMapping(value = "/getOneDayTemperatureByDeviceNumber",method = RequestMethod.GET)
	public Object getOneDayTemperatureByDeviceNumber(String deviceNumber,String time){
		return null;
	}
	
	
	@RequestMapping(value = "/getAllHumidityByDeviceNumber",method = RequestMethod.GET)
	public Object getAllHumidityByDeviceNumber(String deviceNumber,int page,int limit){
		return null;
	}
	
	@RequestMapping(value = "/getOneDayHumidityByDeviceNumber",method = RequestMethod.GET)
	public Object getOneDayHumidityByDeviceNumber(String deviceNumber,String time){
		return null;
	}
	
	
	@RequestMapping(value = "/getAllLightIntensityByDeviceNumber",method = RequestMethod.GET)
	public Object getAllLightIntensityByDeviceNumber(String deviceNumber,int page,int limit){
		return null;
	}
	
	@RequestMapping(value = "/getOneDayLightIntensityByDeviceNumber",method = RequestMethod.GET)
	public Object getOneDayLightIntensityByDeviceNumber(String deviceNumber,String time){
		return null;
	}
	
	
	@RequestMapping(value = "/getOneDayEnviroDataByDeviceNumber",method = RequestMethod.GET)
	public Object getCurrentDayEnviroDataByDeviceNumber(@RequestParam("deviceNumber") String deviceNumber){
		return null;
	}
	
	@RequestMapping(value = "/getAllEnviroDataByDeviceNumber",method = RequestMethod.GET)
	public Object getAllEnviroDataByDeviceNumber(String deviceNumber,int page,int limit){
		return null;
	}
	
	
	@RequestMapping(value = "/getCurrentDayEnviroDataByDeviceNumberForEchart",method = RequestMethod.GET)
	public Object getCurrentDayEnviroDataByDeviceNumberForEchart(@RequestParam("deviceNumber") String deviceNumber){
		return enviroService.getCurrentDayEnviroDataByDeviceNumberForEchart(deviceNumber);
	}
	
	@RequestMapping(value = "/getCurrentEnviroDataByDeviceNumber",method = RequestMethod.GET)
	public Object getCurrentEnviroDataByDeviceNumber(@RequestParam("deviceNumber")String deviceNumber){
		return enviroService.getCurrentEnviroDataByDeviceNumber(deviceNumber);
	}
}
