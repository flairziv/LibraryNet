package com.library.model;


// 表示图书馆数据表的映射类，包含图书、读者及借阅信息等
public class System {

    private Book book;                      // 图书信息
    private BookType bookType;              // 图书类型
    private Reader reader;                  // 读者信息
    private ReaderType readerType;          // 读者类型
    private Borrow borrow;                  // 借阅信息
    private Administrator administrator;    // 管理员信息

    // 获取图书信息
    public Book getBook() {
        return book;
    }

    // 设置图书信息
    public void setBook(Book book) {
        this.book = book;
    }

    // 获取图书类型
    public BookType getBookType() {
        return bookType;
    }

    // 设置图书类型
    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }

    // 获取读者信息
    public Reader getReader() {
        return reader;
    }

    // 设置读者信息
    public void setReader(Reader reader) {
        this.reader = reader;
    }

    // 获取读者类型
    public ReaderType getReaderType() {
        return readerType;
    }

    // 设置读者类型
    public void setReaderType(ReaderType readerType) {
        this.readerType = readerType;
    }

    // 获取借阅信息
    public Borrow getBorrow() {
        return borrow;
    }

    // 设置借阅信息
    public void setBorrow(Borrow borrow) {
        this.borrow = borrow;
    }

    // 获取管理员信息
    public Administrator getAdministrator() {
        return administrator;
    }

    // 设置管理员信息
    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    @Override
    public String toString() {
        return "Total{" +
                "book=" + book +
                ", bookType=" + bookType +
                ", reader=" + reader +
                ", readerType=" + readerType +
                ", borrow=" + borrow +
                ", administrator=" + administrator +
                '}';
    }
}
