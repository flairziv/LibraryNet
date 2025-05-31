package com.library.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


// 用户端界面
public class UserView extends JFrame {
    final int WIDTH = 1000, HEIGHT = 730;
    static String count;
    JTabbedPane jtab = new JTabbedPane(JTabbedPane.TOP); // 创建选项卡窗格
    JPanel[] jpan = new JPanel[5];
    UserBook userBook = new UserBook();
    UserReturn userReturn = new UserReturn();
    UserMessage userMessage = new UserMessage();

    // 构造方法
    public UserView(String count) throws SQLException {
        this.count = count;
        this.setLayout(null);
        this.setTitle("图书管理系统（用户端）");
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setVisible(true);

        // 检查逾期归还图书
        checkOverdueBooks();

        // 创建选项卡
        jtab.setSize(WIDTH, HEIGHT);

        jpan[0] = userBook.addPanel0();
        jtab.addTab("图书查询借阅", jpan[0]);
        jtab.setSelectedIndex(0);

        jpan[1] = userReturn.addPanel1();
        jtab.addTab("借阅归还信息管理", jpan[1]);

        jpan[2] = userMessage.addPanel2();
        jtab.addTab("个人信息管理", jpan[2]);

        this.add(jtab);

        // 监听窗口关闭事件
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

    // 检查逾期归还图书的方法
    private void checkOverdueBooks() throws SQLException {
        // 你需要根据用户编号查询数据库，判断用户是否有逾期的图书
        boolean hasOverdue = userReturn.hasOverdueBooks(count);  // 假设 UserReturn 类有一个方法检查逾期

        if (hasOverdue) {
            // 如果有逾期图书，弹出提示框
            int choice = JOptionPane.showConfirmDialog(this, "您有逾期未归还的图书，请尽快归还。", "逾期提示", JOptionPane.OK_CANCEL_OPTION);
            if (choice == JOptionPane.OK_OPTION) {
                // 逾期提示框确认后可以做一些额外的处理
                System.out.println("用户确认逾期归还提示");
            }
        }
    }
}


