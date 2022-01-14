package ru.job4j.pojo;

public class Book {
    private String title;
    private int pageNum;

    public Book(String title, int pageNum) {
        this.title = title;
        this.pageNum = pageNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
