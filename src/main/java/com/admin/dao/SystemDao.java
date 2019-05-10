package com.admin.dao;

import com.admin.model.Admin;
import com.admin.ov.Location;
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
public interface SystemDao {
	
	@Select({"select province,city,county,community from admins where adminCount = '00000000000'"})
	Location getLocation();
	
	@Update({"update admins set passWord = #{passWord} where adminCount = #{adminCount}"})
	int modifyAdminPassWord(String adminCount,String passWord);
	
	@Insert({"insert into admins (adminCount,adminName,passWord,deviceNumber,province,city,county,community,state,registerTime) " +
			         "values (#{adminCount},#{adminName},#{passWord},#{deviceNumber},#{province},#{city},#{county},#{community},#{state},#{registerTime})"})
	int addAdmin(Admin admin);
	
	@Select({"select * from admins where adminCount = #{adminCount}"})
	Admin getAdminByCount(String adminCount);
	
	@Select({"select * from admins where deviceNumber = #{deviceNumber}"})
	Admin getAdminByDeviceNumber(String deviceNumber);
}
