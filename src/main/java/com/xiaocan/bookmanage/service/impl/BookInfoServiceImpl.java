package com.xiaocan.bookmanage.service.impl;

import com.xiaocan.bookmanage.dao.BookInfoDAO;
import com.xiaocan.bookmanage.dao.DAOFactory;
import com.xiaocan.bookmanage.entity.BookInfo;
import com.xiaocan.bookmanage.entity.BookSearchCondition;
import com.xiaocan.bookmanage.service.BookInfoService;
import com.xiaocan.bookmanage.util.SystemConstant;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookInfoServiceImpl implements BookInfoService {
    BookInfoDAO bookInfoDao = null;

    public BookInfoServiceImpl() {
        bookInfoDao = (BookInfoDAO) DAOFactory.GetDAO(SystemConstant.DAOIMPL_BOOKINFOIMPL);
    }

    @Override
    public List<BookInfo> searchAll() {

        List<BookInfo> list = null;
        try {
            list = bookInfoDao.search();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<BookInfo> FindByCondition(BookSearchCondition condition) {
        return bookInfoDao.search(condition);
    }

    @Override
    public int save(BookInfo book) {
        return bookInfoDao.save(book);
    }

    @Override
    public int upData(BookInfo book) {
        return bookInfoDao.update(book);
    }
}
