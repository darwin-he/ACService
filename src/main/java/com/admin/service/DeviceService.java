package com.admin.service;

import com.admin.model.Device;

/**
 * @author darwin_he
 * @date 2019/5/20 15:55
 */
public interface DeviceService {
	Object getDeviceCount();
	
	Object getDeviceByDeviceNumber(String deviceNumber);
	
	Object addDevice(Device device);
}
