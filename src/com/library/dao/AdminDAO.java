package com.library.dao;


import java.sql.*;
import java.util.Vector;


// ����Ա��Ϣ�����ɾ�Ĳ�
public class AdminDAO {
    /**
     * ��ѯ����Ա���˺�����
     */
    public boolean queryAdmin(String count, String password) throws SQLException {
        String sql = "SELECT admin_count,admin_password FROM library.administrator WHERE admin_count=? AND admin_password=?";
        return DataBaseDAO.exist(sql, count, password);
    }

    /**
     * ��ѯָ������Ա����Ϣ��������Ա������Ϣ��
     */
    public Vector<Vector<Object>> queryAdmin(String count) throws SQLException {
        String sql = "SELECT admin_count,admin_name,admin_id_number,admin_contact,admin_email FROM library.administrator WHERE admin_count=? AND isSuper='0';";
        return DataBaseDAO.queryExact_public(sql, count);
    }

    /**
     * ��ѯȫ������Ա����Ϣ
     */
    public Vector<Vector<Object>> selectAdmin() throws SQLException {
        String sql = "SELECT admin_count,admin_name,admin_id_number,admin_contact,admin_email FROM library.administrator WHERE isSuper='0';";
        return DataBaseDAO.queryExact_public(sql);
    }

    /**
     * ɾ������Ա
     */
    public void deleteAdmin(String superNumber) throws SQLException {
        String sql = "DELETE FROM library.administrator WHERE admin_count=?";
        DataBaseDAO.update_public(sql, superNumber);
    }

    /**
     * �޸Ĺ���Ա��Ϣ
     */
    public void updateAdmin(String admin_contact, String admin_email, String superNumber) throws SQLException {
        String sql = "UPDATE library.administrator SET admin_contact=?,admin_email=? WHERE admin_count=?";
        DataBaseDAO.update_public(sql, admin_contact, admin_email, superNumber);
    }

    /**
     * ��������Ա
     */
    public void insertAdmin(String admin_count, String admin_name, String admin_id_number, String admin_contact, String admin_email,
                           String admin_keepPass, String admin_password) throws SQLException {
        String sql = "INSERT INTO library.administrator(admin_count,admin_name,admin_id_number,admin_contact,admin_email,admin_keepPass,admin_password,isSuper) VALUES(?,?,?,?,?,?,?,'0')";
        DataBaseDAO.update_public(sql, admin_count, admin_name, admin_id_number, admin_contact, admin_email, admin_keepPass, admin_password);
    }

    /**
     * �޸�����
     */
    public void updateAdminPass(String alterPass, String admin_count, String admin_password, String admin_keepPass)
            throws SQLException {
        String sql = "UPDATE library.administrator SET admin_password='" + alterPass
                + "' WHERE admin_count=? AND admin_password=? AND admin_keepPass=?";
        DataBaseDAO.update_public(sql, admin_count, admin_password, admin_keepPass);
    }

    /**
     * ��������Ա��֤
     */
    public boolean proveSuper(String count, String password) throws SQLException {
        String sql = "SELECT admin_count,admin_password FROM library.administrator WHERE admin_count=? AND admin_password=? AND isSuper='1'";
        return DataBaseDAO.exist(sql, count, password);
    }

}

