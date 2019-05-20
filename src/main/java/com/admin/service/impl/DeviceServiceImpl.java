package com.admin.service.impl;

import com.admin.dao.DeviceDao;
import com.admin.service.DeviceService;
import com.admin.vo.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author darwin_he
 * @date 2019/5/20 15:55
 */
@Service
public class DeviceServiceImpl implements DeviceService {
	@Autowired
	private DeviceDao deviceDao;
	
	@Override
	public Object getDeviceCount() {
		int deviceCount=deviceDao.getDeviceCount();
		return new CommonResult(0,"获取总设备数成功！",deviceCount);
	}
	
}
