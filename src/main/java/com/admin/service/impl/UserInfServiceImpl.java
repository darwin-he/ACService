package com.admin.service.impl;

import com.admin.dao.UserDao;
import com.admin.model.User;
import com.admin.service.UserInfService;
import com.admin.utils.TimeUtil;
import com.admin.vo.CommonResult;
import com.admin.vo.TableData;
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
	public Object addUser(User user) {
		User userTemp=userDao.getUserByAccount(user.getAccount());
		if (userTemp!=null)
			return new CommonResult(-1,"账户已存在！",userTemp);
		
		userTemp=userDao.getUserByCardNumber(user.getCardNumber());
		if (userTemp!=null)
			return new CommonResult(-1,"卡片已被注册！",userTemp);
		
		user.setState("可用");
		user.setRegisterTime(TimeUtil.getCurrentTime());
		int addResult=userDao.addUser(user);
		if (addResult!=1)
			return new CommonResult(-1,"新增用户失败！",user);
		
		User addedUser=userDao.getUserByAccount(user.getAccount());
		return new CommonResult(0,"新增用户成功",addedUser);
	}
	
	@Override
	public Object deleteUserById(int id) {
		User userTemp=userDao.getUserById(id);
		if (userTemp==null)
			return new CommonResult(-1,"用户不存在！");
		
		int deleteResult=userDao.deleteUserById(id);
		if (deleteResult==1)
			return new CommonResult(0,"删除成功!",userTemp);
		else
			return new CommonResult(-1,"删除失败！",userTemp);
	}
	
	@Override
	public Object deleteUserByCardNumber(String cardNumber) {
		User userTemp=userDao.getUserByCardNumber(cardNumber);
		if (userTemp==null)
			return new CommonResult(-1,"用户不存在！");
		
		int deleteResult=userDao.deleteUserByCardNumber(cardNumber);
		if (deleteResult==1)
			return new CommonResult(0,"删除成功!",userTemp);
		else
			return new CommonResult(-1,"删除失败！",userTemp);
	}
	
	@Override
	public Object deleteUserByAccount(String account) {
		User userTemp=userDao.getUserByAccount(account);
		if (userTemp==null)
			return new CommonResult(-1,"用户不存在！");
		
		int deleteResult=userDao.deleteUserByAccount(account);
		if (deleteResult==1)
			return new CommonResult(0,"删除成功!",userTemp);
		else
			return new CommonResult(-1,"删除失败！",userTemp);
	}

/*************************************获取用户************************************/
	@Override
	public Object getUserCount() {
		int userCount=userDao.getUserCount();
		return new CommonResult(0,"获取用户数成功！",userCount);
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
	public Object getUserByCardNumber(String cardNumber) {
		User user=userDao.getUserByCardNumber(cardNumber);
		if (user==null)
			return new CommonResult(-1,"用户不存在！");
		return new CommonResult(0,"查找用户成功",user);
	}
	
	@Override
	public Object getUserById(int id) {
		User user=userDao.getUserById(id);
		if (user==null)
			return new CommonResult(-1,"用户不存在！");
		return new CommonResult(0,"查找用户成功",user);
	}
	
	@Override
	public Object getUserByAccount(String account) {
		User user=userDao.getUserByAccount(account);
		if (user==null)
			return new CommonResult(-1,"用户不存在！");
		return new CommonResult(0,"查找用户成功",user);
	}
	
	@Override
	public Object getUsersByName(String name,int page,int limit) {
		List<User> users=userDao.getUsersByName(name);
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
	public Object modifyUserPassWord(int id, String passWord) {
		User user=userDao.getUserById(id);
		if (user==null)
			return new CommonResult(-1,"用户不存在！",id);
		int modifyResult=userDao.modifyUserPassWord(id,passWord);
		if (modifyResult!=1)
			return new CommonResult(-1,"重置密码失败！",passWord);
		return new CommonResult(0,"密码重置成功！",passWord);
	}
	
	@Override
	public Object modifyUserCardNumber(int id, String cardNumber) {
		User user=userDao.getUserById(id);
		if (user==null)
			return new CommonResult(-1,"用户不存在！",id);
		user=userDao.getUserByCardNumber(cardNumber);
		if (user!=null)
			return new CommonResult(-1,"卡号已被其他用户注册！",cardNumber);
		
		int modifyResult=userDao.modifyUserCardNumber(id,cardNumber);
		if (modifyResult!=1)
			return new CommonResult(-1,"重置卡号失败！",cardNumber);
		return new CommonResult(0,"卡号重置成功！",cardNumber);
	}
	
	@Override
	public Object modifyUserName(int id, String userName) {
		User user=userDao.getUserById(id);
		if (user==null)
			return new CommonResult(-1,"用户不存在！",id);
		int modifyResult=userDao.modifyUserName(id,userName);
		if (modifyResult!=1)
			return new CommonResult(-1,"更改用户名失败！",userName);
		return new CommonResult(0,"更改用户名成功！",userName);
	}
	
	@Override
	public Object modifyUserState(int id, String state) {
		User user=userDao.getUserById(id);
		if (user==null)
			return new CommonResult(-1,"用户不存在！",id);
		int modifyResult=userDao.modifyUserState(id,state);
		if (modifyResult!=1)
			return new CommonResult(-1,"用户状态更改失败",state);
		return new CommonResult(0,"用户状态更改成功！",state);
	}
	
	@Override
	public Object modifyUserAccount(int id, String account) {
		User user=userDao.getUserById(id);
		if (user==null)
			return new CommonResult(-1,"用户不存在！",id);
		int modifyResult=userDao.modifyUserAccount(id,account);
		if (modifyResult!=1)
			return new CommonResult(-1,"更改用户账户失败！",account);
		return new CommonResult(0,"用户账户更改成功！",account);
	}
	
}
