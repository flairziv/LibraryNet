package com.library.controller;

import com.library.dao.BookDAO;

import java.util.Vector;


// ��ҳ��ѯ�Ŀ�����
public class PageQueryController {
    public static int currentPageIndex = 1;             // ��ǰҳ��
    public int pageIndex;
    protected int countPerPage = 15;                    // ÿҳ��ʾ����
    public int pageCount;                               // ��ҳ��
    protected int recordCount;                          // �ܼ�¼����
    protected static Vector<Vector<Object>> bigPageVector = new Vector<Vector<Object>>();
    protected Vector<Vector<Object>> smallPageVector = new Vector<Vector<Object>>();
    BookDAO bookDao = new BookDAO();

    /**
     * �޲ι��췽�������������÷���
     */
    public PageQueryController(){

    }
    /**
     * ����ָ��ҳ��Ĺ��캯�����ο��ڼ�ҳ
     */
    public PageQueryController(Vector<Vector<Object>> vec) {
        bigPageVector=vec;

        //����ҳ��
        if (bigPageVector.size() % countPerPage == 0) {
            pageCount = bigPageVector.size() / countPerPage;
        } else {
            pageCount = (bigPageVector.size() / countPerPage) + 1;
        }
    }

    /**
     * �˷��������ã����ݵ�ǰҳ��ɸѡ��¼
     */
    public Vector<Vector<Object>> selectCount() {
        recordCount = bigPageVector.size();
        for (int i = (currentPageIndex - 1) * countPerPage; i < currentPageIndex * countPerPage && i < recordCount; i++) {
            smallPageVector.add(bigPageVector.get(i));
        }
        return smallPageVector;
    }

    /**
     * ȷ�еĻ�ȡ��ǰҳ�ļ�¼������һ��Vector<Vector<Object>>�б�
     *
     * @return
     */
    public Vector<Vector<Object>> setCurrentPageIndex() {
        currentPageIndex=1;
        return selectCount();
    }

    /**
     * ��һҳ
     *
     * @return
     */
    public Vector<Vector<Object>> previousPage() {
        if (currentPageIndex > 1) {
            currentPageIndex--;
            System.out.println("��ǰҳ:" + currentPageIndex);
            pageIndex=currentPageIndex;
        }
        return selectCount();
    }

    /**
     * ��һҳ
     */
    public Vector<Vector<Object>> nextPage() {

        if (currentPageIndex < pageCount) {
            currentPageIndex++;
            pageIndex=currentPageIndex;
            System.out.println("��ǰҳ:" + currentPageIndex);
        }
        return selectCount();
    }

    /**
     * βҳ
     */
    public Vector<Vector<Object>> lastPage() {
        currentPageIndex =  pageCount;
        return selectCount();
    }

    /**
     * ��תҳ��
     */
    public Vector<Vector<Object>> jumpPage(int page) {
        currentPageIndex =  page;
        return selectCount();
    }

    /**
     * ������ҳ��
     */
    public int pageCount() {
        return pageCount;
    }

}

