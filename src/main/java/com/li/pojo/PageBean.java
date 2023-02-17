package com.li.pojo;

import java.util.List;

public class PageBean<T> {

    private List<T> rows;
    private int totalCount;

    public PageBean() {
    }

    public PageBean(List<T> rows, int totalCount) {
        this.rows = rows;
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> row) {
        this.rows = row;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "rows=" + rows +
                ", totalCount=" + totalCount +
                '}';
    }
}
