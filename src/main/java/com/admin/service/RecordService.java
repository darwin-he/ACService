package com.admin.service;

import com.admin.model.Record;

import java.util.List;

/**
 * @author darwin_he
 * @date 2019/5/7 23:15
 */
public interface RecordService {
	
	Object addRecord(Record record);
	
	Object deleteRecordById(int id);
	
	Object getAllRecordCount();
	
	Object getRecordCountByDeviceNumber(String deviceNumber);
	
	Object getRecords(int page,int limit);
	
	Object getRecordsByUserAccount(String userAccount,int page,int limit);
	
	Object getRecordsByUserName(String userName,int page,int limit);
	
	Object getRecordsByDeviceNumber(String deviceNumber,int page,int limit);

	Object getRecordsByDeviceName(String deviceName,int page,int limit);
	
	Object getRecordsByState(String state,int page,int limit);
	
	Object getRecordsByDate(String leftDate,String rightDate,int page,int limit);
}
