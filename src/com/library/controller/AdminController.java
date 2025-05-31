package com.library.controller;

import com.library.dao.AdminDAO;

import java.sql.SQLException;
import java.util.Vector;


// 管理员表的控制器
public class AdminController {
    AdminDAO adminDao = new AdminDAO();

    /**
     * 管理员登录
     */
    public boolean queryAdmin(String count, String password) throws SQLException {
        return adminDao.queryAdmin(count, password);
    }

    /**
     * 查询指定管理员的信息（含管理员自身信息）
     */
    public Vector<Vector<Object>> queryAdmin(String count) throws SQLException {
        return adminDao.queryAdmin(count);
    }

    /**
     * 查询全部管理员的信息
     */
    public Vector<Vector<Object>> selectAdmin() throws SQLException {
        return adminDao.selectAdmin();
    }

    /**
     * 删除管理员
     */
    public void deleteAdmin(String superNumber) throws SQLException {
        adminDao.deleteAdmin(superNumber);
    }

    /**
     * 修改管理员信息
     */
    public void updateAdmin(String admin_contact, String admin_email, String superNumber) throws SQLException {
        adminDao.updateAdmin(admin_contact, admin_email, superNumber);
    }

    /**
     * 新增管理员
     */
    public void insertAdmin(String admin_count, String admin_name, String admin_id_number, String admin_contact, String admin_email,
                           String admin_keepPass, String admin_password) throws SQLException {
        adminDao.insertAdmin(admin_count, admin_name, admin_id_number, admin_contact, admin_email, admin_keepPass, admin_password);
    }

    /**
     * 修改密码
     */
    public void updateAdminPass(String alterPass, String admin_count, String admin_password, String admin_keepPass)
            throws SQLException {
        adminDao.updateAdminPass(alterPass, admin_count, admin_password, admin_keepPass);
    }

    /**
     * 超级管理员验证
     */
    public boolean proveSuper(String count, String password) throws SQLException {
        return adminDao.proveSuper(count, password);
    }
}

