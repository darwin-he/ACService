package com.admin.dao;

import com.admin.model.EnviroData;
import com.admin.model.SystemMsg;
import com.admin.vo.Location;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author darwin_he
 * @date 2019/5/19 4:34
 */
@Mapper
@Component
public interface SystemDao {
	@Select({"select province,city,county,community from systeminfor where id = '1'"})
	Location getLocation();
	
	@Select({"select * from systemmsg where id = #{id}"})
	SystemMsg getSystemMsgByMsgId(int id);
}
