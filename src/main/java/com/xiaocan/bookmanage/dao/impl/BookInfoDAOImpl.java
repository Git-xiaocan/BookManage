package com.xiaocan.bookmanage.dao.impl;

import com.sun.corba.se.impl.presentation.rmi.IDLNameTranslatorImpl;
import com.xiaocan.bookmanage.dao.BaseDAO;
import com.xiaocan.bookmanage.dao.BookInfoDAO;
import com.xiaocan.bookmanage.entity.BookInfo;
import com.xiaocan.bookmanage.entity.BookSearchCondition;
import com.xiaocan.bookmanage.util.Configurations;
import com.xiaocan.bookmanage.util.SystemConstant;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookInfoDAOImpl extends BaseDAO implements BookInfoDAO {
    /**
     * 将传入的对象保存到数据表中
     *
     * @param bookInfo
     */
    @Override
    public void save(BookInfo bookInfo) {
        String sql = "insert into " + SystemConstant.TB_BOOKINFO + " values(default,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        db_Update(
                sql,
                bookInfo.getISBN(),
                bookInfo.getBookName(),
                bookInfo.getInputCode(),
                bookInfo.getAuthor(),
                bookInfo.getKeyWords(),
                bookInfo.getCateCode(),
                bookInfo.getPublisher(),
                bookInfo.getSummary(),
                bookInfo.getContentInfo(),
                bookInfo.getPrice(),
                bookInfo.getStoreCount(),
                bookInfo.getRegDate(),
                bookInfo.getMemo()

        );

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
     * @param bookInfo
     */
    @Override
    public void update(BookInfo bookInfo) {


    }

    /**
     * 根据主键id返回对象数据
     *
     * @param id
     * @return
     */
    @Override
    public BookInfo search(int id) {
        return null;
    }

    /**
     * 返回数据表中的所有数据 - 如果数据量比较多 默认返回1000条数据；
     *
     * @return
     */
    @Override
    public List<BookInfo> search() throws SQLException {
        String sql = "select * from " + Configurations.get(SystemConstant.TB_BOOKINFO) + ";";
        ResultSet resultSet = db_Select(sql);
        List<BookInfo> list = new ArrayList<>();
        while (resultSet.next()) {
            BookInfo bookInfo = new BookInfo(
                    resultSet.getInt(1)
                    , resultSet.getString(2)
                    , resultSet.getString(3)
                    , resultSet.getString(4)
                    , resultSet.getString(5)
                    , resultSet.getString(6)
                    , resultSet.getString(7)
                    , resultSet.getString(8)
                    , resultSet.getString(9)
                    , resultSet.getString(10)
                    , resultSet.getString(11)
                    , resultSet.getInt(12)
                    , resultSet.getString(13)
                    , resultSet.getString(14)
            );

            list.add(bookInfo);


        }


        return list;
    }

    @Override
    public List<BookInfo> search(BookSearchCondition condition) {
        if (condition == null) return null;
        StringBuilder sql = new StringBuilder("select * from ");
        sql.append(Configurations.get(SystemConstant.TB_BOOKINFO));
        sql.append(" where ");
        Field[] fields = BookSearchCondition.class.getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);

                if (fields[i].get(condition) == null||fields[i].get(condition).equals("")) continue;
                if(fields[i].getName().equals("toDate")||fields[i].getName().equals("fromDate")) continue;
//////                if (fields[i].getName().equals("fromDate")) {
//////
//////
//////
//////                } else
                if (fields[i].getType().equals(String.class)) {

                    sql.append(fields[i].getName());
                    sql.append(" like ");
                    sql.append("'%" + fields[i].get(condition) + "%'");
                } else if (fields[i].getType().equals(Integer.class)) {
                    sql.append(fields[i].getName());
                    sql.append("=");
                    sql.append(fields[i].get(condition));
                }
                if (i <= fields.length - 1) {
                    sql.append(" or ");

                }



            }
            if( condition.getToDate() != null && condition.getFromDate() != null&&!condition.getFromDate().equals("")&& !condition.getToDate().equals(""))
            sql.append(" RegDate between '" + condition.getFromDate() + "' and '" + condition.getToDate() + "';");
           if(sql.toString().endsWith(" or ")){

               sql = new StringBuilder(sql.substring(0,sql.length()-3));
           }


            ResultSet resultSet = db_Select(sql.toString());
            List<BookInfo> list = new ArrayList<>();
            if(resultSet==null) return null;
            while (resultSet.next()) {
                BookInfo bookInfo = new BookInfo(
                        resultSet.getInt(1)
                        , resultSet.getString(2)
                        , resultSet.getString(3)
                        , resultSet.getString(4)
                        , resultSet.getString(5)
                        , resultSet.getString(6)
                        , resultSet.getString(7)
                        , resultSet.getString(8)
                        , resultSet.getString(9)
                        , resultSet.getString(10)
                        , resultSet.getString(11)
                        , resultSet.getInt(12)
                        , resultSet.getString(13)
                        , resultSet.getString(14)
                );

                list.add(bookInfo);


            }

            return list;
        } catch (IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
//
        return null;
    }
}
