package com.admin.dao;

import com.admin.model.EnviroData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author darwin_he
 * @date 2019/5/20 16:00
 */
@Mapper
@Component
public interface EnviroDao {
@Select({"select * from (select * from envirodata where deviceNumber = #{deviceNumber}) as a where time between #{startTime} and #{endTime}"})
List<EnviroData> getEnviroDataByDeviceNumberInPeriod(@Param("deviceNumber") String deviceNumber,
                                                     @Param("startTime") String startTime, @Param("endTime")String endTime);

@Select({"select *,max(time) from envirodata where deviceNumber = #{deviceNumber}"})
EnviroData getCurrentEnviroDataByDeviceNumber(@Param("deviceNumber") String deviceNumber);
}
