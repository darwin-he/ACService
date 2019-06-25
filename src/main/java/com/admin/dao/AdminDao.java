package com.admin.dao;

import com.admin.model.Admin;
import com.admin.model.AdminMsg;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author darwin_he
 * @date 2019/5/6 21:26
 */
@Mapper
@Component
public interface AdminDao {
	@Update({"update admins set passWord = #{passWord} where account = #{account}"})
	int modifyAdminPassWord(String account,String passWord);
	
	@Insert({"insert into admins (account,name,passWord,deviceNumber,deviceName,deviceNikeName,type,state,registerTime) " +
			         "values (#{account},#{name},#{passWord},#{deviceNumber},#{deviceName},#{deviceNikeName},#{type},#{state},#{registerTime})"})
	int addAdmin(Admin admin);
	
	@Select({"select * from admins where account = #{account}"})
	Admin getAdminByAccount(String account);
	
	@Select({"select * from admins where deviceNumber = #{deviceNumber}"})
	Admin getAdminByDeviceNumber(String deviceNumber);
	
	@Select({"select * from adminmsg where adminAccount = #{account} order by createTime desc"})
	List<AdminMsg> getAdminMsgs(String account);
	
	@Update({"update adminmsg set state = #{state} where id = #{id}"})
	int modifyAdminMsgStateByMsId(int id,String state);
	
	@Delete({"delete from adminmsg where id = #{id}"})
	int deleteAdminMsgByMsgId(int id);
	
	@Select({"select * from (select * from adminmsg where adminAccount = #{account}) as a where createTime between #{leftDate} and #{rightDate}"})
	List<AdminMsg> getAdminMsgByDate(String account,String leftDate,String rightDate);
}
