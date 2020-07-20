package com.admin.service.impl;

import com.admin.dao.SystemDao;
import com.admin.model.SystemMsg;
import com.admin.service.SystemService;
import com.admin.vo.CommonResult;
import com.admin.vo.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author darwin_he
 * @date 2019/5/19 4:36
 */
@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    private SystemDao systemDao;

    @Override
    public Object getLocation() {
        Location location = systemDao.getLocation();
        if (location == null)
            return new CommonResult(-1, "获取设备区域信息失败！");
        return new CommonResult(0, "获取设备区域信息成功！", location);
    }

    @Override
    public Object getSystemMsgByMsgId(int id) {
        SystemMsg systemMsg = systemDao.getSystemMsgByMsgId(id);
        if (systemMsg != null)
            return new CommonResult(0, "获取系统消息成功！", systemMsg);
        return new CommonResult(-1, "获取系统消息失败！");
    }
}
