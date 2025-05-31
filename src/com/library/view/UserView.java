package com.library.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


// �û��˽���
public class UserView extends JFrame {
    final int WIDTH = 1000, HEIGHT = 730;
    static String count;
    JTabbedPane jtab = new JTabbedPane(JTabbedPane.TOP); // ����ѡ�����
    JPanel[] jpan = new JPanel[5];
    UserBook userBook = new UserBook();
    UserReturn userReturn = new UserReturn();
    UserMessage userMessage = new UserMessage();

    // ���췽��
    public UserView(String count) throws SQLException {
        this.count = count;
        this.setLayout(null);
        this.setTitle("ͼ�����ϵͳ���û��ˣ�");
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setVisible(true);

        // ������ڹ黹ͼ��
        checkOverdueBooks();

        // ����ѡ�
        jtab.setSize(WIDTH, HEIGHT);

        jpan[0] = userBook.addPanel0();
        jtab.addTab("ͼ���ѯ����", jpan[0]);
        jtab.setSelectedIndex(0);

        jpan[1] = userReturn.addPanel1();
        jtab.addTab("���Ĺ黹��Ϣ����", jpan[1]);

        jpan[2] = userMessage.addPanel2();
        jtab.addTab("������Ϣ����", jpan[2]);

        this.add(jtab);

        // �������ڹر��¼�
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

    // ������ڹ黹ͼ��ķ���
    private void checkOverdueBooks() throws SQLException {
        // ����Ҫ�����û���Ų�ѯ���ݿ⣬�ж��û��Ƿ������ڵ�ͼ��
        boolean hasOverdue = userReturn.hasOverdueBooks(count);  // ���� UserReturn ����һ�������������

        if (hasOverdue) {
            // ���������ͼ�飬������ʾ��
            int choice = JOptionPane.showConfirmDialog(this, "��������δ�黹��ͼ�飬�뾡��黹��", "������ʾ", JOptionPane.OK_CANCEL_OPTION);
            if (choice == JOptionPane.OK_OPTION) {
                // ������ʾ��ȷ�Ϻ������һЩ����Ĵ���
                System.out.println("�û�ȷ�����ڹ黹��ʾ");
            }
        }
    }
}


