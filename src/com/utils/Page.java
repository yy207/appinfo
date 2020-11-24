package com.utils;

public class Page {
    private Integer totalCount;
    private Integer totalPageCount;
    private Integer currentPageNo;
    private Integer pageSize = 5;
    private Integer pageOffset = 0;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalPageCount = totalCount % this.pageSize == 0 ?
                totalCount / this.pageSize : totalCount / this.pageSize + 1;
        this.totalCount = totalCount;
    }

    public Integer getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(Integer totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public Integer getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(Integer currentPageNo) {
        this.currentPageNo = currentPageNo;
        this.pageOffset = ( this.currentPageNo -1 ) * this.pageSize ;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(Integer pageOffset) {
        this.pageOffset = pageOffset;
    }
}
