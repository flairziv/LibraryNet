package com.library.controller;

import com.library.dao.ReaderTypeDAO;

import java.sql.SQLException;
import java.util.Vector;


// 读者类型表的控制器
public class ReaderTypeController {
    ReaderTypeDAO readerTypeDao = new ReaderTypeDAO();

    /**
     * 查询读者类型表的全部数据
     */
    public Object[][] queryReaderType() throws SQLException {
        Object[][] data_readerType =  readerTypeDao.queryReaderType();
        return data_readerType;
    }

    /**
     * 查询读者类型
     */
    public String[] getReaderType() throws SQLException {
        Object[][] data_readerType = readerTypeDao.queryReaderType();
        String[] readerType = new String[data_readerType.length];
        for (int i = 0; i < data_readerType.length; i++) {
            readerType[i] = data_readerType[i][1].toString();
        }
        return readerType;
    }
    /**
     * 查询读者类型的ID
     */
    public int queryReaderTypeID(String reader_type) throws SQLException {
        int rt_id=readerTypeDao.queryReaderTypeID(reader_type);
        return rt_id;
    }
    /**
     * 查询个人权限
     */
    public Vector<Vector<Object>> queryPersonalType(String count) throws SQLException {
        return readerTypeDao.queryPersonalType(count);
    }
    /**
     * 新增读者类型
     */
    public void insertReaderType(String rt_name,int maxCount,int maxDay) throws SQLException {
        readerTypeDao.insertReaderType(rt_name, maxCount, maxDay);
    }
    /**
     * 删除读者类型
     */
    public void deleteReaderType(int rt_id) throws SQLException {
        readerTypeDao.deleteReaderType(rt_id);
    }
    /**
     * 更新读者类型
     */
    public void updateReaderType(String readerType, int maxCount, int maxDay, int rt_id) throws SQLException {
        readerTypeDao.updateReaderType(readerType,maxCount ,maxDay,rt_id);
    }
}

