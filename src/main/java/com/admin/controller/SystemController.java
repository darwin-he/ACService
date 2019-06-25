package com.admin.controller;

import com.admin.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author darwin_he
 * @date 2019/5/19 4:33
 */
@RestController
public class SystemController {
	@Autowired
	private SystemService systemService;
	/*************************************系统信息相关************************************/
	@RequestMapping(value = "/getLocation",method = RequestMethod.GET)
	public Object getLocation(){
		return systemService.getLocation();
	}
	
	@RequestMapping(value = "/getSystemMsgByMsgId",method = RequestMethod.GET)
	public Object getSystemMsgByMsgId(@RequestParam("id") int id){
		return systemService.getSystemMsgByMsgId(id);
	}
	
}
