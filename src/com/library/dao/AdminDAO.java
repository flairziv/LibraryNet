package com.library.dao;


import java.sql.*;
import java.util.Vector;


// 管理员信息表的增删改查
public class AdminDAO {
    /**
     * 查询管理员的账号密码
     */
    public boolean queryAdmin(String count, String password) throws SQLException {
        String sql = "SELECT admin_count,admin_password FROM library.administrator WHERE admin_count=? AND admin_password=?";
        return DataBaseDAO.exist(sql, count, password);
    }

    /**
     * 查询指定管理员的信息（含管理员自身信息）
     */
    public Vector<Vector<Object>> queryAdmin(String count) throws SQLException {
        String sql = "SELECT admin_count,admin_name,admin_id_number,admin_contact,admin_email FROM library.administrator WHERE admin_count=? AND isSuper='0';";
        return DataBaseDAO.queryExact_public(sql, count);
    }

    /**
     * 查询全部管理员的信息
     */
    public Vector<Vector<Object>> selectAdmin() throws SQLException {
        String sql = "SELECT admin_count,admin_name,admin_id_number,admin_contact,admin_email FROM library.administrator WHERE isSuper='0';";
        return DataBaseDAO.queryExact_public(sql);
    }

    /**
     * 删除管理员
     */
    public void deleteAdmin(String superNumber) throws SQLException {
        String sql = "DELETE FROM library.administrator WHERE admin_count=?";
        DataBaseDAO.update_public(sql, superNumber);
    }

    /**
     * 修改管理员信息
     */
    public void updateAdmin(String admin_contact, String admin_email, String superNumber) throws SQLException {
        String sql = "UPDATE library.administrator SET admin_contact=?,admin_email=? WHERE admin_count=?";
        DataBaseDAO.update_public(sql, admin_contact, admin_email, superNumber);
    }

    /**
     * 新增管理员
     */
    public void insertAdmin(String admin_count, String admin_name, String admin_id_number, String admin_contact, String admin_email,
                           String admin_keepPass, String admin_password) throws SQLException {
        String sql = "INSERT INTO library.administrator(admin_count,admin_name,admin_id_number,admin_contact,admin_email,admin_keepPass,admin_password,isSuper) VALUES(?,?,?,?,?,?,?,'0')";
        DataBaseDAO.update_public(sql, admin_count, admin_name, admin_id_number, admin_contact, admin_email, admin_keepPass, admin_password);
    }

    /**
     * 修改密码
     */
    public void updateAdminPass(String alterPass, String admin_count, String admin_password, String admin_keepPass)
            throws SQLException {
        String sql = "UPDATE library.administrator SET admin_password='" + alterPass
                + "' WHERE admin_count=? AND admin_password=? AND admin_keepPass=?";
        DataBaseDAO.update_public(sql, admin_count, admin_password, admin_keepPass);
    }

    /**
     * 超级管理员验证
     */
    public boolean proveSuper(String count, String password) throws SQLException {
        String sql = "SELECT admin_count,admin_password FROM library.administrator WHERE admin_count=? AND admin_password=? AND isSuper='1'";
        return DataBaseDAO.exist(sql, count, password);
    }

}

