package com.library.model;


// ��ʾͼ������ݱ��ӳ���࣬����ͼ�顢���߼�������Ϣ��
public class System {

    private Book book;                      // ͼ����Ϣ
    private BookType bookType;              // ͼ������
    private Reader reader;                  // ������Ϣ
    private ReaderType readerType;          // ��������
    private Borrow borrow;                  // ������Ϣ
    private Administrator administrator;    // ����Ա��Ϣ

    // ��ȡͼ����Ϣ
    public Book getBook() {
        return book;
    }

    // ����ͼ����Ϣ
    public void setBook(Book book) {
        this.book = book;
    }

    // ��ȡͼ������
    public BookType getBookType() {
        return bookType;
    }

    // ����ͼ������
    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }

    // ��ȡ������Ϣ
    public Reader getReader() {
        return reader;
    }

    // ���ö�����Ϣ
    public void setReader(Reader reader) {
        this.reader = reader;
    }

    // ��ȡ��������
    public ReaderType getReaderType() {
        return readerType;
    }

    // ���ö�������
    public void setReaderType(ReaderType readerType) {
        this.readerType = readerType;
    }

    // ��ȡ������Ϣ
    public Borrow getBorrow() {
        return borrow;
    }

    // ���ý�����Ϣ
    public void setBorrow(Borrow borrow) {
        this.borrow = borrow;
    }

    // ��ȡ����Ա��Ϣ
    public Administrator getAdministrator() {
        return administrator;
    }

    // ���ù���Ա��Ϣ
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
