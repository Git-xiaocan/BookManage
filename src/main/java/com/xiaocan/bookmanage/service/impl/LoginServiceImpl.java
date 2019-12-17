package com.xiaocan.bookmanage.service.impl;

import com.xiaocan.bookmanage.dao.DAOFactory;
import com.xiaocan.bookmanage.dao.UserDAO;
import com.xiaocan.bookmanage.entity.UserInfo;
import com.xiaocan.bookmanage.service.LoginService;
import com.xiaocan.bookmanage.util.SystemConstant;

public class LoginServiceImpl implements LoginService {
    //操作数据库需要使用到dao对象
    private UserDAO userDAO = null;

    public LoginServiceImpl() {

        this.userDAO = (UserDAO) DAOFactory.GetDAO(SystemConstant.DAOIMPL_USERDAOIMPL);
    }

    /**
     * 通过数据库验证传入的用户名或者密码是否合法
     *
     * @param LoginCode
     * @param passwod
     * @return 如果身份合法返回对应的用户对象
     */
    @Override
    public UserInfo login(String LoginCode, String passwod) {

        UserInfo userInfo = userDAO.search(LoginCode);
        if (null == userInfo) return null;
        if (passwod.equals(userInfo.getLoginPwd())) {
            return userInfo;
        }
        return null;
    }
}
