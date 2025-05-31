package com.library.model;


// ������Ϣ���ֶ� ӳ��
public class Reader {
    private String cardNumber;              // ����֤��
    private String name;                    // ����
    private String gender;                  // �Ա�
    private String dept;                    // Ժϵ
    private String classes;                 // �༶
    private String contact;                 // �ֻ���
    private String email;                   // ����
    private java.sql.Timestamp loginDate;   // ��¼ʱ��
    private String password;                // ����
    private String keepPass;                // ��������
    private int readerType;                 // ��������


    // Getter �� Setter ����
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public java.sql.Timestamp getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(java.sql.Timestamp loginDate) {
        this.loginDate = loginDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKeepPass() {
        return keepPass;
    }

    public void setKeepPass(String keepPass) {
        this.keepPass = keepPass;
    }

    public int getReaderType() {
        return readerType;
    }

    public void setReaderType(int readerType) {
        this.readerType = readerType;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "cardNumber='" + cardNumber + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", dept='" + dept + '\'' +
                ", classes='" + classes + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", loginDate=" + loginDate +
                ", password='" + password + '\'' +
                ", keepPass='" + keepPass + '\'' +
                ", readerType=" + readerType +
                '}';
    }

}
