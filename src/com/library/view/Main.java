package com.library.view;


import com.library.controller.AdminController;
import com.library.controller.ReaderController;
import com.library.model.Administrator;
import com.library.util.InputLimit;
import com.library.util.MD5Utils;
import com.library.util.PubJdialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;

import static com.library.util.BackupScheduler.restoreDatabase;

// ��¼����
public class Main extends JFrame implements ActionListener {
    boolean isuser = true;
    String count;
    final int WIDTH = 700, HEIGHT = 530;
    JPanel panel = new JPanel();
    JTextField jtext = new JTextField();
    JPasswordField jpassword = new JPasswordField();
    JLabel backImage;
    JLabel[] jlab = {new JLabel("�˺ţ�"), new JLabel("���룺")};// ������ǩ����
    Font fnt = new Font("Microsoft YaHei", Font.BOLD, 20);
    ImageIcon img_lading = new ImageIcon("E:\\code\\IntelliJ_IDEAProjects\\LibraryNet\\src\\com\\library\\images\\lading.jpg");
    ImageIcon img_login = new ImageIcon("E:\\code\\IntelliJ_IDEAProjects\\LibraryNet\\src\\com\\library\\images\\login.jpg");
    ImageIcon img_forgetPass = new ImageIcon("E:\\code\\IntelliJ_IDEAProjects\\LibraryNet\\src\\com\\library\\images\\forgetPass.jpg");
    JButton jbt_lading, jbt_login, jbt_forgetPass;
    JRadioButton jrb1 = new JRadioButton("�û�");
    JRadioButton jrb2 = new JRadioButton("����Ա");
    AdminController admiCon = new AdminController();
    Administrator admi = new Administrator();
    ReaderController readercon = new ReaderController();

