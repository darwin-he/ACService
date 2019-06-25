package com.admin.controller;

import com.admin.model.Device;
import com.admin.service.DeviceService;
import com.admin.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author darwin_he
 * @date 2019/5/20 15:54
 */
@RestController
public class DeviceController {
	@Autowired
	private DeviceService deviceService;
	
	@RequestMapping(value = "/getDeviceCount",method = RequestMethod.GET)
	public Object getDeviceCount(){
		return deviceService.getDeviceCount();
	}
	
	@RequestMapping(value = "/addDevice",method = RequestMethod.POST)
	public Object addDevice(@RequestBody Device device){
		return deviceService.addDevice(device);
	}
}
