package com.xiaocan.bookmanage.entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 图书实体类对应数据库t_BookInfo
 */
public class BookInfo {
    private int bookId;
    private String ISBN;
    private String bookName;
    private String inputCode;
    private String author;
    private String KeyWords;
    private String CateCode;
    private String Publisher;
    private String Summary;
    private String ContentInfo;
    private String Price;
    private int StoreCount;
    private String RegDate;
    private String Memo;

    public BookInfo(int bookId, String ISBN, String bookName, String inputCode, String author, String keyWords, String cateCode, String publisher, String summary, String contentInfo, String price, int storeCount, String memo, String regDate) {
        this.bookId = bookId;
        this.ISBN = ISBN;
        this.bookName = bookName;
        this.inputCode = inputCode;
        this.author = author;
        KeyWords = keyWords;
        CateCode = cateCode;
        Publisher = publisher;
        Summary = summary;
        ContentInfo = contentInfo;
        Price = price;
        StoreCount = storeCount;
        RegDate = regDate;
        Memo = memo;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "bookId=" + bookId +
                ", ISBN='" + ISBN + '\'' +
                ", bookName='" + bookName + '\'' +
                ", inputCode='" + inputCode + '\'' +
                ", author='" + author + '\'' +
                ", KeyWords='" + KeyWords + '\'' +
                ", CateCode='" + CateCode + '\'' +
                ", Publisher='" + Publisher + '\'' +
                ", Summary='" + Summary + '\'' +
                ", ContentInfo='" + ContentInfo + '\'' +
                ", Price=" + Price +
                ", StoreCount=" + StoreCount +
                ", RegDate='" + RegDate + '\'' +
                ", Memo='" + Memo + '\'' +
                '}';
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getInputCode() {
        return inputCode;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getKeyWords() {
        return KeyWords;
    }

    public void setKeyWords(String keyWords) {
        KeyWords = keyWords;
    }

    public String getCateCode() {
        return CateCode;
    }

    public void setCateCode(String cateCode) {
        CateCode = cateCode;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    public String getContentInfo() {
        return ContentInfo;
    }

    public void setContentInfo(String contentInfo) {
        ContentInfo = contentInfo;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public int getStoreCount() {
        return StoreCount;
    }

    public void setStoreCount(int storeCount) {
        StoreCount = storeCount;
    }


    public String getRegDate() {
        return RegDate;
    }

    public void setRegDate(String regDate) {
        RegDate = regDate;
    }

    public String getMemo() {
        return Memo;
    }

    public void setMemo(String memo) {
        Memo = memo;
    }
}
