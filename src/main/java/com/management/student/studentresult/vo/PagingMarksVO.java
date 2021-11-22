package com.management.student.studentresult.vo;

import java.util.List;

public class PagingMarksVO {
    private int currentPage;
    private int totalPage;
    private int pageSize;
    private List<MarksVO> marksVOList;

    public PagingMarksVO(int currentPage, int totalPage, int pageSize, List<MarksVO> marksVOList) {
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.marksVOList = marksVOList;
        this.pageSize = pageSize;
    }

    public PagingMarksVO() {
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<MarksVO> getMarksVOList() {
        return marksVOList;
    }

    public void setMarksVOList(List<MarksVO> marksVOList) {
        this.marksVOList = marksVOList;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
