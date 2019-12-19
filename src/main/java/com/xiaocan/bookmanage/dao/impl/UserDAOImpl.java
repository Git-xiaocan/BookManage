package com.xiaocan.bookmanage.dao.impl;

import com.xiaocan.bookmanage.dao.BaseDAO;
import com.xiaocan.bookmanage.dao.UserDAO;
import com.xiaocan.bookmanage.entity.UserInfo;
import com.xiaocan.bookmanage.util.Configurations;
import com.xiaocan.bookmanage.util.SystemConstant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl extends BaseDAO implements UserDAO {


    /**
     * 我了方便实现登录操作  根据指定的logincode返回指定的对象
     *
     * @param LoginCode
     * @return
     */
    @Override
    public UserInfo search(String LoginCode) {
        String sql = "select * from " + Configurations.get(SystemConstant.TB_USER) + " where LoginCode=?;";
        ResultSet resultSet = db_Select(sql, LoginCode);
        UserInfo user = null;
        try {
            if (resultSet.next()) {
                user = new UserInfo(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)

                        , resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * 将传入的对象保存到数据表中
     *
     * @param userInfo
     */
    @Override
    public void save(UserInfo userInfo) {
        String sql = "insert into t_userinfo(LoginCode,LoginPwd,UserName,IsLock ,Memo) values('" + userInfo.getLoginCode() + "','" + userInfo.getLoginPwd() + "','" + userInfo.getUserName() + "'," + userInfo.getLock() + ",'" + userInfo.getMemo() + "')";

        db_Update(sql);
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
     * @param userInfo
     */
    @Override
    public void update(UserInfo userInfo) {

    }

    /**
     * 根据主键id返回对象数据
     *
     * @param id
     * @return
     */
    @Override
    public UserInfo search(int id) {
        return null;
    }

    /**
     * 返回数据表中的所有数据 - 如果数据量比较多 默认返回1000条数据；
     *
     * @return
     */
    @Override
    public List<UserInfo> search() {
        return null;
    }
}
