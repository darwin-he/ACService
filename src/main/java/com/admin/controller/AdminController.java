package com.admin.controller;

import com.admin.model.Admin;
import com.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author darwin_he
 * @date 2019/5/6 16:24
 */
@RestController
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value = "/adminLogin",method = RequestMethod.POST)
	public Object adminLogin(@RequestParam(value = "account")String account,@RequestParam(value = "passWord")String passWord){
		return adminService.adminLogin(account,passWord);
	}
	
	@RequestMapping(value = "/addAdmin",method = RequestMethod.POST)
	public Object addAdmin(@RequestBody Admin admin){
		return adminService.addAdmin(admin);
	}
	
	@RequestMapping(value = "/deleteAdmin",method = RequestMethod.GET)
	public Object deleteAdmin(String superAdminAccount,String superAdminPassWord,int adminId){
		return null;
	}
	
	@RequestMapping(value = "/modifyAdminPassWord",method = RequestMethod.GET)
	public Object modifyAdminPassWord(@RequestParam("account")String account,@RequestParam("passWord")String passWord){
		return adminService.modifyAdminPassWord(account,passWord);
	}
	
	@RequestMapping(value = "/getAdminMsgs",method = RequestMethod.GET)
	public Object getAdminMsgs(@RequestParam("account")String account,@RequestParam("page") int page,@RequestParam("limit") int limit){
		return adminService.getAdminMsgs(account,page,limit);
	}
	
	@RequestMapping(value = "/deleteAdminMsgByMsId",method = RequestMethod.GET)
	public Object deleteAdminMsgByMsId(@RequestParam("id")int id){
		return adminService.deleteAdminMsgByMsgId(id);
	}
	
	@RequestMapping(value = "/modifyAdminMsgStateByMsId",method = RequestMethod.GET)
	public Object modifyAdminMsgStateByMsId(@RequestParam("id")int id,@RequestParam("state")String state){
		return adminService.modifyAdminMsgStateByMsId(id,state);
	}
	
	@RequestMapping(value = "getAdminMsgByDate",method = RequestMethod.GET)
	public Object getAdminMsgByDate(@RequestParam("account")String account,@RequestParam("leftDate")String leftDate,
	                                @RequestParam("rightDate")String rightDate, @RequestParam("page") int page,
	                                @RequestParam("limit") int limit){
		return adminService.getAdminMsgByDate(account,page,limit,leftDate,rightDate);
	}
	
	
}
