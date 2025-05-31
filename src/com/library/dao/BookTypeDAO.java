package com.library.dao;

import com.library.model.BookType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// ͼ��������Ϣ�����ɾ�Ĳ�
public class BookTypeDAO {

    /**
     * ��ѯͼ�����͵�id
     *
     * @throws SQLException
     */
    public int queryBTid(String bt_name) throws SQLException {
        Connection conn = DataBaseDAO.connectMySQL();
        String sql = "SELECT bt_id from library.bookType WHERE bt_name=?";
        PreparedStatement pr = conn.prepareStatement(sql);
        pr.setString(1, bt_name);
        ResultSet rs = pr.executeQuery();
        int bookType = 0;
        while (rs.next()) { // �������ݿ������
            bookType = rs.getInt("bt_id");
        }
        DataBaseDAO.closeMySQL();// �ر�����
        return bookType;
    }

    /**
     * ��ѯͼ�����ͼ������
     */
    public List<BookType> queryBookType() throws SQLException {
        List<BookType> bookTypeData = new ArrayList<BookType>();
        Connection conn = DataBaseDAO.connectMySQL();
        String sql = "SELECT * FROM library.bookType ORDER BY bt_id ASC";//��ѯ����������
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            BookType bookType = new BookType();
            bookType.setBt_id(rs.getInt("bt_id"));
            bookType.setBt_name(rs.getString("bt_name"));
            bookTypeData.add(bookType);
        }
        DataBaseDAO.closeMySQL();// �ر�����
        return bookTypeData;
    }

    /**
     * ����ͼ������
     */
    public int insertBookType(String bt_name) throws SQLException {
        String sql = "INSERT INTO library.bookType(bt_name) VALUES(?);";
        DataBaseDAO.update_public(sql, bt_name);

        int id = 0;
        Connection conn = DataBaseDAO.connectMySQL();
        String insertID = "SELECT bt_id FROM library.bookType WHERE bt_name=?";
        PreparedStatement pr = conn.prepareStatement(insertID);
        pr.setString(1, bt_name);
        ResultSet rs = pr.executeQuery();
        while (rs.next()) {
            id = rs.getInt("bt_id");
        }
        return id;
    }

    /**
     * ɾ��ͼ������
     */
    public void deleteBookType(int bt_id) throws SQLException {
        String sql = "DELETE FROM library.bookType WHERE bt_id=?";
        DataBaseDAO.update_public(sql, bt_id);
    }

    /**
     * �޸�ͼ������
     */
    public void updateBookType(String input_bookType, int bt_id) throws SQLException {
        String sql = "UPDATE library.bookType SET bt_name='" + input_bookType + "' WHERE bt_id=?";
        DataBaseDAO.update_public(sql, bt_id);
    }
}
