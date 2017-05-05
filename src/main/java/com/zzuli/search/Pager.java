package com.zzuli.search;

import com.zzuli.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Pager<T> implements Serializable {

    private static final long serialVersionUID = -8741766802354222579L;
    private int pageSize; // 每页显示多少条记录
    private int currentPage; //当前第几页数据
    private int totalRecord; // 一共多少条记录
    private int totalPage; // 一共多少页记录
    private boolean isNext;//是否有下一页
    private boolean isPrev;//是否有上一页
    private List<T> dataList; //要显示的数据
    private List<Integer> pageIndex;//下标

    public Pager() {
    }

    public Pager(int pageNum, int pageSize, int totalRecord, List<T> sourceList) {
        if (sourceList == null || sourceList.isEmpty()) {
            return;
        }
        // 总记录条数
        this.totalRecord = totalRecord;
        // 每页显示多少条记录
        this.pageSize = pageSize;
        //获取总页数
        this.totalPage = this.totalRecord / this.pageSize;
        if (this.totalRecord % this.pageSize != 0) {
            this.totalPage = this.totalPage + 1;
        }
        // 当前第几页数据
        this.currentPage = this.totalPage < pageNum ? this.totalPage : pageNum;

        //是否有上一页
        this.isPrev = this.currentPage > 1;

        //是否有下一页
        this.isNext = this.currentPage < this.totalPage;

        //开始页码
        int pageFromIndex = (this.currentPage - Constants.PAGE_NUMBER) > 0 ? (this.currentPage - Constants.PAGE_NUMBER) : 1;

        //结束页码
        int pageToIndex = (this.currentPage + Constants.PAGE_NUMBER) > totalPage ? totalPage : (this.currentPage + Constants.PAGE_NUMBER);

        if (pageToIndex - pageFromIndex < 2 * Constants.PAGE_NUMBER) {
            pageToIndex = 2 * Constants.PAGE_NUMBER > totalPage ? totalPage : 2 * Constants.PAGE_NUMBER;
        }
        //页码下标
        this.pageIndex = new ArrayList<Integer>();
        for (int i = pageFromIndex; i <= pageToIndex; i++) {
            this.pageIndex.add(i);
        }
        this.dataList = sourceList;
    }

    public List<Integer> getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(List<Integer> pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public boolean isNext() {
        return isNext;
    }

    public void setNext(boolean next) {
        isNext = next;
    }

    public boolean isPrev() {
        return isPrev;
    }

    public void setPrev(boolean prev) {
        isPrev = prev;
    }
}
