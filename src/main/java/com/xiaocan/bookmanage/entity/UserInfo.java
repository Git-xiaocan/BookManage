package com.xiaocan.bookmanage.entity;

public class UserInfo {
    private int  UserId;//用户ID 唯一
    private String LoginCode;// 登录名
    private String LoginPwd ;//登录密码
    private String UserName;//真实姓名
    private String Lock;//是否锁定int
    private String Memo;//备注信息

    public UserInfo(int userId, String loginCode, String loginPwd, String userName, String lock, String memo) {
        UserId = userId;
        LoginCode = loginCode;
        LoginPwd = loginPwd;
        UserName = userName;
        Lock = lock;
        Memo = memo;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getLoginCode() {
        return LoginCode;
    }

    public void setLoginCode(String loginCode) {
        LoginCode = loginCode;
    }

    public String getLoginPwd() {
        return LoginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        LoginPwd = loginPwd;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getLock() {
        return Lock;
    }

    public void setLock(String lock) {
        Lock = lock;
    }

    public String getMemo() {
        return Memo;
    }

    public void setMemo(String memo) {
        Memo = memo;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "UserId=" + UserId +
                ", LoginCode='" + LoginCode + '\'' +
                ", LoginPwd='" + LoginPwd + '\'' +
                ", UserName='" + UserName + '\'' +
                ", Lock='" + Lock + '\'' +
                ", Memo='" + Memo + '\'' +
                '}';
    }
}
