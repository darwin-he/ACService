package com.admin.controller;

import com.admin.model.Admin;
import com.admin.service.AdminService;
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
	public Object adminLogin(@RequestParam(value = "account")String account,@RequestParam(value = "passWord")String passWord){
		return adminService.adminLogin(account,passWord);
	}
	
	@ResponseBody
	@RequestMapping(value = "/addAdmin",method = RequestMethod.POST)
	public Object addAdmin(@RequestBody Admin admin){
		return adminService.addAdmin(admin);
	}

	@ResponseBody
	@RequestMapping(value = "/deleteAdmin",method = RequestMethod.GET)
	public Object deleteAdmin(String superAdminAccount,String superAdminPassWord,int adminId){
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/modifyAdminPassWord",method = RequestMethod.GET)
	public Object modifyAdminPassWord(@RequestParam("account")String account,@RequestParam("passWord")String passWord){
		return adminService.modifyAdminPassWord(account,passWord);
	}
	
}
