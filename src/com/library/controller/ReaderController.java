package com.library.controller;


import com.library.dao.ReaderDAO;

import java.sql.SQLException;
import java.util.Vector;


// 读者信息表的控制器
public class ReaderController {
    ReaderDAO readerDao = new ReaderDAO();

    /**
     * 注册时向reader表中添加信息
     */
    public void insertReader(String r_Card_number, String r_name, String gender, int reader_type, String dept,
                             String classes, String r_contact, String r_email, String keepPass, String r_password) throws SQLException {
        readerDao.insertReader(r_Card_number, r_name, gender, reader_type, dept, classes, r_contact, r_email, keepPass,
                r_password);
    }

    /**
     * 查询账号是否存在 用于用户注册和新增读者（保证账号唯一性）
     */
    public boolean isCard_number(String r_Card_number) throws SQLException {
        return readerDao.isCard_number(r_Card_number);
    }
    /**
     * 登录验证
     */
    public boolean queryReader(String r_card_number, String r_password) throws SQLException {
        boolean find = readerDao.queryReader(r_card_number, r_password);
        return find;
    }

    /**
     * 查询个人信息
     */
    public Vector<Vector<Object>> queryReaderInfo(String r_Card_number) throws SQLException {
        Vector<Vector<Object>> readerInfo = readerDao.queryReaderInfo(r_Card_number);
        return readerInfo;
    }

    /**
     * 修改读者信息
     */
    public void updateReader(String dept, String classes, String contact, String email, String Card_number)
            throws SQLException {
        readerDao.updateReader(dept, classes, contact, email, Card_number);
    }

    /**
     * 修改用户密码
     */
    public void updateReaderPass(String alterPass, String r_Card_number, String r_password, String r_keepPass)
            throws SQLException {
        readerDao.updateReaderPass(alterPass, r_Card_number, r_password, r_keepPass);
    }

    /**
     * 查询 全部读者
     */
    public Vector<Vector<Object>> selectReader() throws SQLException {
        return readerDao.selectReader();
    }

    /**
     * 查询读者所有信息
     */
    public Vector<Vector<Object>> queryReaderInfo(String Card_number, String name, String dept, String classes)
            throws SQLException {
        Vector<Vector<Object>> readerInfo = readerDao.queryReaderInfo(Card_number, name, dept, classes);
        return readerInfo;
    }

    /**
     * 查询读者的信息 模糊查寻 类型查询
     */
    public Vector<Vector<Object>> selectReaderInfo(String Card_number, String name, String dept, String classes,
                                                 String reader_type) throws SQLException {
        return readerDao.selectReaderInfo(Card_number, name, dept, classes, reader_type);
    }

    /**
     * 删除读者
     */
    public void dropReader(String studentCard_number) throws SQLException {
        readerDao.dropReader(studentCard_number);
    }

    /**
     * 忘记密码 密保验证
     */
    public boolean queryKeepPass(String forgetPass, String count) throws SQLException {
        return readerDao.queryKeepPass(forgetPass, count);
    }

    /**
     * 忘记密码后重置密码
     */
    public void resetPass(String forgetPass, String count, String newPass) throws SQLException {
        readerDao.resetPass(forgetPass, count, newPass);
    }

    /**
     * 查询是否有读者具备此读者类型
     * 删除类读者类型前，保证没有读者应用此读者类型
     */
    public boolean existReaderType(int rt_id) throws SQLException {
        return  readerDao.existReaderType(rt_id);
    }
}

