package com.library.util;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

// 限制输入内容及信息提示
public class InputLimit extends PlainDocument implements FocusListener {
    private int lengthLimit;  // 限制的长度
    private String hintMessage;  // 提示信息
    private JTextField inputField;  // 输入框

    public static final String NAME_REGEX = "^([\u4e00-\u9fa5]{2,5})$";  // 中文姓名（2-5个字符）
    public static final String CHINESE_REGEX = "^[\\u4e00-\\u9fa5]{0,}$";  // 中文
    public static final String TELEPHONE_REGEX = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";  // 电话号码
    public static final String EMAIL_REGEX = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";  // 邮箱
    public static final String INTEGER_REGEX = "^[0-9]*$";  // 整数
    public static final String DECIMAL_REGEX = "^[0-9]+(\\.[0-9]{1,2})?$";  // 小数（最多两位小数）
    public static final String ID_CARD_REGEX = "^[1-8][0-7]{2}\\d{3}([12]\\d{3})(0[1-9]|1[012])(0[1-9]|[12]\\d|3[01])\\d{3}([0-9Xx])$";  // 身份证号
    public static final String PASSWORD_REGEX = "^[a-zA-Z0-9]{6,16}$";  // 密码（6-16位）
    public static final String CHINESE_ENGLISH_MATH_REGEX = "^[a-zA-Z0-9\\u4e00-\\u9fa5 ]{2,20}$";  // 中英文和数字
    public static final String CHINESE_ENGLISH_REGEX = "^[a-zA-Z\\u4e00-\\u9fa5 ]{2,20}$";  // 中英文
    public static final String CHINESE_MATH_REGEX = "^[0-9\\u4e00-\\u9fa5 ]{2,20}$";  // 中文和数字
    public static final String STUDENT_NUMBER_REGEX = "^20[\\d]{9}$";  // 借阅证号（20开头，9位数字）
    public static final String ISBN_REGEX = "^[0-9]{10}$";  // ISBN号（10位数字）
    public static final String COUNT_REGEX = "^[0-9a-zA-Z]{6,16}$";  // 计数器（6-16位字母数字）

    // 构造函数
    public InputLimit(int lengthLimit) {
        super();  // 调用父类构造
        this.lengthLimit = lengthLimit;
    }

    // 限制输入长度的方法（工具）
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null)
            return;
        if ((getLength() + str.length()) <= lengthLimit) {
            super.insertString(offset, str, attr);// 调用父类方法
        }
    }

    // 传参为String型的正则验证方法（工具）
    public static boolean regular(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        boolean result = matcher.matches();
        return result;
    }


     // 传参为String[]型的正则验证方法（工具）
    public static boolean[] regular(String regex[], String input[]) {
        boolean[] result =new boolean[regex.length];
        for (int i = 0; i < regex.length; i++) {
            Pattern pattern = Pattern.compile(regex[i]);
            Matcher matcher = pattern.matcher(input[i]);
            result[i] = matcher.matches();
        }
        return result;
    }


     // 输入框提示输入信息的功能 重载构造方法
    public InputLimit(JTextField jTextField, String hintText) {
        this.inputField = jTextField;
        this.hintMessage = hintText;
        jTextField.setText(hintText); // 默认直接显示
        jTextField.setForeground(Color.GRAY);// 显示为灰色文字
    }

    // 获取焦点事件
    @Override
    public void focusGained(FocusEvent e) {
        // TODO Auto-generated method stub
        // 获取焦点时，清空提示内容
        String temp = inputField.getText();
        if (temp.equals(hintMessage)) {
            inputField.setText("");
            inputField.setForeground(Color.BLACK);
        }
    }

    // 失去焦点事件
    @Override
    public void focusLost(FocusEvent e) {
        // TODO Auto-generated method stub
        // 失去焦点时，没有输入内容，显示提示内容

        String temp = inputField.getText();
        if (temp.equals("")) {
            inputField.setForeground(Color.GRAY);
            inputField.setText(hintMessage);
        }
    }


    // 判断输入的内容是否为空
    public static boolean inputIsNull(JTextField[] input) {
        for (int i = 0; i < input.length; i++) {
            if (input[i].getText().equals("")) {
                JOptionPane.showMessageDialog(null, "您所注册的信息中不能为空", "信息错误", JOptionPane.ERROR_MESSAGE);
                return true;// 跳出循环，防止一直循环弹出对话框
            }
        }
        return false;
    }

    // 判断String类型数据是否为空
    public static boolean strIsNull(String str) {
        if (str.equals("")) {
            return true;
        }
        return false;
    }
}
