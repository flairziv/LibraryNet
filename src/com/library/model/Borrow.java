package com.library.model;


// 借阅归还信息表字段映射
public class Borrow {

    private int borrow_id;      // 借阅ID
    private String r_number;    // 读者编号
    private int borrow_b_id;    // 图书编号
    private int borrowDate;     // 借书日期
    private int dueDate;        // 还书日期
    private int returnDate;     // 实际归还日期
    private boolean isReturn;   // 是否已归还

    // Getter 和 Setter 方法
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

    // 重写 toString 方法，方便调试时输出对象信息
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

