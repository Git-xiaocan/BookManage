package com.xiaocan.bookmanage.service;

import com.xiaocan.bookmanage.entity.UserInfo;

public interface LoginService {
    UserInfo login(String LoginCode, String passwod);
}
