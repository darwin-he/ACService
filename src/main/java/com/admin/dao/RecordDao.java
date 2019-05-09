package com.admin.dao;

import com.admin.model.Record;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author darwin_he
 * @date 2019/5/2 3:32
 */
@Mapper
@Component
public interface RecordDao {
	
	@Insert({"insert into records (userCount,userName,deviceNumber,deviceName,state,time) " +
			         "values (#{userCount},#{userName},#{deviceNumber},#{deviceName},#{state},#{time})"})
	int addRecord(Record record);
	
	@Delete({"delete from records where passId = #{passId}"})
	int deleteRecordByPassId(@Param("passId")int passId);
	
	
	
	@Select({"select * from records"})
	List<Record> getRecords();
	
	@Select({"select * from records where userCount = #{userCount}"})
	List<Record> getRecordsByUserCount(@Param("userCount")String userCount);
	
	@Select({"select * from records where userName like concat(concat('%',#{userName}),'%')"})
	List<Record> getRecordsByUserName(@Param("userName")String userName);
	
	@Select({"select * from records where deviceNumber = #{deviceNumber}"})
	List<Record> getRecordsByDeviceNumber(@Param("deviceNumber")String deviceNumber);
	
	@Select({"select * from records where deviceName like concat(concat('%',#{deviceName}),'%')"})
	List<Record> getRecordsByDeviceName(@Param("deviceName")String deviceName);
	
	@Select({"select * from records where state = #{state}"})
	List<Record> getRecordsByState(@Param("state")char state);
	
	@Select({"select * from records where time between #{leftDate} and #{rightDate}"})
	List<Record> getRecordsByDate(@Param("leftDate")String leftDate,@Param("rightDate")String rightDate);
}
