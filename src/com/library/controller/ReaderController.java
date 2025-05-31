package com.library.controller;


import com.library.dao.ReaderDAO;

import java.sql.SQLException;
import java.util.Vector;


// ������Ϣ��Ŀ�����
public class ReaderController {
    ReaderDAO readerDao = new ReaderDAO();

    /**
     * ע��ʱ��reader���������Ϣ
     */
    public void insertReader(String r_Card_number, String r_name, String gender, int reader_type, String dept,
                             String classes, String r_contact, String r_email, String keepPass, String r_password) throws SQLException {
        readerDao.insertReader(r_Card_number, r_name, gender, reader_type, dept, classes, r_contact, r_email, keepPass,
                r_password);
    }

    /**
     * ��ѯ�˺��Ƿ���� �����û�ע����������ߣ���֤�˺�Ψһ�ԣ�
     */
    public boolean isCard_number(String r_Card_number) throws SQLException {
        return readerDao.isCard_number(r_Card_number);
    }
    /**
     * ��¼��֤
     */
    public boolean queryReader(String r_card_number, String r_password) throws SQLException {
        boolean find = readerDao.queryReader(r_card_number, r_password);
        return find;
    }

    /**
     * ��ѯ������Ϣ
     */
    public Vector<Vector<Object>> queryReaderInfo(String r_Card_number) throws SQLException {
        Vector<Vector<Object>> readerInfo = readerDao.queryReaderInfo(r_Card_number);
        return readerInfo;
    }

    /**
     * �޸Ķ�����Ϣ
     */
    public void updateReader(String dept, String classes, String contact, String email, String Card_number)
            throws SQLException {
        readerDao.updateReader(dept, classes, contact, email, Card_number);
    }

    /**
     * �޸��û�����
     */
    public void updateReaderPass(String alterPass, String r_Card_number, String r_password, String r_keepPass)
            throws SQLException {
        readerDao.updateReaderPass(alterPass, r_Card_number, r_password, r_keepPass);
    }

    /**
     * ��ѯ ȫ������
     */
    public Vector<Vector<Object>> selectReader() throws SQLException {
        return readerDao.selectReader();
    }

    /**
     * ��ѯ����������Ϣ
     */
    public Vector<Vector<Object>> queryReaderInfo(String Card_number, String name, String dept, String classes)
            throws SQLException {
        Vector<Vector<Object>> readerInfo = readerDao.queryReaderInfo(Card_number, name, dept, classes);
        return readerInfo;
    }

    /**
     * ��ѯ���ߵ���Ϣ ģ����Ѱ ���Ͳ�ѯ
     */
    public Vector<Vector<Object>> selectReaderInfo(String Card_number, String name, String dept, String classes,
                                                 String reader_type) throws SQLException {
        return readerDao.selectReaderInfo(Card_number, name, dept, classes, reader_type);
    }

    /**
     * ɾ������
     */
    public void dropReader(String studentCard_number) throws SQLException {
        readerDao.dropReader(studentCard_number);
    }

    /**
     * �������� �ܱ���֤
     */
    public boolean queryKeepPass(String forgetPass, String count) throws SQLException {
        return readerDao.queryKeepPass(forgetPass, count);
    }

    /**
     * �����������������
     */
    public void resetPass(String forgetPass, String count, String newPass) throws SQLException {
        readerDao.resetPass(forgetPass, count, newPass);
    }

    /**
     * ��ѯ�Ƿ��ж��߾߱��˶�������
     * ɾ�����������ǰ����֤û�ж���Ӧ�ô˶�������
     */
    public boolean existReaderType(int rt_id) throws SQLException {
        return  readerDao.existReaderType(rt_id);
    }
}

