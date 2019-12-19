package com.xiaocan.bookmanage.dao.impl;

import com.xiaocan.bookmanage.dao.BaseDAO;
import com.xiaocan.bookmanage.dao.BookCateDAO;
import com.xiaocan.bookmanage.entity.BookCate;
import com.xiaocan.bookmanage.util.Configurations;
import com.xiaocan.bookmanage.util.SystemConstant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookCateDAOImpl extends BaseDAO implements BookCateDAO {



    /**
     * 将传入的对象保存到数据表中
     *
     * @param bookCate
     */
    @Override
    public void save(BookCate bookCate) {

    }

    /**
     * 根据主键id删除指定的数据
     *
     * @param id
     */
    @Override
    public void delete(int id) {

    }

    /**
     * 修改指定的数据
     *
     * @param bookCate
     */
    @Override
    public void update(BookCate bookCate) {

    }

    /**
     * 根据主键id返回对象数据
     *
     * @param id
     * @return
     */
    @Override
    public BookCate search(int id) {
        return null;
    }

    /**
     * 返回数据表中的所有数据 - 如果数据量比较多 默认返回1000条数据；
     *
     * @return
     */
    @Override
    public List<BookCate> search() throws SQLException {
        return null;
    }

    @Override
    public List<BookCate> GetAllParents() {
        String sql = "select * from " + Configurations.get(SystemConstant.TB_BOOKCATE)  + " where parentId = ?;";

        ResultSet res = db_Select(sql, 0);
        List<BookCate> bookCateList = new ArrayList<>();
        try {
            while (res.next()) {

                bookCateList.add(new BookCate(res.getInt("bookCateId"), res.getInt("ParentId"), res.getString("BookCateCode"), res.getString("BookCateName")));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookCateList;
    }
}
