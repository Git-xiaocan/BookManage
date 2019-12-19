package com.xiaocan.bookmanage.util;

public class SystemConstant {
    /**
     * mysql 链接配置常量 Driver
     */
    public static final  String DB_DRIVER = "db.driver";
    public static final String DB_URL= "db.url";
    public static final String DB_USER = "db.user";
    public static final String DB_PWD = "db.pwd";


    /**
     *数据库表格项 常量
     */
    public static final  String TB_USER = "tb.user";
    public static final  String TB_BOOKINFO = "tb.bookinfo";
    public static final  String TB_BOOKCATE = "tb.bookcate";

/**
 * DAO配置
 */
    public static final String DAOIMPL_USERDAOIMPL = "dao.UserDAOImpl";
    public static final String DAOIMPL_BOOKINFOIMPL = "dao.BookInfoDAOImpl";
    public static final String DAOIMPL_BOOKCATEIMPL = "dao.BookCateDAOImpl";


    /**
     * entity常量
     */
    public static final String ENTITY_USERINFO= "entity.UserInfo";
    public static final String ENTITY_BOOKINFO= "entity.BookInfo";
    public static final String ENTITY_BOOK_SEARCH_CONDITION = "entity.BookSearchCondition";


    /**
     * service配置
     */
    public static final String SERVICE_LOGIN = "service.login";
    public static final String SERVICE_BOOK = "service.book";
}
