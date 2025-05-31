package com.library.controller;

import com.library.dao.BookDAO;

import java.util.Vector;


// 分页查询的控制器
public class PageQueryController {
    public static int currentPageIndex = 1;             // 当前页码
    public int pageIndex;
    protected int countPerPage = 15;                    // 每页显示条数
    public int pageCount;                               // 总页数
    protected int recordCount;                          // 总记录条数
    protected static Vector<Vector<Object>> bigPageVector = new Vector<Vector<Object>>();
    protected Vector<Vector<Object>> smallPageVector = new Vector<Vector<Object>>();
    BookDAO bookDao = new BookDAO();

    /**
     * 无参构造方法方便匿名调用方法
     */
    public PageQueryController(){

    }
    /**
     * 传入指定页码的构造函数，参看第几页
     */
    public PageQueryController(Vector<Vector<Object>> vec) {
        bigPageVector=vec;

        //设置页数
        if (bigPageVector.size() % countPerPage == 0) {
            pageCount = bigPageVector.size() / countPerPage;
        } else {
            pageCount = (bigPageVector.size() / countPerPage) + 1;
        }
    }

    /**
     * 此方法供调用，根据当前页，筛选记录
     */
    public Vector<Vector<Object>> selectCount() {
        recordCount = bigPageVector.size();
        for (int i = (currentPageIndex - 1) * countPerPage; i < currentPageIndex * countPerPage && i < recordCount; i++) {
            smallPageVector.add(bigPageVector.get(i));
        }
        return smallPageVector;
    }

    /**
     * 确切的获取当前页的记录，返回一个Vector<Vector<Object>>列表
     *
     * @return
     */
    public Vector<Vector<Object>> setCurrentPageIndex() {
        currentPageIndex=1;
        return selectCount();
    }

    /**
     * 上一页
     *
     * @return
     */
    public Vector<Vector<Object>> previousPage() {
        if (currentPageIndex > 1) {
            currentPageIndex--;
            System.out.println("当前页:" + currentPageIndex);
            pageIndex=currentPageIndex;
        }
        return selectCount();
    }

    /**
     * 下一页
     */
    public Vector<Vector<Object>> nextPage() {

        if (currentPageIndex < pageCount) {
            currentPageIndex++;
            pageIndex=currentPageIndex;
            System.out.println("当前页:" + currentPageIndex);
        }
        return selectCount();
    }

    /**
     * 尾页
     */
    public Vector<Vector<Object>> lastPage() {
        currentPageIndex =  pageCount;
        return selectCount();
    }

    /**
     * 跳转页数
     */
    public Vector<Vector<Object>> jumpPage(int page) {
        currentPageIndex =  page;
        return selectCount();
    }

    /**
     * 返回总页数
     */
    public int pageCount() {
        return pageCount;
    }

}

