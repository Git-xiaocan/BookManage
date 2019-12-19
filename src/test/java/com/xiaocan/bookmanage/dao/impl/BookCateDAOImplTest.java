package com.xiaocan.bookmanage.dao.impl;

import com.xiaocan.bookmanage.dao.DAOFactory;
import com.xiaocan.bookmanage.entity.BookCate;
import com.xiaocan.bookmanage.util.Configurations;
import com.xiaocan.bookmanage.util.SystemConstant;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BookCateDAOImplTest {

    @Test
    public void search() {
        BookCateDAOImpl bookCateDAO = null;
        try {
            bookCateDAO = (BookCateDAOImpl) Class.forName(Configurations.get(SystemConstant.DAOIMPL_BOOKCATEIMPL)).newInstance();
        List<BookCate> list = null;

        list = bookCateDAO.GetAllParents();
        list.forEach(System.out::println);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}