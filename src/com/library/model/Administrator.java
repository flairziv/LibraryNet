package com.library.model;


/**
 * ����Ա����ֶ�ӳ��
 * �������ڱ�ʾ����Ա�Ļ�����Ϣ����������Ա���˺š����롢���������֤�š��绰�������ʼ��ȡ�
 */
public class Administrator {
    private int admId;              // ����ԱID
    private String admCount;        // ����Ա�˺�
    private String admPassword;     // ����Ա����
    private String admName;         // ����Ա����
    private String admIdNumber;     // ����Ա���֤��
    private String admContact;      // ����Ա��ϵ�绰
    private String admEmail;        // ����Ա����
    private String admKeepPass;     // ����Ա�Ƿ���Ҫ��������
    private boolean isSuper;        // �Ƿ�Ϊ��������Ա


    // Getter �� Setter ����

    public int getAdmId() {
        return admId;
    }

    public void setAdmId(int admId) {
        this.admId = admId;
    }

    public String getAdmCount() {
        return admCount;
    }

    public void setAdmCount(String admCount) {
        this.admCount = admCount;
    }

    public String getAdmPassword() {
        return admPassword;
    }

    public void setAdmPassword(String admPassword) {
        this.admPassword = admPassword;
    }

    public String getAdmName() {
        return admName;
    }

    public void setAdmName(String admName) {
        this.admName = admName;
    }

    public String getAdmIdNumber() {
        return admIdNumber;
    }

    public void setAdmIdNumber(String admIdNumber) {
        this.admIdNumber = admIdNumber;
    }

    public String getAdmContact() {
        return admContact;
    }

    public void setAdmContact(String admContact) {
        this.admContact = admContact;
    }

    public String getAdmEmail() {
        return admEmail;
    }

    public void setAdmEmail(String admEmail) {
        this.admEmail = admEmail;
    }

    public String getAdmKeepPass() {
        return admKeepPass;
    }

    public void setAdmKeepPass(String admKeepPass) {
        this.admKeepPass = admKeepPass;
    }

    public boolean isSuper() {
        return isSuper;
    }

    public void setSuper(boolean isSuper) {
        this.isSuper = isSuper;
    }

    // toString ���������ڴ�ӡ����Ա��Ϣ
    @Override
    public String toString() {
        return "Administrator{" +
                "admId=" + admId +
                ", admCount='" + admCount + '\'' +
                ", admName='" + admName + '\'' +
                ", admIdNumber='" + admIdNumber + '\'' +
                ", admContact='" + admContact + '\'' +
                ", admEmail='" + admEmail + '\'' +
                ", admKeepPass='" + admKeepPass + '\'' +
                ", isSuper=" + isSuper +
                '}';
    }
}

