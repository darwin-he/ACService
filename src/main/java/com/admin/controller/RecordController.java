package com.admin.controller;

import com.admin.model.Record;
import com.admin.service.RecordService;
import com.sun.org.apache.regexp.internal.RE;
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
	@RequestMapping(value = "/deleteRecordByUserAccount",method = RequestMethod.GET)
	public Object deleteRecordByUserCount(@RequestParam(value = "userAccount")String userAccount){
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteRecordByDeviceNumber",method = RequestMethod.GET)
	public Object deleteRecordByDeviceNumber(@RequestParam(value = "deviceNumber")String deviceNumber){
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteRecordById",method = RequestMethod.GET)
	public Object deleteRecordByPassId(@RequestParam(value = "id")int id){
		return recordService.deleteRecordById(id);
	}
	
	/*************************************查询记录************************************/
	@ResponseBody
	@RequestMapping(value = "/getAllRecordCount",method = RequestMethod.GET)
	public Object getAllRecordCount(){
		return recordService.getAllRecordCount();
	}
	
	@ResponseBody
	@RequestMapping(value = "/getRecordCountByDeviceNumber",method = RequestMethod.GET)
	public Object getRecordCountByDeviceNumber(@RequestParam("deviceNumber")String deviceNumber){
		return recordService.getRecordCountByDeviceNumber(deviceNumber);
	}
	
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
	@RequestMapping(value = "/getRecordsByUserAccount",method = RequestMethod.GET)
	public Object getRecordsByUserCount(@RequestParam(value = "userAccount")String userAccount,
	                                    @RequestParam(value = "page")int page,@RequestParam(value = "limit")int limit){
		return recordService.getRecordsByUserAccount(userAccount,page,limit);
	}
	@ResponseBody
	@RequestMapping(value = "/getRecordsByDeviceNumber",method = RequestMethod.GET)
	public Object getRecordsByDeviceNumber(@RequestParam(value = "deviceNumber")String deviceNumber,
	                                       @RequestParam(value = "page")int page,@RequestParam(value = "limit")int limit){
		return recordService.getRecordsByDeviceNumber(deviceNumber,page,limit);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getRecordsByDeviceNikeName",method = RequestMethod.GET)
	public Object getRecordsByDeviceName(@RequestParam(value = "deviceNikeName")String deviceNikeName,
	                                     @RequestParam(value = "page")int page,@RequestParam(value = "limit")int limit){
		return recordService.getRecordsByDeviceNikeName(deviceNikeName,page,limit);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getRecordsByState",method = RequestMethod.GET)
	public Object getRecordsByState(@RequestParam(value = "state")String state,
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
