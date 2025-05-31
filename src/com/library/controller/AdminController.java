package com.library.controller;

import com.library.dao.AdminDAO;

import java.sql.SQLException;
import java.util.Vector;


// ����Ա��Ŀ�����
public class AdminController {
    AdminDAO adminDao = new AdminDAO();

    /**
     * ����Ա��¼
     */
    public boolean queryAdmin(String count, String password) throws SQLException {
        return adminDao.queryAdmin(count, password);
    }

    /**
     * ��ѯָ������Ա����Ϣ��������Ա������Ϣ��
     */
    public Vector<Vector<Object>> queryAdmin(String count) throws SQLException {
        return adminDao.queryAdmin(count);
    }

    /**
     * ��ѯȫ������Ա����Ϣ
     */
    public Vector<Vector<Object>> selectAdmin() throws SQLException {
        return adminDao.selectAdmin();
    }

    /**
     * ɾ������Ա
     */
    public void deleteAdmin(String superNumber) throws SQLException {
        adminDao.deleteAdmin(superNumber);
    }

    /**
     * �޸Ĺ���Ա��Ϣ
     */
    public void updateAdmin(String admin_contact, String admin_email, String superNumber) throws SQLException {
        adminDao.updateAdmin(admin_contact, admin_email, superNumber);
    }

    /**
     * ��������Ա
     */
    public void insertAdmin(String admin_count, String admin_name, String admin_id_number, String admin_contact, String admin_email,
                           String admin_keepPass, String admin_password) throws SQLException {
        adminDao.insertAdmin(admin_count, admin_name, admin_id_number, admin_contact, admin_email, admin_keepPass, admin_password);
    }

    /**
     * �޸�����
     */
    public void updateAdminPass(String alterPass, String admin_count, String admin_password, String admin_keepPass)
            throws SQLException {
        adminDao.updateAdminPass(alterPass, admin_count, admin_password, admin_keepPass);
    }

    /**
     * ��������Ա��֤
     */
    public boolean proveSuper(String count, String password) throws SQLException {
        return adminDao.proveSuper(count, password);
    }
}

