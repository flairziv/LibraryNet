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


// 用户归还图书及查询借阅信息面板
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
        JButton[] jbt_return = { new JButton("借阅信息"), new JButton("归还图书"), new JButton("查看历史") };
        for (int i = 0; i < jbt_return.length; i++) {
            jbt_return[i].setBounds(200 + i * 150, 20, 100, 40);
            jpanup.add(jbt_return[i]);
        }
        Object[] columnBorrow = { "序号", "ISBN", "书名", "图书类型", "作者", "借阅时间", "应还时间", "实际归还时间" };
        try {
            borrowDate = borrowcon.queryBorrowInfo(UserView.count, UserView.count, false);
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        DefaultTableModel dfttableBorrow = new DefaultTableModel(borrowDate, columnBorrow);
        JTable tableBorrow = new JTable(dfttableBorrow) {
            public boolean isCellEditable(int row, int column) {
                return false;// 表格不允许被编辑
            }
        };
        ListSelectionModel selectionModel = tableBorrow.getSelectionModel();// 创建表格选择器
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// 一次只能选择一个列表索引
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (selectionModel.getValueIsAdjusting()) {
                    if (tableBorrow.getSelectedRow() < 0) {
                        return;
                    }
                    int counts = tableBorrow.getSelectedRow();// 获取你选中的行号（记录）
                    getBorrowName = tableBorrow.getValueAt(counts, 2).toString();// 读取你获取行号的某一列
                    borrow_id = Integer.valueOf(tableBorrow.getValueAt(counts, 0).toString());
                }
            }
        });
        TableUtils.setTable(tableBorrow, dfttableBorrow);
        // 超过范围出现滚动条
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
                dfttableBorrow.setDataVector(borrowDate, columnBorrow);// 设定模型数据和字段,初始化该表
                borrow_id = TableUtils.cancelTableSelected(tableBorrow, borrow_id);
            }
        });
        // 归还图书
        jbt_return[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (borrow_id != -1) {
                    int c = JOptionPane.showConfirmDialog(null, "是否确定归还此图书", "验证操作", JOptionPane.YES_NO_OPTION);
                    if (c == JOptionPane.YES_OPTION) {
                        String returnBook = "inventory=inventory+1";
                        try {
                            // 增加实际归还时间
                            if(borrowcon.returnBorrow(DateUtils.getNewStamp(), borrow_id,bookcon.selectB_name(getBorrowName))) {
                                dfttableBorrow.removeRow(tableBorrow.getSelectedRow());// 归还图书，删除选中的此行
                                JOptionPane.showMessageDialog(null, "您已经成功归还《" + getBorrowName + "》", "操作成功",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        } // 将文本框中的书名传参，以但方便修改“库存量”
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "您没有选中图书！！！", "操作失败", JOptionPane.ERROR_MESSAGE);
                }
                borrow_id = TableUtils.cancelTableSelected(tableBorrow, borrow_id);
            }
        });
        // 点击“查看历史”——查阅个人历史借阅信息(最好最新借阅的排在前边)
        jbt_return[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jbt_return[1].setEnabled(false);
                dfttableBorrow.fireTableDataChanged();
                try {
                    borrowDate = borrowcon.queryBorrowReturnDate(UserView.count, UserView.count, true);
                    dfttableBorrow.setDataVector(borrowDate, columnBorrow);// 设定模型数据和字段,初始化该表
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

    // 检查用户是否有逾期图书
    public boolean hasOverdueBooks(String userId) throws SQLException {
        // 模拟数据库查询逻辑，查询该用户是否有逾期图书
        boolean isOverdue = false;

        // 当前日期，用于比较
        long currentDate = System.currentTimeMillis() / 1000L; // 当前时间戳，单位为秒

        // SQL 查询，查找未归还且逾期的借阅记录
        String query = "SELECT * FROM borrow WHERE r_number = ? AND isReturn = 0 AND dueDate < ?";
        try (Connection conn = DataBaseDAO.connectMySQL();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, userId);
            stmt.setLong(2, currentDate);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    isOverdue = true; // 如果有逾期图书
                }
            }
        }
        return isOverdue;
    }

}

