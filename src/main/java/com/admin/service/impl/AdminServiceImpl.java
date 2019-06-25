package com.admin.service.impl;

import com.admin.dao.AdminDao;
import com.admin.dao.DeviceDao;
import com.admin.model.Admin;
import com.admin.model.AdminMsg;
import com.admin.model.Device;
import com.admin.service.AdminService;
import com.admin.utils.TimeUtil;
import com.admin.vo.CommonResult;
import com.admin.vo.TableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author darwin_he
 * @date 2019/5/7 0:45
 */
@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private DeviceDao deviceDao;
	
	@Override
	public Object modifyAdminPassWord(String account, String passWord) {
		Admin admin=adminDao.getAdminByAccount(account);
		if (admin==null)
			return new CommonResult(-1,"该管理员不存在！",account);
		if (adminDao.modifyAdminPassWord(account,passWord)!=1)
			return new CommonResult(-1,"密码更改失败！",passWord);
		return new CommonResult(0,"密码更改成功！",passWord);
	}

	@Override
	public Object adminLogin(String account, String passWord) {
		Admin admin=adminDao.getAdminByAccount(account);
		if (admin==null)
			return new CommonResult(-1,"账户不存在！",account);
		
		if (!admin.getPassWord().equals(passWord))
			return new CommonResult(-1,"密码错误",passWord);
		
		return new CommonResult(0,"登陆成功！",admin);
	}
	
	@Override
	public Object addAdmin(Admin admin) {
		Admin tempAdmin=adminDao.getAdminByAccount(admin.getAccount());
		if (tempAdmin!=null)
			return new CommonResult(-1,"账号已注册！",admin);
		tempAdmin=adminDao.getAdminByDeviceNumber(admin.getDeviceNumber());
		if (tempAdmin!=null)
			return new CommonResult(-1,"改设备已与其他管理员关联！",admin);
		
		Device tempDevice=deviceDao.getDeviceByDeviceNumber(admin.getDeviceNumber());
		if (tempDevice==null){
			Device device=new Device();
			device.setDeviceNumber(admin.getDeviceNumber());
			device.setDeviceName(admin.getDeviceName());
			device.setDeviceNikeName(admin.getDeviceNikeName());
			device.setState("可用");
			device.setRegisterTime(TimeUtil.getCurrentTime());
			int addResult=deviceDao.addDevice(device);
			if (addResult!=1)
				return new CommonResult(-1,"注册设备失败！");
		}
		
		admin.setType("普通管理员");
		admin.setState("可用");
		admin.setRegisterTime(TimeUtil.getCurrentTime());
		int addResult=adminDao.addAdmin(admin);
		if (addResult!=1)
			return new CommonResult(-1,"新增管理员失败！",admin);
		
		return new CommonResult(0,"新增管理员成功！",admin);
	}

	@Override
	public Object getAdminByDeviceNumber(String deviceNumber) {
		Admin adminTemp=adminDao.getAdminByDeviceNumber(deviceNumber);
		if (adminTemp==null)
			return new CommonResult(-1,"获取管理员信息失败");
		return new CommonResult(0,"获取管理员信息成功！",adminTemp);
	}

	@Override
	public Object getAdminMsgs(String account, int page, int limit) {
		List<AdminMsg> tempMsgs=adminDao.getAdminMsgs(account);
		if (tempMsgs.isEmpty())
			return new TableData(-1,"你的系统消息为空！",0,null);
		if ((page-1)*limit>tempMsgs.size())
			return new TableData(-1,"数据库中无这么多数据");
		if (page*limit>tempMsgs.size())
			return new TableData(0,"通行记录获取成功",tempMsgs.size(),tempMsgs.subList((page-1)*limit,tempMsgs.size()));
		return new TableData(0,"通行记录获取成功",tempMsgs.size(),tempMsgs.subList((page-1)*limit,page*limit));
	}

	@Override
	public Object modifyAdminMsgStateByMsId(int id, String state) {
		if (adminDao.modifyAdminMsgStateByMsId(id,state)==1)
			return new CommonResult(0,"更改消息状态成功！",state);
		return new CommonResult(-1,"更改消息状态失败");
	}

	@Override
	public Object deleteAdminMsgByMsgId(int id) {
		if (adminDao.deleteAdminMsgByMsgId(id)==1)
			return new CommonResult(0,"删除成功！");
		return new CommonResult(-1,"删除失败");
	}
	
	@Override
	public Object getAdminMsgByDate(String account, int page, int limit, String leftDate, String rightDate) {
		List<AdminMsg> tempMsgs=adminDao.getAdminMsgByDate(account,leftDate,rightDate);
		if (tempMsgs.isEmpty())
			return new TableData(-1,"你的系统消息为空！",0,null);
		if ((page-1)*limit>tempMsgs.size())
			return new TableData(-1,"数据库中无这么多数据");
		if (page*limit>tempMsgs.size())
			return new TableData(0,"通行记录获取成功",tempMsgs.size(),tempMsgs.subList((page-1)*limit,tempMsgs.size()));
		return new TableData(0,"通行记录获取成功",tempMsgs.size(),tempMsgs.subList((page-1)*limit,page*limit));
	}
}
