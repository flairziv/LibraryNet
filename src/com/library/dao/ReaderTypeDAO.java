package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


// 读者类型信息表的增删改查
public class ReaderTypeDAO {

    /**
     * 查询读者类型
     * @throws SQLException
     */
    public Object[][] queryReaderType() throws SQLException{
        Connection conn = DataBaseDAO.connectMySQL();
        String sql = "SELECT * from library.readerType";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        int len = 0;
        while(rs.next()){
            len++;//遍历结果集，知道表中有多少行数据
        }
        Object[][] data_readerType = new Object[len][4];
        rs.beforeFirst();
        for(int i=0;i<len ;i++) {
            rs.next();
            data_readerType[i][0] = rs.getInt("rt_id");
            data_readerType[i][1] = rs.getString("rt_name");
            data_readerType[i][2] = rs.getInt("maxCount");
            data_readerType[i][3] = rs.getInt("maxDay");
        }
        DataBaseDAO.closeMySQL();// 关闭连接
        return data_readerType;
    }
    /**
     * 查询读者类型的ID
     */
    public int queryReaderTypeID(String reader_type) throws SQLException {
        Connection conn = DataBaseDAO.connectMySQL();
        String sql = "SELECT rt_id from library.readerType WHERE rt_name=?";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, reader_type);
        ResultSet rs = ptmt.executeQuery();
        int rt_id=0;
        while (rs.next()) {
            rt_id=rs.getInt("rt_id");
        }
        DataBaseDAO.closeMySQL();// 关闭连接
        return rt_id;
    }
    /**
     * 查询个人权限
     */
    public Vector<Vector<Object>> queryPersonalType(String count) throws SQLException {
        int column=3;
        String sql="SELECT rt_name,maxCount,maxDay FROM library.readerType "
                + "WHERE rt_id IN ( SELECT reader_type FROM library.reader WHERE card_number=? )";
        return DataBaseDAO.queryExact_public(sql,count);
    }
    /**
     * 新增读者类型
     * @throws SQLException
     */
    public void insertReaderType(String rt_name,int maxCount,int maxDay) throws SQLException {
        String sql = "INSERT INTO library.readerType(rt_name,maxCount,maxDay) VALUES(?,?,?)";
        DataBaseDAO.update_public(sql, rt_name,maxCount,maxDay);
    }
    /**
     *删除读者类型
     */
    public void deleteReaderType(int rt_id) throws SQLException {
        String sql = "DELETE FROM library.readerType WHERE rt_id=?";
        DataBaseDAO.update_public(sql, rt_id);
    }
    /**
     * 修改读者类型
     */
    public void updateReaderType(String readerType,int maxCount,int maxDay,int rt_id) throws SQLException {
        String sql = "UPDATE library.readerType SET rt_name=?,maxCount=?,maxDay=? WHERE rt_id=?";
        DataBaseDAO.update_public(sql,readerType,maxCount ,maxDay,rt_id);
    }
}

