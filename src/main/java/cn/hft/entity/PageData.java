package cn.hft.entity;

import java.io.Serializable;
import java.util.List;

public class PageData<T> implements Serializable {
    private int pageNum; //当前页
    private int pageSize;//每页数量
    private int totalCount;//总条数
    private int totalPageNum;//总页数
    private List<T> factory;//当前页返回数据

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public List<T> getFactory() {
        return factory;
    }

    public void setFactory(List<T> factory) {
        this.factory = factory;
    }

    public PageData(int pageNum, int pageSize, int totalCount, int totalPageNum, List<T> factory) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPageNum = totalPageNum;
        this.factory = factory;
    }

    @Override
    public String toString() {
        return "PageData{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", totalPageNum=" + totalPageNum +
                ", factory=" + factory +
                '}';
    }

    public PageData() {
    }
}

