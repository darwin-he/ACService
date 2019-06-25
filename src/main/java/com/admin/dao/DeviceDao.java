package com.admin.dao;

import com.admin.model.Device;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author darwin_he
 * @date 2019/5/20 15:54
 */
@Mapper
@Component
public interface DeviceDao {
	@Select({"select count(*) from devices"})
	int getDeviceCount();
	
	@Select({"select * from devices where deviceNumber = #{deviceNumber}"})
	Device getDeviceByDeviceNumber(@Param("deviceNumber") String deviceNumber);
	
	@Insert({"insert into devices (deviceNumber,deviceName,deviceNikeName,state,registerTime) " +
			         "values (#{deviceNumber},#{deviceName},#{deviceNikeName},#{state},#{registerTime})"})
	int addDevice(Device device);
}
