package com.xiaocan.bookmanage.View;

import com.xiaocan.bookmanage.View.Components.BookCatePane;
import com.xiaocan.bookmanage.View.Components.BookManagePane;
import javafx.scene.Node;

/**
 *MainStage 工厂模式创建指定的内容面板
 */
public class MainStageFactory {
    private static BookManagePane bookManagePane = null;
    private  static BookCatePane bookCatePane = null;

    /**
     * 根据传入的文本 返回对应的 面板
     * @param text
     * @return
     */
    public static Node CreatePaneByText(String text){

        if(text=="图书管理"){
            if(bookManagePane== null){
                bookManagePane = new BookManagePane();
            }
            return bookManagePane;
        }else if(text=="分类管理"){
            if(bookCatePane == null){
                bookCatePane = new BookCatePane();
            }
            return bookCatePane;
        }


        return null;
    }

}
