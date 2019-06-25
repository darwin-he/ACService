package com.admin.service;

import com.admin.model.EnviroData;

/**
 * @author darwin_he
 * @date 2019/5/20 16:00
 */
public interface EnviroService {
	Object getCurrentDayEnviroDataByDeviceNumberForEchart( String deviceNumber);

	Object getCurrentEnviroDataByDeviceNumber(String deviceNumber);
	
	Object addErviroData(EnviroData enviroData);
}
