package com.xiaocan.bookmanage.service;

public class BookSearchService {

    private String ISBN;
    private String BookName;
    private String KeyWords;

    public BookSearchService(String ISBN, String bookName, String keyWords) {
        this.ISBN = ISBN;
        BookName = bookName;
        KeyWords = keyWords;
    }
}
