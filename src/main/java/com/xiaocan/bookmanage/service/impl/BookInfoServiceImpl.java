package com.xiaocan.bookmanage.service.impl;

import com.xiaocan.bookmanage.dao.BookInfoDAO;
import com.xiaocan.bookmanage.dao.DAOFactory;
import com.xiaocan.bookmanage.entity.BookInfo;
import com.xiaocan.bookmanage.service.BookInfoService;
import com.xiaocan.bookmanage.util.SystemConstant;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookInfoServiceImpl implements BookInfoService {
    BookInfoDAO bookInfoDao = null;

    @Override
    public List<BookInfo> searchAll() {
        bookInfoDao = (BookInfoDAO)DAOFactory.GetDAO(SystemConstant.DAOIMPL_BOOKINFOIMPL);
        List<BookInfo> list = null;
        try {
            list = bookInfoDao.search();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
