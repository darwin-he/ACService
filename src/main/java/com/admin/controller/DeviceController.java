package com.admin.controller;

import com.admin.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	
}
