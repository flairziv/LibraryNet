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

// 登录界面
public class Main extends JFrame implements ActionListener {
    boolean isuser = true;
    String count;
    final int WIDTH = 700, HEIGHT = 530;
    JPanel panel = new JPanel();
    JTextField jtext = new JTextField();
    JPasswordField jpassword = new JPasswordField();
    JLabel backImage;
    JLabel[] jlab = {new JLabel("账号："), new JLabel("密码：")};// 声明标签数组
    Font fnt = new Font("Microsoft YaHei", Font.BOLD, 20);
    ImageIcon img_lading = new ImageIcon("E:\\code\\IntelliJ_IDEAProjects\\LibraryNet\\src\\com\\library\\images\\lading.jpg");
    ImageIcon img_login = new ImageIcon("E:\\code\\IntelliJ_IDEAProjects\\LibraryNet\\src\\com\\library\\images\\login.jpg");
    ImageIcon img_forgetPass = new ImageIcon("E:\\code\\IntelliJ_IDEAProjects\\LibraryNet\\src\\com\\library\\images\\forgetPass.jpg");
    JButton jbt_lading, jbt_login, jbt_forgetPass;
    JRadioButton jrb1 = new JRadioButton("用户");
    JRadioButton jrb2 = new JRadioButton("管理员");
    AdminController admiCon = new AdminController();
    Administrator admi = new Administrator();
    ReaderController readercon = new ReaderController();

