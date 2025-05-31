package com.library.controller;

import com.library.dao.ReaderTypeDAO;

import java.sql.SQLException;
import java.util.Vector;


// �������ͱ�Ŀ�����
public class ReaderTypeController {
    ReaderTypeDAO readerTypeDao = new ReaderTypeDAO();

    /**
     * ��ѯ�������ͱ��ȫ������
     */
    public Object[][] queryReaderType() throws SQLException {
        Object[][] data_readerType =  readerTypeDao.queryReaderType();
        return data_readerType;
    }

    /**
     * ��ѯ��������
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
     * ��ѯ�������͵�ID
     */
    public int queryReaderTypeID(String reader_type) throws SQLException {
        int rt_id=readerTypeDao.queryReaderTypeID(reader_type);
        return rt_id;
    }
    /**
     * ��ѯ����Ȩ��
     */
    public Vector<Vector<Object>> queryPersonalType(String count) throws SQLException {
        return readerTypeDao.queryPersonalType(count);
    }
    /**
     * ������������
     */
    public void insertReaderType(String rt_name,int maxCount,int maxDay) throws SQLException {
        readerTypeDao.insertReaderType(rt_name, maxCount, maxDay);
    }
    /**
     * ɾ����������
     */
    public void deleteReaderType(int rt_id) throws SQLException {
        readerTypeDao.deleteReaderType(rt_id);
    }
    /**
     * ���¶�������
     */
    public void updateReaderType(String readerType, int maxCount, int maxDay, int rt_id) throws SQLException {
        readerTypeDao.updateReaderType(readerType,maxCount ,maxDay,rt_id);
    }
}

