package com.admin.dao;

import com.admin.model.Admin;
import com.admin.vo.Location;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * @author darwin_he
 * @date 2019/5/6 21:26
 */
@Mapper
@Component
public interface AdminDao {
	@Update({"update admins set passWord = #{passWord} where account = #{account}"})
	int modifyAdminPassWord(String account,String passWord);
	
	@Insert({"insert into admins (account,name,passWord,deviceNumber,deviceName,type,state,registerTime) " +
			         "values (#{account},#{name},#{passWord},#{deviceNumber},#{deviceName},#{type},#{state},#{registerTime})"})
	int addAdmin(Admin admin);
	
	@Select({"select * from admins where account = #{account}"})
	Admin getAdminByAccount(String account);
	
	@Select({"select * from admins where deviceNumber = #{deviceNumber}"})
	Admin getAdminByDeviceNumber(String deviceNumber);
}
