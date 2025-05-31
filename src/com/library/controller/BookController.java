package com.library.controller;


import com.library.dao.BookDAO;

import java.sql.SQLException;
import java.util.Vector;


// ͼ����Ϣ��Ŀ�����
public class BookController {
    BookDAO bd = new BookDAO();

    /**
     * ��ȷ��ѯ��ģ����ѯ��������ͼ�����ͣ�
     */
    public Vector<Vector<Object>> getVector(String ISBN,String b_name, String author) throws SQLException {
        Vector<Vector<Object>> Vdata = bd.inithavesold(ISBN,b_name, author);
        return Vdata;
    }

    /**
     * ��ѯȫ��ͼ��
     */
    public Vector<Vector<Object>> selectBook() throws SQLException {
        return bd.selectBook();
    }

    /**
     * ��ȷ��ѯ��ģ����ѯ������ͼ�����ͣ�
     */
    public Vector<Vector<Object>> getBook(String ISBN,String b_name, String author, String b_type) throws SQLException {
        Vector<Vector<Object>> bookData = bd.queryBook(ISBN,b_name, author, b_type);
        return bookData;
    }

    /**
     * ��ѯͼ������
     */
    public Vector<String> getB_type() throws SQLException {
        Vector<String> bt_name = bd.selectB_type();
        return bt_name;
    }

    /**
     * ��ѯͼ������id
     */
    public int selectB_name(String name) throws SQLException {
        int count = bd.selectB_name(name);
        return count;
    }

    /**
     * ɾ��ͼ��
     */
    public void dropBook(int b_id) throws SQLException {
        bd.dropBook(b_id);
    }

    /**
     * ����ͼ��
     */
    public void insertBook(String ISBN, String b_name, int bookType, String author, String press, double price,
                           int inventory) throws SQLException {
        bd.insertBook(ISBN, b_name, bookType, author, press, price, inventory);
    }

    /**
     * �޸�ͼ��
     */
    public void updateBook(String ISBN, String b_name, String author, String press, double price, int inventory,
                           int b_id) throws SQLException {
        bd.updateBook(ISBN, b_name, author, press, price, inventory, b_id);
    }

    /**
     * ��ѯ�Ƿ���ͼ��߱���ͼ������ ɾ����ͼ������ǰ����֤û��ͼ��Ӧ�ô�ͼ������
     */
    public boolean existBookType(int bt_id) throws SQLException {
        return bd.existBookType(bt_id);
    }


    /**
     * ��ѯͼ��ISBN�Ƿ���� �����ڣ�����ע��
     */
    public boolean isISBN(String ISBN) throws SQLException {
        return bd.isISBN(ISBN);
    }
}

