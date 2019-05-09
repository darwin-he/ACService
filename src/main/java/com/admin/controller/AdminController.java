package com.admin.controller;

import com.admin.model.Admin;
import com.admin.model.Card;
import com.admin.ov.Location;
import com.admin.service.AdminService;
import com.admin.utils.CommonResult;
import com.admin.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author darwin_he
 * @date 2019/5/6 16:24
 */
@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@ResponseBody
	@RequestMapping(value = "/adminLogin",method = RequestMethod.POST)
	public Object adminLogin(@RequestParam(value = "adminCount")String adminCount,@RequestParam(value = "passWord")String passWord){
		return adminService.adminLogin(adminCount,passWord);
	}
	
	@ResponseBody
	@RequestMapping(value = "/addAdmin",method = RequestMethod.POST)
	public Object addAdmin(@RequestBody Admin admin){
		CommonResult result=(CommonResult) adminService.getLocation();
		if (result.getCode()!=0)
			return result;
		Location location=(Location) result.getData();
		admin.setProvince(location.getProvince());
		admin.setCity(location.getCity());
		admin.setCounty(location.getCounty());
		admin.setCommunity(location.getCommunity());
		admin.setState('1');
		admin.setRegisterTime(TimeUtil.getCurrentTime());
		return adminService.addAdmin(admin);
	}
	
	@ResponseBody
	@RequestMapping(value = "/modifyAdminPassWord",method = RequestMethod.GET)
	public Object modifyAdminPassWord(@RequestParam("adminCount")String adminCount,@RequestParam("passWord")String passWord){
		return adminService.modifyAdminPassWord(adminCount,passWord);
	}
	
	@ResponseBody
	@RequestMapping(value = "/readCardNumber",method = RequestMethod.GET)
	public Object readCardNumber(){
		Card card=new Card();
		card.setCardNumber("88888888");
		return new CommonResult(0,"卡片读取成功！",card);
	}
	
	@ResponseBody
	@RequestMapping(value = "/openDoor",method = RequestMethod.GET)
	public Object openDoor(){
		return null;
	}
}
