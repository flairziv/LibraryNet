package com.library.model;


/**
 * 管理员表的字段映射
 * 该类用于表示管理员的基本信息，包括管理员的账号、密码、姓名、身份证号、电话、电子邮件等。
 */
public class Administrator {
    private int admId;              // 管理员ID
    private String admCount;        // 管理员账号
    private String admPassword;     // 管理员密码
    private String admName;         // 管理员姓名
    private String admIdNumber;     // 管理员身份证号
    private String admContact;      // 管理员联系电话
    private String admEmail;        // 管理员邮箱
    private String admKeepPass;     // 管理员是否需要保留密码
    private boolean isSuper;        // 是否为超级管理员


    // Getter 和 Setter 方法

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

    // toString 方法，便于打印管理员信息
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

