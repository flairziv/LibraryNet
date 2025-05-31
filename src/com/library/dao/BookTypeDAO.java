package com.library.dao;

import com.library.model.BookType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// 图书类型信息表的增删改查
public class BookTypeDAO {

    /**
     * 查询图书类型的id
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
        while (rs.next()) { // 遍历数据库的数据
            bookType = rs.getInt("bt_id");
        }
        DataBaseDAO.closeMySQL();// 关闭连接
        return bookType;
    }

    /**
     * 查询图书类型及其序号
     */
    public List<BookType> queryBookType() throws SQLException {
        List<BookType> bookTypeData = new ArrayList<BookType>();
        Connection conn = DataBaseDAO.connectMySQL();
        String sql = "SELECT * FROM library.bookType ORDER BY bt_id ASC";//查询并排序（升序）
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            BookType bookType = new BookType();
            bookType.setBt_id(rs.getInt("bt_id"));
            bookType.setBt_name(rs.getString("bt_name"));
            bookTypeData.add(bookType);
        }
        DataBaseDAO.closeMySQL();// 关闭连接
        return bookTypeData;
    }

    /**
     * 新增图书类型
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
     * 删除图书类型
     */
    public void deleteBookType(int bt_id) throws SQLException {
        String sql = "DELETE FROM library.bookType WHERE bt_id=?";
        DataBaseDAO.update_public(sql, bt_id);
    }

    /**
     * 修改图书类型
     */
    public void updateBookType(String input_bookType, int bt_id) throws SQLException {
        String sql = "UPDATE library.bookType SET bt_name='" + input_bookType + "' WHERE bt_id=?";
        DataBaseDAO.update_public(sql, bt_id);
    }
}
