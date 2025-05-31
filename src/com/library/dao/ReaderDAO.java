package com.library.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


// ������Ϣ�����ɾ�Ĳ�
public class ReaderDAO {

    /**
     * ע��ʱ��reader���������Ϣ
     */
    public void insertReader(String r_card_number, String r_name, String gender, int reader_type, String dept,
                             String classes, String r_contact, String r_email, String keepPass, String r_password) throws SQLException {
        String sql = "INSERT INTO library.reader(card_number,name,gender,reader_type,dept,classes,contact,email,keepPass,password) VALUES(?,?,?,?,?,?,?,?,?,?)";
        DataBaseDAO.update_public(sql, r_card_number, r_name, gender, reader_type, dept, classes, r_contact, r_email, keepPass,
                r_password);
    }

    /**
     * ��ѯ�˺��Ƿ���� �����û�ע����������ߣ���֤�˺�Ψһ�ԣ�
     */
    public boolean isCard_number(String r_card_number) throws SQLException {
        String sql = "SELECT card_number FROM library.reader WHERE card_number=?";
        return DataBaseDAO.exist(sql, r_card_number);
    }

    /**
     * ��ѯ�˺������Ƿ����
     */
    public boolean queryReader(String r_card_number, String r_password) throws SQLException {
        String sql = "SELECT card_number,password FROM library.reader WHERE card_number=? AND password=?";
        return DataBaseDAO.exist(sql, r_card_number, r_password);
    }

    /**
     * ��ѯ������Ϣ
     */
    public Vector<Vector<Object>> queryReaderInfo(String count) throws SQLException {
        String sql = "SELECT * FROM library.reader WHERE card_number=?";
        return DataBaseDAO.queryExact_public(sql, count);
    }

    /**
     * �޸Ķ�����Ϣ
     */
    public void updateReader(String dept, String classes, String contact, String email, String card_number)
            throws SQLException {
        String sql = "UPDATE library.reader SET dept=?,classes=?,contact=?,email=? WHERE card_number=?";
        DataBaseDAO.update_public(sql, dept, classes, contact, email, card_number);
    }

    /**
     * �޸�����
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
     * ��ѯ ȫ������
     */
    public Vector<Vector<Object>> selectReader() throws SQLException {
        String sql = "SELECT card_number,name,gender,rt_name,dept,classes,contact,email,loginDate from library.reader,library.readerType where library.reader.reader_type=library.readerType.rt_id";
        return DataBaseDAO.queryExact_public(sql);
    }

    /**
     * ��ѯ���ߵ���Ϣ ģ����Ѱ
     */
    public Vector<Vector<Object>> queryReaderInfo(String card_number, String name, String dept, String classes)
            throws SQLException {
        String sql = "SELECT card_number,name,gender,rt_name,dept,classes,contact,email,loginDate from library.reader,library.readerType "
                + "where library.reader.reader_type=library.readerType.rt_id AND (card_number LIKE ? OR name LIKE ? OR dept LIKE ? OR classes LIKE ?)";
        return DataBaseDAO.queryDim_public(sql, card_number, name, dept, classes);
    }

    /**
     * ��ѯ���ߵ���Ϣ ģ����Ѱ ���Ͳ�ѯ ��дԭ�򣬶����������ģ����Ѱ������ֶ�����������������
     */
    public Vector<Vector<Object>> selectReaderInfo(String card_number, String name, String dept, String classes,
                                                 String reader_type) throws SQLException {
        String sql = "SELECT card_number,name,gender,rt_name,dept,classes,contact,email,loginDate from library.reader,library.readerType "
                + "where library.reader.reader_type=library.readerType.rt_id AND (card_number LIKE ? OR name LIKE ? OR dept LIKE ? OR classes LIKE ?) AND rt_name=?";
        Vector<Vector<Object>> dataVector = new Vector<Vector<Object>>(); // �洢�������ݣ�����ÿ��С��Vector�Ǵ浥�е�
        Connection conn = DataBaseDAO.connectMySQL();// �������ݿ�����ӷ���
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setString(1, "%" + card_number + "%");
        ptmt.setString(2, "%" + name + "%");
        ptmt.setString(3, "%" + dept + "%");
        ptmt.setString(4, "%" + classes + "%");
        ptmt.setString(5, reader_type);
        ResultSet rs = ptmt.executeQuery();
        while (rs.next()) {
            Vector<Object> vec = new Vector<Object>();// ��������浥�еģ����ŵ�����Ĵ��Vector����
            // �������ݿ���ÿ�еĽ���� column��Ҫ����������
            for (int i = 1; i <= 9; i++) {
                vec.add(rs.getObject(i));
            }
            dataVector.add(vec);
        }
        DataBaseDAO.closeMySQL();// �ر�����
        return dataVector;
    }

    /**
     * ɾ��������Ϣ
     */
    public void dropReader(String studentCard_number) throws SQLException {
        String sql = "DELETE FROM library.reader WHERE card_number=?";
        DataBaseDAO.update_public(sql, studentCard_number);
    }

    /**
     * �������� �ܱ���֤
     */
    public boolean queryKeepPass(String forgetPass, String count) throws SQLException {
        String sql = "SELECT keepPass FROM library.reader WHERE keepPass=? AND card_number=?";
        return DataBaseDAO.exist(sql, forgetPass, count);
    }

    /**
     * �����������������
     */
    public void resetPass(String forgetPass, String count, String newPass) throws SQLException {
        String sql = "UPDATE library.reader SET password='" + newPass + "' WHERE keepPass=? AND card_number=? ";
        DataBaseDAO.update_public(sql, forgetPass, count);
    }

    /**
     * ��ѯ�Ƿ��ж��߾߱��˶������� ɾ�����������ǰ����֤û�ж���Ӧ�ô˶�������
     */
    public boolean existReaderType(int rt_id) throws SQLException {
        String sql = "SELECT reader_type FROM library.reader WHERE reader_type=?";
        return DataBaseDAO.exist(sql, rt_id);

    }
}

