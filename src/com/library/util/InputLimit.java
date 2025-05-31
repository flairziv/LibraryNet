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

// �����������ݼ���Ϣ��ʾ
public class InputLimit extends PlainDocument implements FocusListener {
    private int lengthLimit;  // ���Ƶĳ���
    private String hintMessage;  // ��ʾ��Ϣ
    private JTextField inputField;  // �����

    public static final String NAME_REGEX = "^([\u4e00-\u9fa5]{2,5})$";  // ����������2-5���ַ���
    public static final String CHINESE_REGEX = "^[\\u4e00-\\u9fa5]{0,}$";  // ����
    public static final String TELEPHONE_REGEX = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";  // �绰����
    public static final String EMAIL_REGEX = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";  // ����
    public static final String INTEGER_REGEX = "^[0-9]*$";  // ����
    public static final String DECIMAL_REGEX = "^[0-9]+(\\.[0-9]{1,2})?$";  // С���������λС����
    public static final String ID_CARD_REGEX = "^[1-8][0-7]{2}\\d{3}([12]\\d{3})(0[1-9]|1[012])(0[1-9]|[12]\\d|3[01])\\d{3}([0-9Xx])$";  // ���֤��
    public static final String PASSWORD_REGEX = "^[a-zA-Z0-9]{6,16}$";  // ���루6-16λ��
    public static final String CHINESE_ENGLISH_MATH_REGEX = "^[a-zA-Z0-9\\u4e00-\\u9fa5 ]{2,20}$";  // ��Ӣ�ĺ�����
    public static final String CHINESE_ENGLISH_REGEX = "^[a-zA-Z\\u4e00-\\u9fa5 ]{2,20}$";  // ��Ӣ��
    public static final String CHINESE_MATH_REGEX = "^[0-9\\u4e00-\\u9fa5 ]{2,20}$";  // ���ĺ�����
    public static final String STUDENT_NUMBER_REGEX = "^20[\\d]{9}$";  // ����֤�ţ�20��ͷ��9λ���֣�
    public static final String ISBN_REGEX = "^[0-9]{10}$";  // ISBN�ţ�10λ���֣�
    public static final String COUNT_REGEX = "^[0-9a-zA-Z]{6,16}$";  // ��������6-16λ��ĸ���֣�

    // ���캯��
    public InputLimit(int lengthLimit) {
        super();  // ���ø��๹��
        this.lengthLimit = lengthLimit;
    }

    // �������볤�ȵķ��������ߣ�
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null)
            return;
        if ((getLength() + str.length()) <= lengthLimit) {
            super.insertString(offset, str, attr);// ���ø��෽��
        }
    }

    // ����ΪString�͵�������֤���������ߣ�
    public static boolean regular(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        boolean result = matcher.matches();
        return result;
    }


     // ����ΪString[]�͵�������֤���������ߣ�
    public static boolean[] regular(String regex[], String input[]) {
        boolean[] result =new boolean[regex.length];
        for (int i = 0; i < regex.length; i++) {
            Pattern pattern = Pattern.compile(regex[i]);
            Matcher matcher = pattern.matcher(input[i]);
            result[i] = matcher.matches();
        }
        return result;
    }


     // �������ʾ������Ϣ�Ĺ��� ���ع��췽��
    public InputLimit(JTextField jTextField, String hintText) {
        this.inputField = jTextField;
        this.hintMessage = hintText;
        jTextField.setText(hintText); // Ĭ��ֱ����ʾ
        jTextField.setForeground(Color.GRAY);// ��ʾΪ��ɫ����
    }

    // ��ȡ�����¼�
    @Override
    public void focusGained(FocusEvent e) {
        // TODO Auto-generated method stub
        // ��ȡ����ʱ�������ʾ����
        String temp = inputField.getText();
        if (temp.equals(hintMessage)) {
            inputField.setText("");
            inputField.setForeground(Color.BLACK);
        }
    }

    // ʧȥ�����¼�
    @Override
    public void focusLost(FocusEvent e) {
        // TODO Auto-generated method stub
        // ʧȥ����ʱ��û���������ݣ���ʾ��ʾ����

        String temp = inputField.getText();
        if (temp.equals("")) {
            inputField.setForeground(Color.GRAY);
            inputField.setText(hintMessage);
        }
    }


    // �ж�����������Ƿ�Ϊ��
    public static boolean inputIsNull(JTextField[] input) {
        for (int i = 0; i < input.length; i++) {
            if (input[i].getText().equals("")) {
                JOptionPane.showMessageDialog(null, "����ע�����Ϣ�в���Ϊ��", "��Ϣ����", JOptionPane.ERROR_MESSAGE);
                return true;// ����ѭ������ֹһֱѭ�������Ի���
            }
        }
        return false;
    }

    // �ж�String���������Ƿ�Ϊ��
    public static boolean strIsNull(String str) {
        if (str.equals("")) {
            return true;
        }
        return false;
    }
}
