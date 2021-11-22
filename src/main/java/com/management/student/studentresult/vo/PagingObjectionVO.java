package com.management.student.studentresult.vo;

import java.util.List;

public class PagingObjectionVO {
    private int currentPage;
    private int totalPage;
    private int pageSize;
    private List<ObjectionVO> objectionVOList;

    public PagingObjectionVO(int currentPage, int totalPage, int pageSize, List<ObjectionVO> objectionVOList) {
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.pageSize = pageSize;
        this.objectionVOList = objectionVOList;
    }

    public PagingObjectionVO() {
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

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<ObjectionVO> getObjectionVOList() {
        return objectionVOList;
    }

    public void setObjectionVOList(List<ObjectionVO> objectionVOList) {
        this.objectionVOList = objectionVOList;
    }
}
