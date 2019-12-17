package com.xiaocan.bookmanage.dao;

import com.xiaocan.bookmanage.entity.UserInfo;

/**
 * 封装了对t_UserInfo表的数据访问
 */
public interface UserDAO extends DAO<UserInfo>{
    /**
     * 我了方便实现登录操作  根据指定的logincode返回指定的对象
     * @param LoginCode
     * @return
     */
    public UserInfo search(String LoginCode);




}
