package com.admin.service.impl;

import com.admin.dao.UserDao;
import com.admin.model.User;
import com.admin.ov.Location;
import com.admin.ov.TableData;
import com.admin.service.UserInfService;
import com.admin.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author darwin_he
 * @date 2019/5/2 17:13
 */
@Service
public class UserInfServiceImpl implements UserInfService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public Object getLocation() {
		CommonResult result;
		Location location=userDao.getLocation();
		if (location==null){
			result=new CommonResult(-1,"设备归属信息获取失败！");
			return result;
		}
		result=new CommonResult(0,"用户归属获取成功",location);
		return result;
	}

	@Override
	public Object addUser(User user) {
		User userTemp=userDao.getUserByUserCount(user.getUserCount());
		if (userTemp!=null)
			return new CommonResult(-1,"账户已存在！",userTemp);
		
		userTemp=userDao.getUserByUserCard(user.getUserCard());
		if (userTemp!=null)
			return new CommonResult(-1,"卡片已被注册！",userTemp);
		
		int addResult=userDao.addUser(user);
		if (addResult!=1)
			return new CommonResult(-1,"新增用户失败！",user);
		
		User addedUser=userDao.getUserByUserCount(user.getUserCount());
		return new CommonResult(0,"新增用户成功",addedUser);
	}
	
	@Override
	public Object deleteUserByUserId(int userId) {
		User userTemp=userDao.getUserByUserId(userId);
		if (userTemp==null)
			return new CommonResult(-1,"用户不存在！");
		
		int deleteResult=userDao.deleteUserByUserId(userId);
		if (deleteResult==1)
			return new CommonResult(0,"删除成功!",userTemp);
		else
			return new CommonResult(-1,"删除失败！",userTemp);
	}
	
	@Override
	public Object deleteUserByUserCard(String userCard) {
		User userTemp=userDao.getUserByUserCard(userCard);
		if (userTemp==null)
			return new CommonResult(-1,"用户不存在！");
		
		int deleteResult=userDao.deleteUserByUserCard(userCard);
		if (deleteResult==1)
			return new CommonResult(0,"删除成功!",userTemp);
		else
			return new CommonResult(-1,"删除失败！",userTemp);
	}
	
	@Override
	public Object deleteUserByUserCount(String userCount) {
		User userTemp=userDao.getUserByUserCount(userCount);
		if (userTemp==null)
			return new CommonResult(-1,"用户不存在！");
		
		int deleteResult=userDao.deleteUserByUserCount(userCount);
		if (deleteResult==1)
			return new CommonResult(0,"删除成功!",userTemp);
		else
			return new CommonResult(-1,"删除失败！",userTemp);
	}

	@Override
	public Object getUser(int page,int limit) {
		List<User> users=userDao.getUser();
		if (users.isEmpty())
			return new TableData(-1,"获取用户失败");
		if ((page-1)*limit>users.size())
			return new TableData(-1,"数据库中无这么多数据");
		if (page*limit>users.size())
			return new TableData(0,"获取用户成功",users.size(),users.subList((page-1)*limit,users.size()));
		return new TableData(0,"获取用户成功",users.size(),users.subList((page-1)*limit,page*limit));
	}

