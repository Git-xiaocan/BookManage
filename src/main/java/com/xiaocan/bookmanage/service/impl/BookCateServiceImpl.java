package com.xiaocan.bookmanage.service.impl;

import com.xiaocan.bookmanage.dao.BookCateDAO;
import com.xiaocan.bookmanage.dao.BookInfoDAO;
import com.xiaocan.bookmanage.dao.DAOFactory;
import com.xiaocan.bookmanage.dao.impl.BookCateDAOImpl;
import com.xiaocan.bookmanage.dao.impl.BookInfoDAOImpl;
import com.xiaocan.bookmanage.entity.BookCate;
import com.xiaocan.bookmanage.entity.BookInfo;
import com.xiaocan.bookmanage.entity.BookSearchCondition;
import com.xiaocan.bookmanage.service.BookCateService;
import com.xiaocan.bookmanage.util.Configurations;
import com.xiaocan.bookmanage.util.SystemConstant;

import java.util.List;

public class BookCateServiceImpl implements BookCateService {
    BookCateDAO bookCateDAO = null;


    public BookCateServiceImpl() {
        this.bookCateDAO = (BookCateDAOImpl)DAOFactory.GetDAO(SystemConstant.DAOIMPL_BOOKCATEIMPL);

    }

    @Override
    public List<BookCate> getAllParentCate() {
        return bookCateDAO.GetAllParents();
    }


}
