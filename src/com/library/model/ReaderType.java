package com.library.model;


// �������ͱ��ֶ� ӳ��
public class ReaderType {

    private int rt_id;       // ��������ID
    private String rt_name;  // ������������
    private int maxCount;    // ����������
    private int maxDay;      // ����������

    // Getter �� Setter ����
    public int getRt_id() {
        return rt_id;
    }

    public void setRt_id(int rt_id) {
        this.rt_id = rt_id;
    }

    public String getRt_name() {
        return rt_name;
    }

    public void setRt_name(String rt_name) {
        this.rt_name = rt_name;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public int getMaxDay() {
        return maxDay;
    }

    public void setMaxDay(int maxDay) {
        this.maxDay = maxDay;
    }

    // ��д toString �������������ʱ���������Ϣ
    @Override
    public String toString() {
        return "ReaderType{" +
                "rt_id=" + rt_id +
                ", rt_name='" + rt_name + '\'' +
                ", maxCount=" + maxCount +
                ", maxDay=" + maxDay +
                '}';
    }
}