    /*
     * 登录界面
     */
    public Main() {
        ButtonGroup grp = new ButtonGroup();// 实例化单选按钮组
        backImage = new JLabel(new ImageIcon("E:\\code\\IntelliJ_IDEAProjects\\LibraryNet\\src\\com\\library\\images\\LadingInterface.jpg"));
        panel.setLayout(null);// 取消默认布局管理器
        jbt_lading = new JButton(img_lading);// 登录按钮
        jbt_login = new JButton(img_login);// 注册按钮
        jbt_forgetPass = new JButton(img_forgetPass);
        this.setLayout(null);
        this.setTitle("图书管理系统");
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);// 设置窗体居中显示
        this.setResizable(false);// 窗口不能改变大小
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);// 关闭窗口不执行任何操作
        this.setVisible(true);// 使窗口显示
        jtext.setBounds(250, 200, 250, 40);
        jpassword.setBounds(250, 260, 250, 40);
        jtext.setFont(fnt);
        jpassword.setFont(fnt);
        jtext.setDocument(new InputLimit(16));
        jpassword.setDocument(new InputLimit(16));// 匿名调用工具类——“限制输入”工具，限制输入16位

        panel.setBounds(0, 0, WIDTH, HEIGHT);
        jbt_lading.setBounds(280, 330, 200, 40);
        jbt_login.setBounds(500, 200, 100, 40);
        jbt_forgetPass.setBounds(500, 260, 100, 40);
        jbt_login.setFocusable(false);// 去焦点
        jlab[0].setBounds(180, 200, 80, 40);
        jlab[1].setBounds(180, 260, 80, 40);
        jlab[0].setFont(fnt);
        jlab[1].setFont(fnt);
        backImage.setSize(WIDTH, HEIGHT);
        jlab[0].setHorizontalAlignment(0);// 设置水平对齐方式 0居中
        jlab[1].setHorizontalAlignment(0);
        jrb1.setBounds(270, 150, 80, 40);
        jrb2.setBounds(370, 150, 80, 40);
        jrb1.setSelected(true);//设置用户单选按钮默认被选中
        grp.add(jrb1);// 将单选按钮添加到单选按钮组中（保证单选）
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

        jrb1.addActionListener(this);// 设置单选框的监听者
        jrb2.addActionListener(this);
        jbt_lading.addActionListener(this);// 设置按钮的监听者
        jbt_login.addActionListener(this);
        jbt_forgetPass.addActionListener(this);
        jtext.addActionListener(this);// 设置账号监听者
        jpassword.addActionListener(this);// 设置密码监听者
        jtext.addFocusListener(new InputLimit(jtext, "11位的借阅证号"));// 设置文诓提示的外部类监听
        // 点击结束程序时，弹出对话框
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int c = JOptionPane.showConfirmDialog(null, "是否要退出系统程序", "操作验证", JOptionPane.YES_NO_OPTION);
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
        boolean result = InputLimit.regular("^[0-9a-zA-Z]{6,16}$", number);// 调用工具类中的验证正则的方法，传递正则和验证正则的文本
        /*
         * 管理员不可注册
         */
        if (obj == jrb1) {
            isuser = true;
            jbt_login.setVisible(true);
            jbt_forgetPass.setVisible(true);
            panel.add(jbt_login);
            panel.add(jbt_forgetPass);
            jbt_login.repaint();
            jbt_forgetPass.repaint();
            jtext.addFocusListener(new InputLimit(jtext, "11位借阅证号"));// 设置文诓提示的外部类监听
        } else if (obj == jrb2) {
            isuser = false;
            jbt_login.setVisible(false);
            jbt_forgetPass.setVisible(false);
            panel.remove(jbt_login);
            panel.remove(jbt_forgetPass);
            jtext.addFocusListener(new InputLimit(jtext, "6-16位管理员账号"));// 设置文诓提示的外部类监听
        }
        /*
         * 判断登录的账号密码信息 加入正则验证
         */
        if (obj == jbt_lading) {// 登录按钮
            try {
                if (number.equals("") || password.equals("")) { // 判断输入是否为空
                    JOptionPane.showMessageDialog(null, "输入数据为空", "输入错误", JOptionPane.ERROR_MESSAGE);
                } else if (result) {
                    if (readercon.queryReader(number, password) && isuser == true) {
                        count = number;// 将登录正确的账号传回数据库，方便查询信息，确保是哪个用户
                        new UserView(count);
                        this.dispose();
                    } else if (admiCon.queryAdmin(number, password) && isuser == false) {
                        count = number;
                        // 弹出确认框，询问是否恢复备份
                        int response = JOptionPane.showConfirmDialog(null,
                                "是否要恢复备份的数据库数据？",
                                "恢复数据库",
                                JOptionPane.YES_NO_OPTION);

                        // 如果管理员选择"是"，执行恢复操作
                        if (response == JOptionPane.YES_OPTION) {
                            isRestoreDatabase();
                        } else if (response == JOptionPane.NO_OPTION) {
                            // 如果选择“否”，跳过备份操作，直接跳转到管理界面
                            JOptionPane.showMessageDialog(null, "您选择了不恢复备份，跳过备份操作。",
                                    "跳过备份", JOptionPane.INFORMATION_MESSAGE);
                        }
                        new ManageView(count);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "用户名或密码输入错误", "信息错误", JOptionPane.ERROR_MESSAGE);
                        jpassword.setText(null);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "请输入6-16位的数字或者字母", "输入格式错误", JOptionPane.ERROR_MESSAGE);
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
            JLabel[] jlab_forget = {new JLabel("忘记密码的账号："), new JLabel("密保：")};
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
        // 让管理员输入备份文件路径
        String backupFilePath = JOptionPane.showInputDialog(null,
                "请输入备份文件的名称：");

        if (backupFilePath != null && !backupFilePath.trim().isEmpty()) {
            // 检查备份文件是否存在
            String path = "E:\\code\\IntelliJ_IDEAProjects\\LibraryNet\\backups\\";
            File backupFile = new File(path + backupFilePath);
            if (backupFile.exists() && backupFile.isFile()) {
                // 文件存在，恢复数据库
                restoreDatabase(path + backupFilePath);
                JOptionPane.showMessageDialog(null, "数据库恢复成功！", "恢复成功", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // 文件不存在，提示重新输入
                JOptionPane.showMessageDialog(null, "备份文件不存在，请重新输入正确的文件路径。",
                        "错误", JOptionPane.ERROR_MESSAGE);
                isRestoreDatabase();  // 重新让管理员输入备份路径
            }
        } else {
            JOptionPane.showMessageDialog(null, "您没有输入任何路径。", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

}

