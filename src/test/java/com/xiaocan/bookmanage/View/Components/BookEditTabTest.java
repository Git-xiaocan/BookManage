package com.xiaocan.bookmanage.View.Components;

import org.junit.Test;

import static org.junit.Assert.*;

public class BookEditTabTest {

    @Test
    public void isNum() {
        BookEditTab bookEditTab = new BookEditTab();
        bookEditTab.IsNum("ewffsd");
    }
}