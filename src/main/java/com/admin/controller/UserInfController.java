package com.admin.controller;

import com.admin.model.User;
import com.admin.ov.Location;
import com.admin.service.UserInfService;
import com.admin.utils.CommonResult;
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
		CommonResult result =(CommonResult) userInfService.getLocation();
		if (result.getCode()==0){
			Location location=(Location) result.getData();
			user.setProvince(location.getProvince());
			user.setCity(location.getCity());
			user.setCounty(location.getCounty());
			user.setCommunity(location.getCommunity());
			user.setState('1');
			user.setRegisterTime(TimeUtil.getCurrentTime());
		}else {
			return result;
		}
		return userInfService.addUser(user);
	}
	
	/*************************************删除用户************************************/
	@ResponseBody
	@RequestMapping(value = "/deleteUserByUserId",method = RequestMethod.GET)
	public Object deleteUserByUserId(@RequestParam(value = "userId")int userId){
		return userInfService.deleteUserByUserId(userId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteUserByUserCard",method = RequestMethod.GET)
	public Object deleteUserByUserCard(@RequestParam(value = "userCard") String userCard){
		return userInfService.deleteUserByUserCard(userCard);
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteUserByUserCount",method = RequestMethod.GET)
	public Object deleteUserByUserCount(@RequestParam(value = "userCount")String userCount){
		return userInfService.deleteUserByUserCount(userCount);
	}
	
	/*************************************获取用户************************************/
	@ResponseBody
	@RequestMapping(value = "/getUsers",method = RequestMethod.GET)
	public Object getUsers(@RequestParam(value = "page")int page,@RequestParam(value = "limit")int limit){
		return userInfService.getUser(page,limit);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getUserByUserCard",method = RequestMethod.GET)
	public Object getUserByUserCard(@RequestParam(value = "userCard") String userCard){
		return userInfService.getUserByUserCard(userCard);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getUserByUserId",method = RequestMethod.GET)
	public Object getUserByUserId(@RequestParam(value = "userId")int userId){
		return userInfService.getUserByUserId(userId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getUserByUserCount",method = RequestMethod.GET)
	public Object getUserByUserCount(@RequestParam(value = "userCount")String userCount){
		return userInfService.getUserByUserCount(userCount);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getUsersByUserName",method = RequestMethod.GET)
	public Object getUsersByUserName(@RequestParam(value = "userName")String userName,
	                                 @RequestParam(value = "page")int page,@RequestParam(value = "limit") int limit){
		return userInfService.getUsersByUserName(userName,page,limit);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getUsersByDate",method = RequestMethod.GET)
	public Object getUsersByDate(@RequestParam(value = "leftDate")String leftDate,@RequestParam(value = "rightDate")String rightDate,
	                             @RequestParam(value = "page")int page,@RequestParam(value = "limit") int limit){
		return userInfService.getUsersByDate(leftDate,rightDate,page,limit);
	}
	
	/*************************************修改用户************************************/
	@ResponseBody
	@RequestMapping(value = "/modifyUser",method = RequestMethod.GET)
	public Object modifyUser(@RequestBody User user){
		return userInfService.modifyUser(user);
	}
	
	@ResponseBody
	@RequestMapping(value = "/modifyUserPassWord",method = RequestMethod.GET)
	public Object modifyUserPassWord(@RequestParam(value = "userId")int userId, @RequestParam(value = "passWord")String passWord) {
		return userInfService.modifyUserPassWord(userId,passWord);
	}

	@ResponseBody
	@RequestMapping(value = "/modifyUserCard",method = RequestMethod.GET)
	public Object modifyUserCard(@RequestParam(value = "userId")int userId, @RequestParam(value = "userCard")String userCard) {
		return userInfService.modifyUserCard(userId,userCard);
	}
	
	@ResponseBody
	@RequestMapping(value = "/modifyUserName",method = RequestMethod.GET)
	public Object modifyUserName(@RequestParam(value = "userId")int userId, @RequestParam(value = "userName")String userName) {
		return userInfService.modifyUserName(userId,userName);
	}

	@ResponseBody
	@RequestMapping(value = "/modifyUserState",method = RequestMethod.GET)
	public Object modifyUserState(@RequestParam(value = "userId")int userId, @RequestParam(value = "state")char state) {
		return userInfService.modifyUserState(userId,state);
	}

	@ResponseBody
	@RequestMapping(value = "/modifyUserCount",method = RequestMethod.GET)
	public Object modifyUserCount(@RequestParam(value = "userId")int userId, @RequestParam(value = "userCount")String userCount) {
		return userInfService.modifyUserCount(userId,userCount);
	}
	
	@ResponseBody
	@RequestMapping(value = "/modifyUserLocation",method = RequestMethod.GET)
	public Object modifyUserLocation(@RequestParam(value = "userId")int userId, @RequestParam(value = "province")String province,
	                                 @RequestParam(value = "city")String city, @RequestParam(value = "county")String county,
	                                 @RequestParam(value = "community")String community) {
		return userInfService.modifyUserLocation(userId,province,city,county,community);
	}
	
}
