package com.admin.service.impl;

import com.admin.dao.EnviroDao;
import com.admin.model.EnviroData;
import com.admin.service.EnviroService;
import com.admin.vo.CommonResult;
import com.admin.vo.EnviroDataForEchart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author darwin_he
 * @date 2019/5/20 16:01
 */
@Service
public class EnviroServiceImpl implements EnviroService {
	@Autowired
	private EnviroDao enviroDao;

	@Override
	public Object getCurrentDayEnviroDataByDeviceNumberForEchart(String deviceNumber) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String time=df.format(new Date());// new Date()为获取当前系统时间
		String startTime=time.substring(0,11)+"00:00:00";
		String endTime=time.substring(0,11)+"23:59:59";
		List<EnviroData> enviroDataList=enviroDao.getEnviroDataByDeviceNumberInPeriod(deviceNumber,startTime,endTime);
		if (enviroDataList.isEmpty())
			return new CommonResult(-1,"无今日环境数据！");
		List<String> timeList=new ArrayList<>();
		List<Float> temperatureData=new ArrayList<>();
		List<Float> humidityData=new ArrayList<>();
		List<Integer> lightData=new ArrayList<>();
		for (int i=0;i<enviroDataList.size();i++){
			EnviroData enviroData=enviroDataList.get(i);
			timeList.add(enviroData.getTime().substring(11,16));
			temperatureData.add(enviroData.getTemperature());
			humidityData.add(enviroData.getHumidity());
			lightData.add(enviroData.getLightIntensity());
		}
		EnviroDataForEchart enviroDataVo=new EnviroDataForEchart();
		enviroDataVo.setTimeList(timeList);
		enviroDataVo.setTemperatureData(temperatureData);
		enviroDataVo.setHumidityData(humidityData);
		enviroDataVo.setLightData(lightData);
		return new CommonResult(0,"获取当日环境数据成功！",enviroDataVo);
	}

	@Override
	public Object getCurrentEnviroDataByDeviceNumber(String deviceNumber) {
		EnviroData enviroData=enviroDao.getCurrentEnviroDataByDeviceNumber(deviceNumber);
		if (enviroData==null)
			return new CommonResult(-1,"获取最新环境数据失败！");
		return new CommonResult(0,"获取最新环境数据成功！",enviroData);
	}
}
