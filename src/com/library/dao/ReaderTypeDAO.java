package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


// ����������Ϣ�����ɾ�Ĳ�
public class ReaderTypeDAO {

    /**
     * ��ѯ��������
     * @throws SQLException
     */
    public Object[][] queryReaderType() throws SQLException{
        Connection conn = DataBaseDAO.connectMySQL();
        String sql = "SELECT * from library.readerType";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        int len = 0;
        while(rs.next()){
            len++;//�����������֪�������ж���������
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
        DataBaseDAO.closeMySQL();// �ر�����
        return data_readerType;
    }
    /**
     * ��ѯ�������͵�ID
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
        DataBaseDAO.closeMySQL();// �ر�����
        return rt_id;
    }
    /**
     * ��ѯ����Ȩ��
     */
    public Vector<Vector<Object>> queryPersonalType(String count) throws SQLException {
        int column=3;
        String sql="SELECT rt_name,maxCount,maxDay FROM library.readerType "
                + "WHERE rt_id IN ( SELECT reader_type FROM library.reader WHERE card_number=? )";
        return DataBaseDAO.queryExact_public(sql,count);
    }
    /**
     * ������������
     * @throws SQLException
     */
    public void insertReaderType(String rt_name,int maxCount,int maxDay) throws SQLException {
        String sql = "INSERT INTO library.readerType(rt_name,maxCount,maxDay) VALUES(?,?,?)";
        DataBaseDAO.update_public(sql, rt_name,maxCount,maxDay);
    }
    /**
     *ɾ����������
     */
    public void deleteReaderType(int rt_id) throws SQLException {
        String sql = "DELETE FROM library.readerType WHERE rt_id=?";
        DataBaseDAO.update_public(sql, rt_id);
    }
    /**
     * �޸Ķ�������
     */
    public void updateReaderType(String readerType,int maxCount,int maxDay,int rt_id) throws SQLException {
        String sql = "UPDATE library.readerType SET rt_name=?,maxCount=?,maxDay=? WHERE rt_id=?";
        DataBaseDAO.update_public(sql,readerType,maxCount ,maxDay,rt_id);
    }
}

