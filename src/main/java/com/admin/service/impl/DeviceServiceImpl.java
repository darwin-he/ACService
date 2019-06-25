package com.admin.service.impl;

import com.admin.dao.DeviceDao;
import com.admin.model.Device;
import com.admin.service.DeviceService;
import com.admin.utils.TimeUtil;
import com.admin.vo.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author darwin_he
 * @date 2019/5/20 15:55
 */
@Service
public class DeviceServiceImpl implements DeviceService {
	@Autowired
	private DeviceDao deviceDao;
	
	@Override
	public Object getDeviceCount() {
		int deviceCount=deviceDao.getDeviceCount();
		return new CommonResult(0,"获取总设备数成功！",deviceCount);
	}
	
	@Override
	public Object getDeviceByDeviceNumber(String deviceNumber){
		Device device=deviceDao.getDeviceByDeviceNumber(deviceNumber);
		if (device!=null)
			return new CommonResult(0,"获取设备信息成功！",device);
		else
			return new CommonResult(-1,"获取设备信息失败！");
	}

	@Override
	public Object addDevice(Device device) {
		Device tempDevice=deviceDao.getDeviceByDeviceNumber(device.getDeviceNumber());
		if (tempDevice!=null)
			return new CommonResult(-1,"设备已注册！");
		device.setState("可用");
		device.setRegisterTime(TimeUtil.getCurrentTime());
		int addResult=deviceDao.addDevice(device);
		if (addResult==1)
			return new CommonResult(0,"注册设备成功！");
		return new CommonResult(-1,"注册设备失败");
	}
}
