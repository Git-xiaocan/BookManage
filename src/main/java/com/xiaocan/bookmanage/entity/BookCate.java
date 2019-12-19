package com.xiaocan.bookmanage.entity;

public class BookCate {
    private int bookCateId;
    private int parentId;
    private String bookCateCode;
    private  String bookCateName;

    public BookCate(int bookCateId, int parentId, String bookCateCode, String bookCateName) {
        this.bookCateId = bookCateId;
        this.parentId = parentId;
        this.bookCateCode = bookCateCode;
        this.bookCateName = bookCateName;
    }

    @Override
    public String toString() {
        return "BookCate{" +
                "bookCateId=" + bookCateId +
                ", parentId=" + parentId +
                ", bookCateCode='" + bookCateCode + '\'' +
                ", bookCateName='" + bookCateName + '\'' +
                '}';
    }

    public int getBookCateId() {
        return bookCateId;
    }

    public void setBookCateId(int bookCateId) {
        this.bookCateId = bookCateId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getBookCateCode() {
        return bookCateCode;
    }

    public void setBookCateCode(String bookCateCode) {
        this.bookCateCode = bookCateCode;
    }

    public String getBookCateName() {
        return bookCateName;
    }

    public void setBookCateName(String bookCateName) {
        this.bookCateName = bookCateName;
    }
}
