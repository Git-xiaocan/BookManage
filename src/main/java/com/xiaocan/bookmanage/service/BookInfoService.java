package com.xiaocan.bookmanage.service;

import com.xiaocan.bookmanage.dao.BookInfoDAO;
import com.xiaocan.bookmanage.entity.BookInfo;

import java.util.List;

public interface BookInfoService {

    public List<BookInfo> searchAll();
}
