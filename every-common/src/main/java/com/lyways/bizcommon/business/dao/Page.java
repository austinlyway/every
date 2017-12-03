package com.lyways.bizcommon.business.dao;

import java.util.List;

/**
 * @author: austin
 * @createDate: 10/25/17
 */
public class Page<T> {

    private int pageSize = 10;

    private int pageIndex;

    private long totalCount;

    private int totalPage;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        if(this.pageSize== 0 || this.totalCount == 0) {
            return 0;
        } else {
            this.totalPage = (int)(this.totalCount / this.pageSize);
            if(this.totalCount % this.pageSize != 0) {
                ++ this.totalPage;
            }
            return this.totalPage;
        }
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    private List<T> dataList;

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public int getOffset(){
        return pageIndex > 0 ? (pageIndex - 1) * pageSize : 0;
    }
}
