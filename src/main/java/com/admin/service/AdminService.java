package com.admin.service;

import com.admin.model.Admin;

/**
 * @author darwin_he
 * @date 2019/5/7 0:33
 */
public interface AdminService {
	
	Object getLocation();
	
	Object modifyAdminPassWord(String adminCount,String passWord);
	
	Object adminLogin(String adminCount,String passWord);
	
	Object addAdmin(Admin admin);
}
