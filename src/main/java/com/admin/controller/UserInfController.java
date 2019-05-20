package com.admin.controller;

import com.admin.model.User;
import com.admin.vo.Location;
import com.admin.service.UserInfService;
import com.admin.vo.CommonResult;
import com.admin.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author darwin_he
 * @date 2019/5/2 0:31
 */
@Controller
public class UserInfController {
	
	@Autowired
	private UserInfService userInfService;
	
	/*************************************新增用户************************************/
	@ResponseBody
	@RequestMapping(value = "/addUser",method = RequestMethod.POST)
	public Object addUser(@RequestBody User user){
		return userInfService.addUser(user);
	}
	
	/*************************************删除用户************************************/
	@ResponseBody
	@RequestMapping(value = "/deleteUserById",method = RequestMethod.GET)
	public Object deleteUserByUserId(@RequestParam(value = "id")int id){
		return userInfService.deleteUserById(id);
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteUserByCardNumber",method = RequestMethod.GET)
	public Object deleteUserByUserCard(@RequestParam(value = "cardNumber") String cardNumber){
		return userInfService.deleteUserByCardNumber(cardNumber);
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteUserByAccount",method = RequestMethod.GET)
	public Object deleteUserByUserCount(@RequestParam(value = "account")String account){
		return userInfService.deleteUserByAccount(account);
	}
	
	/*************************************获取用户************************************/
	@ResponseBody
	@RequestMapping(value = "/getUserCount",method = RequestMethod.GET)
	public Object getUserCount(){
		return userInfService.getUserCount();
	}
	
	@ResponseBody
	@RequestMapping(value = "/getUsers",method = RequestMethod.GET)
	public Object getUsers(@RequestParam(value = "page")int page,@RequestParam(value = "limit")int limit){
		return userInfService.getUser(page,limit);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getUserByCardNumber",method = RequestMethod.GET)
	public Object getUserByUserCard(@RequestParam(value = "cardNumber") String cardNumber){
		return userInfService.getUserByCardNumber(cardNumber);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getUserById",method = RequestMethod.GET)
	public Object getUserByUserId(@RequestParam(value = "id")int id){
		return userInfService.getUserById(id);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getUserByAccount",method = RequestMethod.GET)
	public Object getUserByUserCount(@RequestParam(value = "account")String account){
		return userInfService.getUserByAccount(account);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getUsersByName",method = RequestMethod.GET)
	public Object getUsersByName(@RequestParam(value = "name")String name,
	                                 @RequestParam(value = "page")int page,@RequestParam(value = "limit") int limit){
		return userInfService.getUsersByName(name,page,limit);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getUsersByDate",method = RequestMethod.GET)
	public Object getUsersByDate(@RequestParam(value = "leftDate")String leftDate,@RequestParam(value = "rightDate")String rightDate,
	                             @RequestParam(value = "page")int page,@RequestParam(value = "limit") int limit){
		return userInfService.getUsersByDate(leftDate,rightDate,page,limit);
	}
	
	/*************************************修改用户************************************/
	
	@ResponseBody
	@RequestMapping(value = "/modifyUserPassWord",method = RequestMethod.GET)
	public Object modifyUserPassWord(@RequestParam(value = "id")int id, @RequestParam(value = "passWord")String passWord) {
		return userInfService.modifyUserPassWord(id,passWord);
	}

	@ResponseBody
	@RequestMapping(value = "/modifyUserCardNumber",method = RequestMethod.GET)
	public Object modifyUserCard(@RequestParam(value = "id")int id, @RequestParam(value = "cardNumber")String userCard) {
		return userInfService.modifyUserCardNumber(id,userCard);
	}
	
	@ResponseBody
	@RequestMapping(value = "/modifyUserName",method = RequestMethod.GET)
	public Object modifyUserName(@RequestParam(value = "id")int userId, @RequestParam(value = "userName")String userName) {
		return userInfService.modifyUserName(userId,userName);
	}

	@ResponseBody
	@RequestMapping(value = "/modifyUserState",method = RequestMethod.GET)
	public Object modifyUserState(@RequestParam(value = "id")int id, @RequestParam(value = "state")String state) {
		return userInfService.modifyUserState(id,state);
	}

	@ResponseBody
	@RequestMapping(value = "/modifyUserAccount",method = RequestMethod.GET)
	public Object modifyUserCount(@RequestParam(value = "id")int id, @RequestParam(value = "account")String userCount) {
		return userInfService.modifyUserAccount(id,userCount);
	}
	
}
