package com.admin.service;

import com.admin.model.Admin;

/**
 * @author darwin_he
 * @date 2019/5/7 0:33
 */
public interface AdminService {
	Object modifyAdminPassWord(String account,String passWord);
	
	Object adminLogin(String account,String passWord);
	
	Object addAdmin(Admin admin);
}
