package com.library.model;


// ͼ����Ϣ���ֶ� ӳ��
public class Book {
    private int b_id;        // ͼ��ID
    private String ISBN;     // ISBN
    private String b_name;   // ����
    private int bookType;    // ͼ������
    private String author;   // ����
    private String press;    // ������
    private double price;    // �۸�
    private int inventory;   // �����

    // Getter �� Setter ����
    public int getB_id() {
        return b_id;
    }

    public void setB_id(int b_id) {
        this.b_id = b_id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }

    public int getBookType() {
        return bookType;
    }

    public void setBookType(int bookType) {
        this.bookType = bookType;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Book{" +
                "b_id=" + b_id +
                ", ISBN='" + ISBN + '\'' +
                ", b_name='" + b_name + '\'' +
                ", bookType=" + bookType +
                ", author='" + author + '\'' +
                ", press='" + press + '\'' +
                ", price=" + price +
                ", inventory=" + inventory +
                '}';
    }

}


