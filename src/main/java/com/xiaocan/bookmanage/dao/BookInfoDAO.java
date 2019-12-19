package com.xiaocan.bookmanage.dao;

import com.xiaocan.bookmanage.entity.BookInfo;
import com.xiaocan.bookmanage.entity.BookSearchCondition;

import java.util.List;


public interface BookInfoDAO extends DAO<BookInfo> {

List<BookInfo> search(BookSearchCondition condition);
}
