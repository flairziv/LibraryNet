package com.library.view;

import com.library.controller.BookController;
import com.library.controller.BorrowController;
import com.library.dao.DataBaseDAO;
import com.library.util.DateUtils;
import com.library.util.TableUtils;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


// �û��黹ͼ�鼰��ѯ������Ϣ���
public class UserReturn {
    BookController bookcon = new BookController();
    BorrowController borrowcon = new BorrowController();
    UserBook userBook =new UserBook();
    Object[][] borrowDate = null;
    String getBorrowName, reader;
    int borrow_id = -1;

    protected JPanel addPanel1() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel jpanup = new JPanel();
        jpanup.setLayout(null);
        panel.setPreferredSize(new Dimension(1000, 700));
        jpanup.setPreferredSize(new Dimension(1000, 80));
        JButton[] jbt_return = { new JButton("������Ϣ"), new JButton("�黹ͼ��"), new JButton("�鿴��ʷ") };
        for (int i = 0; i < jbt_return.length; i++) {
            jbt_return[i].setBounds(200 + i * 150, 20, 100, 40);
            jpanup.add(jbt_return[i]);
        }
        Object[] columnBorrow = { "���", "ISBN", "����", "ͼ������", "����", "����ʱ��", "Ӧ��ʱ��", "ʵ�ʹ黹ʱ��" };
        try {
            borrowDate = borrowcon.queryBorrowInfo(UserView.count, UserView.count, false);
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        DefaultTableModel dfttableBorrow = new DefaultTableModel(borrowDate, columnBorrow);
        JTable tableBorrow = new JTable(dfttableBorrow) {
            public boolean isCellEditable(int row, int column) {
                return false;// ��������༭
            }
        };
        ListSelectionModel selectionModel = tableBorrow.getSelectionModel();// �������ѡ����
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// һ��ֻ��ѡ��һ���б�����
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (selectionModel.getValueIsAdjusting()) {
                    if (tableBorrow.getSelectedRow() < 0) {
                        return;
                    }
                    int counts = tableBorrow.getSelectedRow();// ��ȡ��ѡ�е��кţ���¼��
                    getBorrowName = tableBorrow.getValueAt(counts, 2).toString();// ��ȡ���ȡ�кŵ�ĳһ��
                    borrow_id = Integer.valueOf(tableBorrow.getValueAt(counts, 0).toString());
                }
            }
        });
        TableUtils.setTable(tableBorrow, dfttableBorrow);
        // ������Χ���ֹ�����
        JScrollPane scrollPane = new JScrollPane(tableBorrow);
        panel.add(jpanup, BorderLayout.NORTH);
        panel.add(scrollPane);
        jbt_return[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jbt_return[1].setEnabled(true);
                dfttableBorrow.setRowCount(0);
                dfttableBorrow.fireTableDataChanged();
                try {
                    borrowDate = borrowcon.queryBorrowInfo(UserView.count, UserView.count, false);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                dfttableBorrow.setDataVector(borrowDate, columnBorrow);// �趨ģ�����ݺ��ֶ�,��ʼ���ñ�
                borrow_id = TableUtils.cancelTableSelected(tableBorrow, borrow_id);
            }
        });
        // �黹ͼ��
        jbt_return[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (borrow_id != -1) {
                    int c = JOptionPane.showConfirmDialog(null, "�Ƿ�ȷ���黹��ͼ��", "��֤����", JOptionPane.YES_NO_OPTION);
                    if (c == JOptionPane.YES_OPTION) {
                        String returnBook = "inventory=inventory+1";
                        try {
                            // ����ʵ�ʹ黹ʱ��
                            if(borrowcon.returnBorrow(DateUtils.getNewStamp(), borrow_id,bookcon.selectB_name(getBorrowName))) {
                                dfttableBorrow.removeRow(tableBorrow.getSelectedRow());// �黹ͼ�飬ɾ��ѡ�еĴ���
                                JOptionPane.showMessageDialog(null, "���Ѿ��ɹ��黹��" + getBorrowName + "��", "�����ɹ�",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        } // ���ı����е��������Σ��Ե������޸ġ��������
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "��û��ѡ��ͼ�飡����", "����ʧ��", JOptionPane.ERROR_MESSAGE);
                }
                borrow_id = TableUtils.cancelTableSelected(tableBorrow, borrow_id);
            }
        });
        // ������鿴��ʷ���������ĸ�����ʷ������Ϣ(������½��ĵ�����ǰ��)
        jbt_return[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jbt_return[1].setEnabled(false);
                dfttableBorrow.fireTableDataChanged();
                try {
                    borrowDate = borrowcon.queryBorrowReturnDate(UserView.count, UserView.count, true);
                    dfttableBorrow.setDataVector(borrowDate, columnBorrow);// �趨ģ�����ݺ��ֶ�,��ʼ���ñ�
                    tableBorrow.setModel(dfttableBorrow);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                borrow_id = TableUtils.cancelTableSelected(tableBorrow, borrow_id);
            }
        });
        return panel;
    }

    // ����û��Ƿ�������ͼ��
    public boolean hasOverdueBooks(String userId) throws SQLException {
        // ģ�����ݿ��ѯ�߼�����ѯ���û��Ƿ�������ͼ��
        boolean isOverdue = false;

        // ��ǰ���ڣ����ڱȽ�
        long currentDate = System.currentTimeMillis() / 1000L; // ��ǰʱ�������λΪ��

        // SQL ��ѯ������δ�黹�����ڵĽ��ļ�¼
        String query = "SELECT * FROM borrow WHERE r_number = ? AND isReturn = 0 AND dueDate < ?";
        try (Connection conn = DataBaseDAO.connectMySQL();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, userId);
            stmt.setLong(2, currentDate);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    isOverdue = true; // ���������ͼ��
                }
            }
        }
        return isOverdue;
    }

}

