package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

// ���Ĺ黹��Ϣ�����ɾ�Ĳ�
public class BorrowDAO {

    /**
     * ����
     * ���������Ϣ
     */
    public boolean insertBorrow(String number, int b_name, int borrowDate, int dueDate,int b_id){
        boolean isCommit=false;
        Connection conn = DataBaseDAO.connectMySQL();
        String sql = "INSERT INTO library.borrow(r_number,borrow_b_id,borrowDate,dueDate,isReturn) VALUES(?,?,?,?,'0')";
        try {
            conn.setAutoCommit(false);//���Զ��ύ����Ϊfalse 
            PreparedStatement ptmt1 = conn.prepareStatement(sql);
            ptmt1.setString(1, number);
            ptmt1.setInt(2, b_name);
            ptmt1.setLong(3, borrowDate);
            ptmt1.setLong(4, dueDate);
            ptmt1.executeUpdate();

            String updateInvebtorySql="UPDATE library.book SET inventory=inventory-1 WHERE b_id=?;";
            PreparedStatement ptmt2 = conn.prepareStatement(updateInvebtorySql);
            ptmt2.setInt(1, b_id);
            ptmt2.executeUpdate();

            conn.commit();//�����������ɹ����ֶ��ύ  
            isCommit=true;
        } catch (SQLException e) {
            try {
                System.out.println("����ع�");
                isCommit=false;
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }    //һ������һ�������������ع���ʹ�������������ɹ�  
            e.printStackTrace();
        }
        DataBaseDAO.closeMySQL();// �ر�����
        return isCommit;
    }
    /**
     * ����
     * ����ʵ�ʹ黹ʱ�䣬����isReturn���Ƿ�黹����0��Ϊ1
     */
    public boolean returnBorrow(int returnDate,int borrow_id,int b_id){
        boolean isCommit=false;
        Connection conn = DataBaseDAO.connectMySQL();
        try {
            String sql = "UPDATE library.borrow SET returnDate=?,isReturn='1' WHERE borrow_id=?";
            conn.setAutoCommit(false);//���Զ��ύ����Ϊfalse 
            PreparedStatement ptmt1 = conn.prepareStatement(sql);
            ptmt1.setInt(1, returnDate);
            ptmt1.setInt(2, borrow_id);
            ptmt1.executeUpdate();

            String updateInvebtorySql="UPDATE library.book SET inventory=inventory+1 WHERE b_id=?;";
            PreparedStatement ptmt2 = conn.prepareStatement(updateInvebtorySql);
            ptmt2.setInt(1, b_id);
            ptmt2.executeUpdate();
            conn.commit();//�����������ɹ����ֶ��ύ  
            isCommit=true;
        } catch (SQLException e) {
            try {
                System.out.println("����ع�");
                isCommit=false;
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }    //һ������һ�������������ع���ʹ�������������ɹ�  
            e.printStackTrace();
            e.printStackTrace();
        }
        DataBaseDAO.closeMySQL();// �ر�����
        return isCommit;
    }
    /**
     * �鿴������Ϣ�ķ���
     * @throws SQLException
     */
    public Vector<Vector<Object>> queryBorrowInfo(String number1,String number2,boolean isReturn) throws SQLException {
        String sql ="SELECT borrow_id,ISBN,book.b_name,bt_name,author,borrowDate,dueDate,returnDate FROM library.book,library.bookType,library.borrow WHERE library.book.bookType=library.bookType.bt_id" +
                " AND borrow.r_number=? AND borrow.borrow_b_id=book.b_id AND book.b_id IN (SELECT borrow.borrow_b_id FROM library.borrow WHERE r_number=? ) AND borrow.isReturn=? ORDER BY borrowDate DESC";
        return DataBaseDAO.queryExact_public(sql,number1,number2,isReturn);
    }
    /**
     * ��ѯ�����Ƿ񱻽���
     * @throws SQLException
     */
    public boolean queryExistBook(int borrow_b_id) throws SQLException {
        String sql="SELECT borrow_b_id FROM library.borrow WHERE borrow_b_id=? AND isReturn='0'";
        return DataBaseDAO.exist(sql, borrow_b_id);
    }
    /**
     * ��ѯ�����Ƿ񱻽���
     */
    public boolean queryIsBorrowBook(int borrow_b_id,String count)throws SQLException {
        String sql="SELECT borrow_b_id FROM library.borrow WHERE borrow_b_id=? AND r_number=? AND isReturn='0'";
        return DataBaseDAO.exist(sql, borrow_b_id,count);
    }
}

