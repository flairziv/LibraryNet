package com.library.model;


// 读者类型表字段 映射
public class ReaderType {

    private int rt_id;       // 读者类型ID
    private String rt_name;  // 读者类型名称
    private int maxCount;    // 最大借书数量
    private int maxDay;      // 最大借阅天数

    // Getter 和 Setter 方法
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

    // 重写 toString 方法，方便调试时输出对象信息
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

