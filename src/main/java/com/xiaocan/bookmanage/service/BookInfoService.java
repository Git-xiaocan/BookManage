package com.xiaocan.bookmanage.service;

import com.xiaocan.bookmanage.dao.BookInfoDAO;
import com.xiaocan.bookmanage.entity.BookInfo;
import com.xiaocan.bookmanage.entity.BookSearchCondition;

import java.util.List;

public interface BookInfoService {

    public List<BookInfo> searchAll();

    List<BookInfo> FindByCondition(BookSearchCondition condition);
}
