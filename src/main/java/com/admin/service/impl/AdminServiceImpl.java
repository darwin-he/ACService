package com.admin.service.impl;

import com.admin.dao.AdminDao;
import com.admin.model.Admin;
import com.admin.ov.Location;
import com.admin.service.AdminService;
import com.admin.utils.CommonResult;
import com.admin.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author darwin_he
 * @date 2019/5/7 0:45
 */
@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDao adminDao;

	@Override
	public Object getLocation() {
		Location location=adminDao.getLocation();
		if (location==null)
			return new CommonResult(-1,"获取设备区域信息失败！");
		return new CommonResult(0,"获取设备区域信息成功！",location);
	}
	
	@Override
	public Object modifyAdminPassWord(String adminCount, String passWord) {
		Admin admin=adminDao.getAdminByCount(adminCount);
		if (admin==null)
			return new CommonResult(-1,"该管理员不存在！",adminCount);
		if (adminDao.modifyAdminPassWord(adminCount,passWord)!=1)
			return new CommonResult(-1,"密码更改失败！",passWord);
		return new CommonResult(-1,"密码更改成功！",passWord);
	}

@Override
	public Object adminLogin(String adminCount, String passWord) {
		Admin admin=adminDao.getAdminByCount(adminCount);
		if (admin==null)
			return new CommonResult(-1,"账户不存在！",adminCount);
		
		if (!admin.getPassWord().equals(passWord))
			return new CommonResult(-1,"密码错误",passWord);
		
		return new CommonResult(0,"登陆成功！",admin);
	}
	
	@Override
	public Object addAdmin(Admin admin) {
		Admin tempAdmin=adminDao.getAdminByCount(admin.getAdminCount());
		if (tempAdmin!=null)
			return new CommonResult(-1,"账号已注册！",admin);
		tempAdmin=adminDao.getAdminByDeviceNumber(admin.getDeviceNumber());
		if (tempAdmin!=null)
			return new CommonResult(-1,"设备已注册！",admin);
		
		int addResult=adminDao.addAdmin(admin);
		if (addResult!=1)
			return new CommonResult(-1,"新增管理员失败！",admin);
		
		return new CommonResult(0,"新增管理员成功！",admin);
	}
	
}
