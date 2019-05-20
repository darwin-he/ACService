package com.admin.dao;

import org.apache.ibatis.annotations.Mapper;
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
}
