package com.admin.controller;

import com.admin.model.Record;
import com.admin.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author darwin_he
 * @date 2019/5/2 3:02
 */
@Controller
public class RecordController {
	@Autowired
	private RecordService recordService;
	
	/*************************************新增记录************************************/
	@ResponseBody
	@RequestMapping(value = "/addRecord",method = RequestMethod.POST)
	public Object addRecord(@RequestBody Record record){
		return recordService.addRecord(record);
	}
	
	/*************************************删除记录************************************/
	@ResponseBody
	@RequestMapping(value = "/deleteRecordByUserCount",method = RequestMethod.GET)
	public Object deleteRecordByUserCount(@RequestParam(value = "userCount")String userCount){
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteRecordByDeviceNumber",method = RequestMethod.GET)
	public Object deleteRecordByDeviceNumber(@RequestParam(value = "deviceNumber")String deviceNumber){
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteRecordByPassId",method = RequestMethod.GET)
	public Object deleteRecordByPassId(@RequestParam(value = "passId")int passId){
		return recordService.deleteRecordByPassId(passId);
	}
	
	/*************************************查询记录************************************/
	@ResponseBody
	@RequestMapping(value = "/getRecords",method = RequestMethod.GET)
	public Object getRecords(@RequestParam(value = "page")int page,@RequestParam(value = "limit")int limit){
		return recordService.getRecords(page,limit);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getRecordsByUserName",method = RequestMethod.GET)
	public Object getRecordsByUserName(@RequestParam(value = "userName")String userName,
	                                   @RequestParam(value = "page")int page,@RequestParam(value = "limit")int limit){
		return recordService.getRecordsByUserName(userName,page,limit);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getRecordsByUserCount",method = RequestMethod.GET)
	public Object getRecordsByUserCount(@RequestParam(value = "userCount")String userCount,
	                                    @RequestParam(value = "page")int page,@RequestParam(value = "limit")int limit){
		return recordService.getRecordsByUserCount(userCount,page,limit);
	}
	@ResponseBody
	@RequestMapping(value = "/getRecordsByDeviceNumber",method = RequestMethod.GET)
	public Object getRecordsByDeviceNumber(@RequestParam(value = "deviceNumber")String deviceNumber,
	                                       @RequestParam(value = "page")int page,@RequestParam(value = "limit")int limit){
		return recordService.getRecordsByDeviceNumber(deviceNumber,page,limit);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getRecordsByDeviceName",method = RequestMethod.GET)
	public Object getRecordsByDeviceName(@RequestParam(value = "deviceName")String deviceName,
	                                     @RequestParam(value = "page")int page,@RequestParam(value = "limit")int limit){
		return recordService.getRecordsByDeviceName(deviceName,page,limit);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getRecordsByState",method = RequestMethod.GET)
	public Object getRecordsByState(@RequestParam(value = "state")char state,
	                                @RequestParam(value = "page")int page,@RequestParam(value = "limit")int limit){
		return recordService.getRecordsByState(state,page,limit);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getRecordsByDate",method = RequestMethod.GET)
	public Object getRecordsByDate(@RequestParam(value = "leftDate")String leftDate,@RequestParam(value = "rightDate")String rightDate,
	                               @RequestParam(value = "page")int page,@RequestParam(value = "limit")int limit){
		return recordService.getRecordsByDate(leftDate,rightDate,page,limit);
	}
}
