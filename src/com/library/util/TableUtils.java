package com.library.util;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TableUtils {
    static JTable table;
    static DefaultTableModel dfttable;
    /**
     * �����ı�񷽷�
     */
    public static JTable setTable(JTable table, DefaultTableModel dfttable) {
        TableUtils.table = table;
        TableUtils.dfttable = dfttable;
        // ���ñ�����ݾ�����ʾ
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table.setDefaultRenderer(Object.class, renderer);
        //���ñ���и�
        table.setRowHeight(20);
        //���ñ�����в����ƶ�
        table.getTableHeader().setReorderingAllowed(false); // �������в����ƶ�
        return table;
    }

    /**
     * ȡ�����ѡ��״̬
     * ������intֵ��Ϊ-1
     */
    public static int cancelTableSelected(JTable table,int id) {
        table.clearSelection();  //ȡ��ѡ��
        return id=-1;
    }

    /**
     * ȡ�����ѡ��״̬
     * ������Stringֵ��Ϊnull
     */
    public static String setNull(JTable table, String str) {
        table.clearSelection();
        return str=null;
    }

}

