package com.library.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


// 图书信息表的增删改查
public class BookDAO {
    /**
     * 精确查询、模糊查询（不包含图书类型）
     */
    public Vector<Vector<Object>> inithavesold(String ISBN, String b_name, String author) throws SQLException {
        String sql = "SELECT b_id,ISBN,b_name,bt_name,author,press,price,inventory from library.book LEFT JOIN library.bookType "
                + "ON library.book.bookType=library.bookType.bt_id WHERE (ISBN LIKE ? OR b_name LIKE ? OR author LIKE ? )";
        return DataBaseDAO.queryDim_public(sql, ISBN, b_name, author);
    }

    /**
     * 查询全部图书
     */
    public Vector<Vector<Object>> selectBook() throws SQLException {
        String sql = "SELECT b_id,ISBN,b_name,bt_name,author,press,price,inventory from library.book LEFT JOIN library.bookType "
                + "ON library.book.bookType=library.bookType.bt_id ";
        return DataBaseDAO.queryExact_public(sql);
    }

    /**
     * 精确查询、模糊查询（包含图书类型）
     */
    public Vector<Vector<Object>> queryBook(String ISBN, String b_name, String author, String b_type)
            throws SQLException {
        String sql = "SELECT b_id,ISBN,b_name,bt_name,author,press,price,inventory from library.book LEFT JOIN library.bookType  "
                + " ON library.book.bookType=library.bookType.bt_id WHERE (ISBN LIKE ? OR b_name LIKE ? OR author LIKE ? ) AND bt_name=? ";
        Vector<Vector<Object>> dataVector = new Vector<Vector<Object>>(); // 存储所有数据，里面每个小的Vector是存单行的
        Connection conn = DataBaseDAO.connectMySQL();// 调用数据库的连接方法
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, "%" + ISBN + "%");
        ptmt.setString(2, "%" + b_name + "%");
        ptmt.setString(3, "%" + author + "%");
        ptmt.setString(4, b_type);
        ResultSet rs = ptmt.executeQuery();
        while (rs.next()) {
            Vector<Object> vec = new Vector<Object>();// 就是这个存单行的，最后放到上面的大的Vector里面
            // 遍历数据库中每列的结果集 column需要遍历的列数
            for (int i = 1; i <= 8; i++) {
                vec.add(rs.getObject(i));
            }
            dataVector.add(vec);
        }
        DataBaseDAO.closeMySQL();// 关闭连接
        return dataVector;
    }

    /**
     * 查询图书类型
     */
    public Vector<String> selectB_type() throws SQLException {
        Connection conn = DataBaseDAO.connectMySQL();
        Vector<String> bt_name = new Vector<String>();
        String sql = "SELECT bt_name from library.bookType ORDER BY bt_id ASC";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            bt_name.add(rs.getString("bt_name"));
        }
        DataBaseDAO.closeMySQL();// 关闭连接
        return bt_name;
    }

    /**
     * 查询图书类型id
     */
    public int selectB_name(String name) throws SQLException {
        Connection conn = DataBaseDAO.connectMySQL();
        String sql = "SELECT b_id from library.book WHERE b_name=?";
        int count = 0;
        PreparedStatement pr = conn.prepareStatement(sql);
        pr.setString(1, name);
        ResultSet rs = pr.executeQuery();
        while (rs.next()) { // 遍历数据库的数据
            count = rs.getInt(1);
        }
        DataBaseDAO.closeMySQL();// 关闭连接
        return count;
    }

    /**
     * 删除图书
     */
    public void dropBook(int b_id) throws SQLException {
        String sql = "DELETE FROM library.book WHERE b_id=?";
        DataBaseDAO.update_public(sql, b_id);
    }

    /**
     * 新增图书
     */
    public void insertBook(String ISBN, String b_name, int booktype, String author, String press, double price,
                           int inventory) throws SQLException {
        String sql = "INSERT INTO library.book(ISBN,b_name,bookType,author,press,price,inventory) VALUES(?,?,?,?,?,?,?)";
        DataBaseDAO.update_public(sql, ISBN, b_name, booktype, author, press, price, inventory);
    }

    /**
     * 修改图书信息
     */
    public void updateBook(String ISBN, String b_name, String author, String press, double price, int inventory,
                           int b_id) throws SQLException {
        String sql = "UPDATE library.book SET ISBN=?,b_name=?,author=?,press=?,price=?,inventory=? WHERE b_id=?";
        DataBaseDAO.update_public(sql, ISBN, b_name, author, press, price, inventory, b_id);
    }

    /**
     * 查询是否有图书具备此图书类型 删除类图书类型前，保证没有图书应用此图书类型
     */
    public boolean existBookType(int bt_id) throws SQLException {
        String sql = "SELECT bookType FROM library.book WHERE bookType=?";
        return DataBaseDAO.exist(sql, bt_id);
    }

    /**
     * 查询图书ISBN是否存在 若存在，不能注册
     */
    public boolean isISBN(String ISBN) throws SQLException {
        String sql = "SELECT ISBN FROM library.book WHERE ISBN=?";
        return DataBaseDAO.exist(sql, ISBN);
    }
}


