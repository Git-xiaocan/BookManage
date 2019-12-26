package com.xiaocan.bookmanage;

import com.xiaocan.bookmanage.View.Components.MyMessageBox;
import com.xiaocan.bookmanage.View.MainStage;
import com.xiaocan.bookmanage.entity.UserInfo;
import javafx.application.Application;
import javafx.stage.Stage;

public class APP extends Application {

    @Override
    public void start(Stage primaryStage) {
        UserInfo userInfo =  new UserInfo(1,"2","测试用户","测试用户","5","6");
        primaryStage = new MainStage(userInfo);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }

}
