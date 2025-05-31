package com.library.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


// 读者信息表的增删改查
public class ReaderDAO {

    /**
     * 注册时向reader表中添加信息
     */
    public void insertReader(String r_card_number, String r_name, String gender, int reader_type, String dept,
                             String classes, String r_contact, String r_email, String keepPass, String r_password) throws SQLException {
        String sql = "INSERT INTO library.reader(card_number,name,gender,reader_type,dept,classes,contact,email,keepPass,password) VALUES(?,?,?,?,?,?,?,?,?,?)";
        DataBaseDAO.update_public(sql, r_card_number, r_name, gender, reader_type, dept, classes, r_contact, r_email, keepPass,
                r_password);
    }

    /**
     * 查询账号是否存在 用于用户注册和新增读者（保证账号唯一性）
     */
    public boolean isCard_number(String r_card_number) throws SQLException {
        String sql = "SELECT card_number FROM library.reader WHERE card_number=?";
        return DataBaseDAO.exist(sql, r_card_number);
    }

    /**
     * 查询账号密码是否存在
     */
    public boolean queryReader(String r_card_number, String r_password) throws SQLException {
        String sql = "SELECT card_number,password FROM library.reader WHERE card_number=? AND password=?";
        return DataBaseDAO.exist(sql, r_card_number, r_password);
    }

    /**
     * 查询个人信息
     */
    public Vector<Vector<Object>> queryReaderInfo(String count) throws SQLException {
        String sql = "SELECT * FROM library.reader WHERE card_number=?";
        return DataBaseDAO.queryExact_public(sql, count);
    }

    /**
     * 修改读者信息
     */
    public void updateReader(String dept, String classes, String contact, String email, String card_number)
            throws SQLException {
        String sql = "UPDATE library.reader SET dept=?,classes=?,contact=?,email=? WHERE card_number=?";
        DataBaseDAO.update_public(sql, dept, classes, contact, email, card_number);
    }

    /**
     * 修改密码
     *
     * @throws SQLException
     */
    public void updateReaderPass(String alterPass, String r_card_number, String r_password, String r_keepPass)
            throws SQLException {
        String sql = "UPDATE library.reader SET password='" + alterPass
                + "' WHERE card_number=? AND password=? AND keepPass=?";
        DataBaseDAO.update_public(sql, r_card_number, r_password, r_keepPass);
    }

    /**
     * 查询 全部读者
     */
    public Vector<Vector<Object>> selectReader() throws SQLException {
        String sql = "SELECT card_number,name,gender,rt_name,dept,classes,contact,email,loginDate from library.reader,library.readerType where library.reader.reader_type=library.readerType.rt_id";
        return DataBaseDAO.queryExact_public(sql);
    }

    /**
     * 查询读者的信息 模糊查寻
     */
    public Vector<Vector<Object>> queryReaderInfo(String card_number, String name, String dept, String classes)
            throws SQLException {
        String sql = "SELECT card_number,name,gender,rt_name,dept,classes,contact,email,loginDate from library.reader,library.readerType "
                + "where library.reader.reader_type=library.readerType.rt_id AND (card_number LIKE ? OR name LIKE ? OR dept LIKE ? OR classes LIKE ?)";
        return DataBaseDAO.queryDim_public(sql, card_number, name, dept, classes);
    }

    /**
     * 查询读者的信息 模糊查寻 类型查询 重写原因，读者类型如果模糊查寻，会出现读者类型相似性问题
     */
    public Vector<Vector<Object>> selectReaderInfo(String card_number, String name, String dept, String classes,
                                                 String reader_type) throws SQLException {
        String sql = "SELECT card_number,name,gender,rt_name,dept,classes,contact,email,loginDate from library.reader,library.readerType "
                + "where library.reader.reader_type=library.readerType.rt_id AND (card_number LIKE ? OR name LIKE ? OR dept LIKE ? OR classes LIKE ?) AND rt_name=?";
        Vector<Vector<Object>> dataVector = new Vector<Vector<Object>>(); // 存储所有数据，里面每个小的Vector是存单行的
        Connection conn = DataBaseDAO.connectMySQL();// 调用数据库的连接方法
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, "%" + card_number + "%");
        ptmt.setString(2, "%" + name + "%");
        ptmt.setString(3, "%" + dept + "%");
        ptmt.setString(4, "%" + classes + "%");
        ptmt.setString(5, reader_type);
        ResultSet rs = ptmt.executeQuery();
        while (rs.next()) {
            Vector<Object> vec = new Vector<Object>();// 就是这个存单行的，最后放到上面的大的Vector里面
            // 遍历数据库中每列的结果集 column需要遍历的列数
            for (int i = 1; i <= 9; i++) {
                vec.add(rs.getObject(i));
            }
            dataVector.add(vec);
        }
        DataBaseDAO.closeMySQL();// 关闭连接
        return dataVector;
    }

    /**
     * 删除读者信息
     */
    public void dropReader(String studentCard_number) throws SQLException {
        String sql = "DELETE FROM library.reader WHERE card_number=?";
        DataBaseDAO.update_public(sql, studentCard_number);
    }

    /**
     * 忘记密码 密保验证
     */
    public boolean queryKeepPass(String forgetPass, String count) throws SQLException {
        String sql = "SELECT keepPass FROM library.reader WHERE keepPass=? AND card_number=?";
        return DataBaseDAO.exist(sql, forgetPass, count);
    }

    /**
     * 忘记密码后重置密码
     */
    public void resetPass(String forgetPass, String count, String newPass) throws SQLException {
        String sql = "UPDATE library.reader SET password='" + newPass + "' WHERE keepPass=? AND card_number=? ";
        DataBaseDAO.update_public(sql, forgetPass, count);
    }

    /**
     * 查询是否有读者具备此读者类型 删除类读者类型前，保证没有读者应用此读者类型
     */
    public boolean existReaderType(int rt_id) throws SQLException {
        String sql = "SELECT reader_type FROM library.reader WHERE reader_type=?";
        return DataBaseDAO.exist(sql, rt_id);

    }
}

