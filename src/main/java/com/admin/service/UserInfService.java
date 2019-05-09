package com.admin.service;

import com.admin.model.User;

/**
 * @author darwin_he
 * @date 2019/5/2 17:12
 */
public interface UserInfService {

	/*************************************用户归属************************************/
	Object getLocation();
	
	/*************************************新增用户************************************/
	Object addUser(User user);
	
	/*************************************删除用户************************************/
	Object deleteUserByUserId(int userId);
	
	Object deleteUserByUserCard(String userCard);
	
	Object deleteUserByUserCount(String userCount);
	
	/*************************************获取用户************************************/
	Object getUser(int page,int limit);
	
	Object getUserByUserCard(String userCard);
	
	Object getUserByUserId(int userId);
	
	Object getUserByUserCount(String userCount);
	
	Object getUsersByUserName(String userName,int page,int limit);
	
	Object getUsersByDate(String leftDate,String rightDate,int page,int limit);
	
	Object getUsersByLocation(String province,String city,String county,String community,int page,int limit);
	
	/*************************************修改用户************************************/
	Object modifyUser(User user);
	
	Object modifyUserPassWord(int userId,String passWord);

	Object modifyUserCard(int userId,String userCard);
	
	Object modifyUserName(int userId,String userName);
	
	Object modifyUserState(int userId,char state);
	
	Object modifyUserCount(int userId,String userCount);
	
	Object modifyUserLocation(int userId, String province,String city, String county,String community);
}
