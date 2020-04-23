package com.myd.entity;

import java.util.List;

/*
 * 传入的参数：data、pageIndex，rows、pageSize
 * 
 */
public class PageBean<T> {
    private List<T> data; // 页面中显示的分页列表数据
    private int pageIndex = 1; // 当前页的页码，默认页码是 1
    private int totalPage; // 总的页码数量
    private int nextPageIndex; // 下一页的页码
    private int prePageIndex; // 上一页的页码
    private int rows; // 总的行数
    private int pageSize = 10; // 每页显示的行数
    private int beginIndex = 0; // 每页开始始的行号

    public int getBeginIndex() {
        return (pageIndex-1)*pageSize;
    }

    public void setBeginIndex(int beginIndex) {
        this.beginIndex = beginIndex;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setPageIndex(String s){
        if(s==null || "".equals(s)){
            pageIndex = 1;
        }else{
            pageIndex = Integer.parseInt(s);
        }
    }

    public int getTotalPage() {
        totalPage = (rows-1)/pageSize+1;
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getNextPageIndex() {
        if(pageIndex == totalPage)
        {
            nextPageIndex = pageIndex;
        }else{
            nextPageIndex = pageIndex + 1;
        }
        return nextPageIndex;
    }

    public void setNextPageIndex(int nextPageIndex) {
        this.nextPageIndex = nextPageIndex;
    }

    public int getPrePageIndex() {
        if(pageIndex == 1){
            prePageIndex = 1;
        }else{
            prePageIndex = pageIndex -1;
        }
        return prePageIndex;
    }

    public void setPrePageIndex(int prePageIndex) {
        this.prePageIndex = prePageIndex;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
