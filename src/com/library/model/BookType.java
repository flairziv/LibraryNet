package com.library.model;

// 图书类型表字段 映射
public class BookType {
    private int bt_id;
    private String bt_name;

    // Getter 和 Setter 方法
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

    // toString 方法
    @Override
    public String toString() {
        return "BookType{" +
                "bt_id=" + bt_id +
                ", bt_name='" + bt_name + '\'' +
                '}';
    }
}
