package com.library.model;

// ͼ�����ͱ��ֶ� ӳ��
public class BookType {
    private int bt_id;
    private String bt_name;

    // Getter �� Setter ����
    public int getBt_id() {
        return bt_id;
    }

    public void setBt_id(int bt_id) {
        this.bt_id = bt_id;
    }

    public String getBt_name() {
        return bt_name;
    }

    public void setBt_name(String bt_name) {
        this.bt_name = bt_name;
    }

    // toString ����
    @Override
    public String toString() {
        return "BookType{" +
                "bt_id=" + bt_id +
                ", bt_name='" + bt_name + '\'' +
                '}';
    }
}
