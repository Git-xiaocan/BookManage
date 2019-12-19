package com.xiaocan.bookmanage.dao.impl;

import com.xiaocan.bookmanage.entity.BookSearchCondition;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookInfoDAOImplTest {

    @Test
    public void search() {

        BookSearchCondition bookSearchCondition = new BookSearchCondition();
        bookSearchCondition.setBookName("1231");
        bookSearchCondition.setKeyWords("432423");
        bookSearchCondition.setFromDate("resrsdf");
        bookSearchCondition.setIsbn("dasd");
        bookSearchCondition.setToDate("fadsdasd");
        BookInfoDAOImpl bookInfoDAO = new BookInfoDAOImpl();
        bookInfoDAO.search(bookSearchCondition);

    }
}