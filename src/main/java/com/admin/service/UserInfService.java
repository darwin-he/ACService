package com.admin.service;

import com.admin.model.User;

/**
 * @author darwin_he
 * @date 2019/5/2 17:12
 */
public interface UserInfService {
	/*************************************新增用户************************************/
	Object addUser(User user);
	
	/*************************************删除用户************************************/
	Object deleteUserById(int id);
	
	Object deleteUserByCardNumber(String cardNumber);
	
	Object deleteUserByAccount(String account);
	
	/*************************************获取用户************************************/
	Object getUserCount();
	
	Object getUser(int page,int limit);
	
	Object getUserByCardNumber(String cardNumber);
	
	Object getUserById(int id);
	
	Object getUserByAccount(String account);
	
	Object getUsersByName(String Name,int page,int limit);
	
	Object getUsersByDate(String leftDate,String rightDate,int page,int limit);
	
	/*************************************修改用户************************************/
	Object modifyUserPassWord(int id,String passWord);

	Object modifyUserCardNumber(int id,String cardNumber);
	
	Object modifyUserName(int id,String userName);
	
	Object modifyUserState(int id,String state);
	
	Object modifyUserAccount(int id,String account);
	
}
