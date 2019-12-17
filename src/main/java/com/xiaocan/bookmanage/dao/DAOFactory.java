package com.xiaocan.bookmanage.dao;

import com.xiaocan.bookmanage.util.Configurations;

/**
 * 根据指定的DAO 名字 获取配置文件对象的DAO实现类
 * 使用反射实例化并返回实现类对象
 */
public class DAOFactory {
    public static DAO GetDAO(String name){
        String ImplClass = Configurations.get(name);
        try {
            Class<?> daoClass = Class.forName(ImplClass);
            return (DAO)daoClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
