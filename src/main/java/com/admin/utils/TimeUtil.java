package com.admin.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author darwin_he
 * @date 2019/5/6 22:08
 */
public class TimeUtil {
	public static String getCurrentTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间
	}
	
	public static String getHourAndMinuteTime(String time){
		return time.substring(11,16);
	}
	
	public static List<String> getHourAndMinuteTime(List<String> timeList){
		for (int i=0;i<timeList.size();i++){
			String time=timeList.get(i);
			timeList.set(i,time.substring(11,19));
		}
		return timeList;
	}
}
