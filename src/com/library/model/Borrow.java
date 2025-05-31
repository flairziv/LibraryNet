package com.library.model;


// ���Ĺ黹��Ϣ���ֶ�ӳ��
public class Borrow {

    private int borrow_id;      // ����ID
    private String r_number;    // ���߱��
    private int borrow_b_id;    // ͼ����
    private int borrowDate;     // ��������
    private int dueDate;        // ��������
    private int returnDate;     // ʵ�ʹ黹����
    private boolean isReturn;   // �Ƿ��ѹ黹

    // Getter �� Setter ����
    public int getBorrow_id() {
        return borrow_id;
    }

    public void setBorrow_id(int borrow_id) {
        this.borrow_id = borrow_id;
    }

    public String getR_number() {
        return r_number;
    }

    public void setR_number(String r_number) {
        this.r_number = r_number;
    }

    public int getBorrow_b_id() {
        return borrow_b_id;
    }

    public void setBorrow_b_id(int borrow_b_id) {
        this.borrow_b_id = borrow_b_id;
    }

    public int getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(int borrowDate) {
        this.borrowDate = borrowDate;
    }

    public int getDueDate() {
        return dueDate;
    }

    public void setDueDate(int dueDate) {
        this.dueDate = dueDate;
    }

    public int getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(int returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isIsReturn() {
        return isReturn;
    }

    public void setIsReturn(boolean isReturn) {
        this.isReturn = isReturn;
    }

    // ��д toString �������������ʱ���������Ϣ
    @Override
    public String toString() {
        return "Borrow{" +
                "borrow_id=" + borrow_id +
                ", r_number='" + r_number + '\'' +
                ", borrow_b_id=" + borrow_b_id +
                ", borrowDate=" + borrowDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                ", isReturn=" + isReturn +
                '}';
    }
}

