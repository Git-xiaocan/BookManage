package com.xiaocan.bookmanage.dao;

import com.xiaocan.bookmanage.entity.BookCate;
import java.util.List;

/**
 * 封装对t_Bookcate表的数据访问
 */
public interface BookCateDAO extends  DAO<BookCate>{
    /**
     * 根据ID 获取父分类
     * @param
     * @return
     */
    List<BookCate> GetAllParents();
}