@Override
	public Object getUserByUserCard(String userCard) {
		User user=userDao.getUserByUserCard(userCard);
		if (user==null)
			return new CommonResult(-1,"用户不存在！");
		return new CommonResult(0,"查找用户成功",user);
	}
	
	@Override
	public Object getUserByUserId(int userId) {
		User user=userDao.getUserByUserId(userId);
		if (user==null)
			return new CommonResult(-1,"用户不存在！");
		return new CommonResult(0,"查找用户成功",user);
	}
	
	@Override
	public Object getUserByUserCount(String userCount) {
		User user=userDao.getUserByUserCount(userCount);
		if (user==null)
			return new CommonResult(-1,"用户不存在！");
		return new CommonResult(0,"查找用户成功",user);
	}
	
	@Override
	public Object getUsersByUserName(String userName,int page,int limit) {
		List<User> users=userDao.getUsersByUserName(userName);
		if (users.isEmpty())
			return new TableData(-1,"获取用户失败");
		if ((page-1)*limit>users.size())
			return new TableData(-1,"数据库中无这么多数据");
		if (page*limit>users.size())
			return new TableData(0,"获取用户成功",users.size(),users.subList((page-1)*limit,users.size()));
		return new TableData(0,"获取用户成功",users.size(),users.subList((page-1)*limit,page*limit));
	}
	
	@Override
	public Object getUsersByDate(String leftDate, String rightDate,int page,int limit) {
		List<User> users=userDao.getUsersByDate(leftDate,rightDate);
		if (users.isEmpty())
			return new TableData(-1,"获取用户失败");
		if ((page-1)*limit>users.size())
			return new TableData(-1,"数据库中无这么多数据");
		if (page*limit>users.size())
			return new TableData(0,"获取用户成功",users.size(),users.subList((page-1)*limit,users.size()));
		return new TableData(0,"获取用户成功",users.size(),users.subList((page-1)*limit,page*limit));
	}

	@Override
	public Object getUsersByLocation(String province, String city, String county, String community,int page,int limit) {
		List<User> users=userDao.getUsersByLocation(province,city,county,community);
		if (users.isEmpty())
			return new TableData(-1,"获取用户失败");
		if ((page-1)*limit>users.size())
			return new TableData(-1,"数据库中无这么多数据");
		if (page*limit>users.size())
			return new TableData(0,"获取用户成功",users.size(),users.subList((page-1)*limit,users.size()));
		return new TableData(0,"获取用户成功",users.size(),users.subList((page-1)*limit,page*limit));
	}

	@Override
	public Object modifyUser(User user) {
		User temp=userDao.getUserByUserId(user.getUserId());
		if (temp==null)
			return new CommonResult(-1,"用户不存在！",user);
		int modifyResult=userDao.modifyUser(user);
		if (modifyResult!=1)
			return new CommonResult(-1,"修改用户信息失败！",user);
		return new CommonResult(0,"信息修改成功！",user);
	}

	@Override
	public Object modifyUserPassWord(int userId, String passWord) {
		User user=userDao.getUserByUserId(userId);
		if (user==null)
			return new CommonResult(-1,"用户不存在！",userId);
		int modifyResult=userDao.modifyUserPassWord(userId,passWord);
		if (modifyResult!=1)
			return new CommonResult(-1,"重置密码失败！",passWord);
		return new CommonResult(0,"密码重置成功！",passWord);
	}
	
	@Override
	public Object modifyUserCard(int userId, String userCard) {
		User user=userDao.getUserByUserId(userId);
		if (user==null)
			return new CommonResult(-1,"用户不存在！",userId);
		user=userDao.getUserByUserCard(userCard);
		if (user!=null)
			return new CommonResult(-1,"卡号已被其他用户注册！",userCard);
		
		int modifyResult=userDao.modifyUserCard(userId,userCard);
		if (modifyResult!=1)
			return new CommonResult(-1,"重置卡号失败！",userCard);
		return new CommonResult(0,"卡号重置成功！",userCard);
	}
	
	@Override
	public Object modifyUserName(int userId, String userName) {
		User user=userDao.getUserByUserId(userId);
		if (user==null)
			return new CommonResult(-1,"用户不存在！",userId);
		int modifyResult=userDao.modifyUserName(userId,userName);
		if (modifyResult!=1)
			return new CommonResult(-1,"更改用户名失败！",userName);
		return new CommonResult(0,"更改用户名成功！",userName);
	}
	
	@Override
	public Object modifyUserState(int userId, char state) {
		User user=userDao.getUserByUserId(userId);
		if (user==null)
			return new CommonResult(-1,"用户不存在！",userId);
		int modifyResult=userDao.modifyUserState(userId,state);
		if (modifyResult!=1)
			return new CommonResult(-1,"用户状态更改失败",state);
		return new CommonResult(0,"用户状态更改成功！",state);
	}
	
	@Override
	public Object modifyUserCount(int userId, String userCount) {
		User user=userDao.getUserByUserId(userId);
		if (user==null)
			return new CommonResult(-1,"用户不存在！",userId);
		int modifyResult=userDao.modifyUserCount(userId,userCount);
		if (modifyResult!=1)
			return new CommonResult(-1,"更改用户账户失败！",userCount);
		return new CommonResult(0,"用户账户更改成功！",userCount);
	}
	
	@Override
	public Object modifyUserLocation(int userId, String province, String city, String county, String community) {
		User user=userDao.getUserByUserId(userId);
		if (user==null)
			return new CommonResult(-1,"用户不存在！",userId);
		int modifyResult=userDao.modifyUserLocation(userId,province,city,county,community);
		if (modifyResult!=1)
			return new CommonResult(-1,"用户地理位置更改失败！",province+city+county+community);
		return new CommonResult(0,"用户地理位置更改成功！",province+city+county+community);
	}
	
}