    /*
     * ��¼����
     */
    public Main() {
        ButtonGroup grp = new ButtonGroup();// ʵ������ѡ��ť��
        backImage = new JLabel(new ImageIcon("E:\\code\\IntelliJ_IDEAProjects\\LibraryNet\\src\\com\\library\\images\\LadingInterface.jpg"));
        panel.setLayout(null);// ȡ��Ĭ�ϲ��ֹ�����
        jbt_lading = new JButton(img_lading);// ��¼��ť
        jbt_login = new JButton(img_login);// ע�ᰴť
        jbt_forgetPass = new JButton(img_forgetPass);
        this.setLayout(null);
        this.setTitle("ͼ�����ϵͳ");
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);// ���ô��������ʾ
        this.setResizable(false);// ���ڲ��ܸı��С
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);// �رմ��ڲ�ִ���κβ���
        this.setVisible(true);// ʹ������ʾ
        jtext.setBounds(250, 200, 250, 40);
        jpassword.setBounds(250, 260, 250, 40);
        jtext.setFont(fnt);
        jpassword.setFont(fnt);
        jtext.setDocument(new InputLimit(16));
        jpassword.setDocument(new InputLimit(16));// �������ù����ࡪ�����������롱���ߣ���������16λ

        panel.setBounds(0, 0, WIDTH, HEIGHT);
        jbt_lading.setBounds(280, 330, 200, 40);
        jbt_login.setBounds(500, 200, 100, 40);
        jbt_forgetPass.setBounds(500, 260, 100, 40);
        jbt_login.setFocusable(false);// ȥ����
        jlab[0].setBounds(180, 200, 80, 40);
        jlab[1].setBounds(180, 260, 80, 40);
        jlab[0].setFont(fnt);
        jlab[1].setFont(fnt);
        backImage.setSize(WIDTH, HEIGHT);
        jlab[0].setHorizontalAlignment(0);// ����ˮƽ���뷽ʽ 0����
        jlab[1].setHorizontalAlignment(0);
        jrb1.setBounds(270, 150, 80, 40);
        jrb2.setBounds(370, 150, 80, 40);
        jrb1.setSelected(true);//�����û���ѡ��ťĬ�ϱ�ѡ��
        grp.add(jrb1);// ����ѡ��ť��ӵ���ѡ��ť���У���֤��ѡ��
        grp.add(jrb2);
        panel.add(jrb1);
        panel.add(jrb2);
        panel.add(jtext);
        panel.add(jpassword);
        panel.add(jbt_lading);
        panel.add(jbt_login);
        panel.add(jbt_forgetPass);
        panel.add(jlab[0]);
        panel.add(jlab[1]);
        panel.add(backImage);
        this.add(panel);

        jrb1.addActionListener(this);// ���õ�ѡ��ļ�����
        jrb2.addActionListener(this);
        jbt_lading.addActionListener(this);// ���ð�ť�ļ�����
        jbt_login.addActionListener(this);
        jbt_forgetPass.addActionListener(this);
        jtext.addActionListener(this);// �����˺ż�����
        jpassword.addActionListener(this);// �������������
        jtext.addFocusListener(new InputLimit(jtext, "11λ�Ľ���֤��"));// ������ڲ��ʾ���ⲿ�����
        // �����������ʱ�������Ի���
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int c = JOptionPane.showConfirmDialog(null, "�Ƿ�Ҫ�˳�ϵͳ����", "������֤", JOptionPane.YES_NO_OPTION);
                if (c == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        String number = jtext.getText();
        String password = MD5Utils.string2MD5(new String(jpassword.getPassword()));
        boolean result = InputLimit.regular("^[0-9a-zA-Z]{6,16}$", number);// ���ù������е���֤����ķ����������������֤������ı�
        /*
         * ����Ա����ע��
         */
        if (obj == jrb1) {
            isuser = true;
            jbt_login.setVisible(true);
            jbt_forgetPass.setVisible(true);
            panel.add(jbt_login);
            panel.add(jbt_forgetPass);
            jbt_login.repaint();
            jbt_forgetPass.repaint();
            jtext.addFocusListener(new InputLimit(jtext, "11λ����֤��"));// ������ڲ��ʾ���ⲿ�����
        } else if (obj == jrb2) {
            isuser = false;
            jbt_login.setVisible(false);
            jbt_forgetPass.setVisible(false);
            panel.remove(jbt_login);
            panel.remove(jbt_forgetPass);
            jtext.addFocusListener(new InputLimit(jtext, "6-16λ����Ա�˺�"));// ������ڲ��ʾ���ⲿ�����
        }
        /*
         * �жϵ�¼���˺�������Ϣ ����������֤
         */
        if (obj == jbt_lading) {// ��¼��ť
            try {
                if (number.equals("") || password.equals("")) { // �ж������Ƿ�Ϊ��
                    JOptionPane.showMessageDialog(null, "��������Ϊ��", "�������", JOptionPane.ERROR_MESSAGE);
                } else if (result) {
                    if (readercon.queryReader(number, password) && isuser == true) {
                        count = number;// ����¼��ȷ���˺Ŵ������ݿ⣬�����ѯ��Ϣ��ȷ�����ĸ��û�
                        new UserView(count);
                        this.dispose();
                    } else if (admiCon.queryAdmin(number, password) && isuser == false) {
                        count = number;
                        // ����ȷ�Ͽ�ѯ���Ƿ�ָ�����
                        int response = JOptionPane.showConfirmDialog(null,
                                "�Ƿ�Ҫ�ָ����ݵ����ݿ����ݣ�",
                                "�ָ����ݿ�",
                                JOptionPane.YES_NO_OPTION);

                        // �������Աѡ��"��"��ִ�лָ�����
                        if (response == JOptionPane.YES_OPTION) {
                            isRestoreDatabase();
                        } else if (response == JOptionPane.NO_OPTION) {
                            // ���ѡ�񡰷񡱣��������ݲ�����ֱ����ת���������
                            JOptionPane.showMessageDialog(null, "��ѡ���˲��ָ����ݣ��������ݲ�����",
                                    "��������", JOptionPane.INFORMATION_MESSAGE);
                        }
                        new ManageView(count);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "�û����������������", "��Ϣ����", JOptionPane.ERROR_MESSAGE);
                        jpassword.setText(null);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "������6-16λ�����ֻ�����ĸ", "�����ʽ����", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else if (obj == jbt_login) {
            try {
                new LoginView();
                this.dispose();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else if (obj == jbt_forgetPass) {
            JLabel[] jlab_forget = {new JLabel("����������˺ţ�"), new JLabel("�ܱ���")};
            JTextField[] jtext_forget = new JTextField[2];
            try {
                new PubJdialog(jlab_forget, jtext_forget).setVisible(true);
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws ParseException {
        new Main();
    }

    private void isRestoreDatabase() {
        // �ù���Ա���뱸���ļ�·��
        String backupFilePath = JOptionPane.showInputDialog(null,
                "�����뱸���ļ������ƣ�");

        if (backupFilePath != null && !backupFilePath.trim().isEmpty()) {
            // ��鱸���ļ��Ƿ����
            String path = "E:\\code\\IntelliJ_IDEAProjects\\LibraryNet\\backups\\";
            File backupFile = new File(path + backupFilePath);
            if (backupFile.exists() && backupFile.isFile()) {
                // �ļ����ڣ��ָ����ݿ�
                restoreDatabase(path + backupFilePath);
                JOptionPane.showMessageDialog(null, "���ݿ�ָ��ɹ���", "�ָ��ɹ�", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // �ļ������ڣ���ʾ��������
                JOptionPane.showMessageDialog(null, "�����ļ������ڣ�������������ȷ���ļ�·����",
                        "����", JOptionPane.ERROR_MESSAGE);
                isRestoreDatabase();  // �����ù���Ա���뱸��·��
            }
        } else {
            JOptionPane.showMessageDialog(null, "��û�������κ�·����", "����", JOptionPane.ERROR_MESSAGE);
        }
    }

}

