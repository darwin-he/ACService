package com.admin.service.impl;

import com.admin.dao.RecordDao;
import com.admin.model.Record;
import com.admin.vo.TableData;
import com.admin.service.RecordService;
import com.admin.vo.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author darwin_he
 * @date 2019/5/7 23:19
 */
@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordDao recordDao;


    @Override
    public Object addRecord(Record record) {
        if (recordDao.addRecord(record) != 1)
            return new CommonResult(-1, "新增记录失败", record);
        return new CommonResult(0, "新增记录成功", record);
    }


    @Override
    public Object deleteRecordById(int id) {
        if (recordDao.deleteRecordById(id) != 1)
            return new CommonResult(-1, "删除记录失败", id);
        return new CommonResult(0, "删除记录成功", id);
    }

    @Override
    public Object getAllRecordCount() {
        int allRecordCount = recordDao.getAllRecordCount();
        return new CommonResult(0, "获取全部通行数成功！", allRecordCount);
    }

    @Override
    public Object getRecordCountByDeviceNumber(String deviceNumber) {
        int recordCount = recordDao.getRecordCountByDeviceNumber(deviceNumber);
        return new CommonResult(0, "获取通行记录数成功！", recordCount);
    }

    @Override
    public Object getRecords(int page, int limit) {
        List<Record> records = recordDao.getRecords();
        if (records.isEmpty())
            return new TableData(-1, "没有通行记录", 0, null);
        if ((page - 1) * limit > records.size())
            return new TableData(-1, "数据库中无这么多数据");
        if (page * limit > records.size())
            return new TableData(0, "通行记录获取成功", records.size(), records.subList((page - 1) * limit, records.size()));
        return new TableData(0, "通行记录获取成功", records.size(), records.subList((page - 1) * limit, page * limit));
    }

    @Override
    public Object getRecordsByUserAccount(String userAccount, int page, int limit) {
        List<Record> records = recordDao.getRecordsByUserAccount(userAccount);
        if (records.isEmpty())
            return new TableData(-1, "没有通行记录", 0, null);
        if ((page - 1) * limit > records.size())
            return new TableData(-1, "数据库中无这么多数据");
        if (page * limit > records.size())
            return new TableData(0, "通行记录获取成功", records.size(), records.subList((page - 1) * limit, records.size()));
        return new TableData(0, "通行记录获取成功", records.size(), records.subList((page - 1) * limit, page * limit));
    }

    @Override
    public Object getRecordsByUserName(String userName, int page, int limit) {
        List<Record> records = recordDao.getRecordsByUserName(userName);
        if (records.isEmpty())
            return new TableData(-1, "没有通行记录", 0, null);
        if ((page - 1) * limit > records.size())
            return new TableData(-1, "数据库中无这么多数据");
        if (page * limit > records.size())
            return new TableData(0, "通行记录获取成功", records.size(), records.subList((page - 1) * limit, records.size()));
        return new TableData(0, "通行记录获取成功", records.size(), records.subList((page - 1) * limit, page * limit));
    }

    @Override
    public Object getRecordsByDeviceNumber(String deviceNumber, int page, int limit) {
        List<Record> records = recordDao.getRecordsByDeviceNumber(deviceNumber);
        if (records.isEmpty())
            return new TableData(-1, "没有通行记录", 0, null);
        if ((page - 1) * limit > records.size())
            return new TableData(-1, "数据库中无这么多数据");
        if (page * limit > records.size())
            return new TableData(0, "通行记录获取成功", records.size(), records.subList((page - 1) * limit, records.size()));
        return new TableData(0, "通行记录获取成功", records.size(), records.subList((page - 1) * limit, page * limit));
    }

    @Override
    public Object getRecordsByDeviceNikeName(String deviceNikeName, int page, int limit) {
        List<Record> records = recordDao.getRecordsByDeviceNikeName(deviceNikeName);
        if (records.isEmpty())
            return new TableData(-1, "没有通行记录", 0, null);
        if ((page - 1) * limit > records.size())
            return new TableData(-1, "数据库中无这么多数据");
        if (page * limit > records.size())
            return new TableData(0, "通行记录获取成功", records.size(), records.subList((page - 1) * limit, records.size()));
        return new TableData(0, "通行记录获取成功", records.size(), records.subList((page - 1) * limit, page * limit));
    }

    @Override
    public Object getRecordsByState(String state, int page, int limit) {
        List<Record> records = recordDao.getRecordsByState(state);
        if (records.isEmpty())
            return new TableData(-1, "没有通行记录", 0, null);
        if ((page - 1) * limit > records.size())
            return new TableData(-1, "数据库中无这么多数据");
        if (page * limit > records.size())
            return new TableData(0, "通行记录获取成功", records.size(), records.subList((page - 1) * limit, records.size()));
        return new TableData(0, "通行记录获取成功", records.size(), records.subList((page - 1) * limit, page * limit));
    }

    @Override
    public Object getRecordsByDate(String leftDate, String rightDate, int page, int limit) {
        List<Record> records = recordDao.getRecordsByDate(leftDate, rightDate);
        if (records.isEmpty())
            return new TableData(-1, "没有通行记录", 0, null);
        if ((page - 1) * limit > records.size())
            return new TableData(-1, "数据库中无这么多数据");
        if (page * limit > records.size())
            return new TableData(0, "通行记录获取成功", records.size(), records.subList((page - 1) * limit, records.size()));
        return new TableData(0, "通行记录获取成功", records.size(), records.subList((page - 1) * limit, page * limit));
    }

}
