package com.xiaocan.bookmanage.View;

import com.xiaocan.bookmanage.dao.impl.UserDAOImpl;
import com.xiaocan.bookmanage.entity.UserInfo;
import com.xiaocan.bookmanage.service.LoginService;
import com.xiaocan.bookmanage.service.impl.LoginServiceImpl;
import com.xiaocan.bookmanage.util.CaptchaUtil;
import com.xiaocan.bookmanage.util.Configurations;
import com.xiaocan.bookmanage.util.SystemConstant;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.util.Map;


/**
 * 登录界面
 */
public class LoginStage extends Stage {
    private LoginService loginService = null;
    private Scene scene = null;
    private TextField textAccount = new TextField();
    private PasswordField txtPassWord = new PasswordField();
    private TextField textVCode = new TextField();
    private ImageView imgVCode = new ImageView("images/code.png");
    private CheckBox chkRemember = new CheckBox("记住密码");
    private Hyperlink txtForget = new Hyperlink("忘记密码");
    private Button btnLogin = new Button("登录");
    private String captch = null;//验证码

    public LoginStage() {
        setTitle("后台登录界面");
        scene = new Scene(new StackPane(), 1280, 800);

        //加载css样式文件
        scene.getStylesheets().add(getClass().getClassLoader().getResource("css/LoginStage.css").toExternalForm());

        setScene(scene);
        loginService = new LoginServiceImpl();
        initComponents();//封装初始化组件
        initEvent();//封装事件代码

    }

    private void initEvent() {
        //给登录按钮添加方法
        btnLogin.setOnAction(e -> {

            String LoginCode = textAccount.getText();
            String LoginPwd = txtPassWord.getText();
            UserInfo user = loginService.login(LoginCode, LoginPwd);


            /**
             * 先验证账户和密码是存在并正确
             */
            if (user != null) {
                System.out.println("验证码为：" + this.captch);
                if (this.captch.equalsIgnoreCase(textVCode.getText())) {
                    //登录成功
                    System.out.println("登录成功");
                    LoginStage.this.close();
                    new MainStage(user).show();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "验证码错误", ButtonType.OK);
                    alert.show();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "用户名或密码错误", ButtonType.OK);
                alert.show();
            }
            //只要按钮被点击 就会刷新验证码
            UpdataCaptcha();

        });


        //添加验证码事件
        imgVCode.setOnMouseClicked(e -> {
            UpdataCaptcha();

        });

    }


    private void initComponents() {
        StackPane root = (StackPane) scene.getRoot();
        //设置root背景图片
        root.getStyleClass().add("root-BackGround1");
        //添加中间登录面板
        VBox CenterLoginPane = new VBox();

        //设置中间面板
        setCenterLoginPane(CenterLoginPane);
        /*利用一个Hbox和一个vBox实现居中*/
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(hbox);
        hbox.getChildren().add(CenterLoginPane);
        root.getChildren().add(vBox);


    }

    //封装中间登录面板上的登录控件
    private void setCenterLoginPane(VBox centerLoginPane) {
        centerLoginPane.setAlignment(Pos.CENTER);
        centerLoginPane.setPrefSize(632, 544);
        //设置内边距
        centerLoginPane.setPadding(new Insets(30, 140, 30, 140));
        //设置垂直间距
        centerLoginPane.setSpacing(15);
        centerLoginPane.getStyleClass().add("CenterLoginBg");
        Label textFirstTitle = new Label("系统管理员登录");
        textFirstTitle.getStyleClass().add("textFirstTitle");
        Label textSecondTitle = new Label("图书管理系统后台");
        textSecondTitle.getStyleClass().add("SecondTitle");
        VBox.setMargin(textSecondTitle, new Insets(-20, 0, 0, 0));

        //设置验证码小面板
        BorderPane Codepane = new BorderPane();
        Codepane.setLeft(textVCode);
        Codepane.setRight(imgVCode);
        imgVCode.getStyleClass().add("hand");
        //更新验证码
        UpdataCaptcha();


        //设置记住密码 忘记密码
        BorderPane rfpane = new BorderPane();
        rfpane.setLeft(this.chkRemember);
        rfpane.setRight(this.txtForget);
        //登录按钮
        btnLogin.setPrefSize(355, 35);
        btnLogin.getStyleClass().addAll("btnLoginBg", "hand");


        this.textAccount.setPrefHeight(30);
        this.txtPassWord.setPrefHeight(30);
        this.textVCode.setPrefHeight(30);
        imgVCode.setFitHeight(30);
        this.textAccount.setPromptText("登录账户");
        this.txtPassWord.setPromptText("登录密码");
        this.textVCode.setPromptText("验证码");


        centerLoginPane.getChildren().addAll(textFirstTitle, textSecondTitle, this.textAccount, this.txtPassWord, Codepane, rfpane, btnLogin);

    }

    //更新验证码
    void UpdataCaptcha() {
        Map<String, Object> CaptchMap = CaptchaUtil.generateCaptcha(120, 35);

        this.captch = CaptchMap.get((CaptchaUtil.CAPTCHA)).toString();
        imgVCode.setImage((Image) CaptchMap.get(CaptchaUtil.IMAGE));
    }
}
