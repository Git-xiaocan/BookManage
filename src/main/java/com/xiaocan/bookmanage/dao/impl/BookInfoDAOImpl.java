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
    public int save(BookInfo bookInfo) {
        /**
         * 因为没有实现图片的上传功能 所有CoverPath 用一个空字符代替
         */
        String sql = "insert into " + Configurations.get(SystemConstant.TB_BOOKINFO) + " values(DEFAULT,'" + bookInfo.getISBN() + "','"
                + bookInfo.getBookName() + "','" + bookInfo.getInputCode() + "','" + bookInfo.getAuthor() + "','" + bookInfo.getKeyWords() + "','" + bookInfo.getCateCode() + "','"
                + bookInfo.getPublisher() + "','" + bookInfo.getSummary() + "','" + bookInfo.getContentInfo() + "','"+" "+ "',"+ bookInfo.getPrice() + "," + bookInfo.getStoreCount() + ",'" +
                bookInfo.getRegDate() + "','" + bookInfo.getMemo() + "');";
        return db_Update(sql);

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
    public int update(BookInfo bookInfo) {
        //CoverPath由于未实现图片上传功能所以暂时用'' 空串代替
        String sql = "update " + Configurations.get(SystemConstant.TB_BOOKINFO) + " set BookName='"+bookInfo.getBookName()+"', InputCode='"+bookInfo.getInputCode()+"', Author='"+bookInfo.getAuthor()+"', KeyWords='"+bookInfo.getKeyWords()+"' ,CateCode='"+bookInfo.getCateCode()+"' ,Publisher='"+bookInfo.getPublisher()+"', Summary='"+bookInfo.getSummary() + "' ,ContentIntro= '"+bookInfo.getContentInfo()+"' ,CoverPath='', Price="+bookInfo.getPrice()+" ,StoreCount='"+bookInfo.getStoreCount()+"' ,RegDate='" +bookInfo.getRegDate()+"', Memo='"+bookInfo.getMemo()+"' where isbn='"+bookInfo.getISBN()+"';";

        System.out.println(sql);
         return db_Update(sql);
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
                    resultSet.getInt("bookId")
                    , resultSet.getString("ISBN")
                    , resultSet.getString("bookName")
                    , resultSet.getString("inputCode")
                    , resultSet.getString("author")
                    , resultSet.getString("keyWords")
                    , resultSet.getString("cateCode")
                    , resultSet.getString("publisher")
                    , resultSet.getString("summary")
                    , resultSet.getString("ContentIntro")
                    , resultSet.getString("price")
                    , resultSet.getInt("storeCount")
                    , resultSet.getString("memo")
                    , resultSet.getString("regDate")
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

                if (fields[i].get(condition) == null || fields[i].get(condition).equals("")) continue;
                if (fields[i].getName().equals("toDate") || fields[i].getName().equals("fromDate")) continue;

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
            if (condition.getToDate() != null && condition.getFromDate() != null && !condition.getFromDate().equals("") && !condition.getToDate().equals(""))
                sql.append(" RegDate between '" + condition.getFromDate() + "' and '" + condition.getToDate() + "';");
            if (sql.toString().endsWith(" or ")) {

                sql = new StringBuilder(sql.substring(0, sql.length() - 3));
            }
/**
 * int bookId, String ISBN, String bookName, String inputCode, String author, String keyWords, String cateCode, String publisher, String summary, String contentInfo, String price, int storeCount, String memo, String regDate) {

 */

            ResultSet resultSet = db_Select(sql.toString());
            List<BookInfo> list = new ArrayList<>();
            if (resultSet == null) return null;
            while (resultSet.next()) {
                BookInfo bookInfo = new BookInfo(
                        resultSet.getInt("bookId")
                        , resultSet.getString("ISBN")
                        , resultSet.getString("bookName")
                        , resultSet.getString("inputCode")
                        , resultSet.getString("author")
                        , resultSet.getString("keyWords")
                        , resultSet.getString("cateCode")
                        , resultSet.getString("publisher")
                        , resultSet.getString("summary")
                        , resultSet.getString("contentInfo")
                        , resultSet.getString("price")
                        , resultSet.getInt("storeCount")
                        , resultSet.getString("memo")
                        , resultSet.getString("regDate")
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