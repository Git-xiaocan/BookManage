package com.xiaocan.bookmanage.View;


import com.xiaocan.bookmanage.entity.UserInfo;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Iterator;
import java.util.ListIterator;


/**
 * 系统主窗口
 */
public class MainStage extends Stage {
    /**
     * 主窗体左侧导航面板的宽度设置为固定值
     */
    private static final int NAV_WIDTH = 240;
    /**
     * 当前登录的用户 对象 需要从等路窗口传递过来
     */
    private UserInfo LoginUser = null;

    private Scene MainScene = null;
    /**
     * top-center 部分的导航按钮
     */
    private HBox navButtonPane = new HBox();
    /**
     * 中间部分 左侧 图片标签
     */
   private  ImageView[] leftImg = new ImageView[5];
    /**
     * 中部右边内容面板
     */
    private StackPane ContentPane = new StackPane();

    public MainStage(UserInfo loginUser) {
        LoginUser = loginUser;
        BorderPane root = new BorderPane();
        root.getStyleClass().add("root");
        setTitle("狗娃图书管理系统");
        MainScene = new Scene(root, 1280, 800);

        setScene(MainScene);
        //加载样式文件
        MainScene.getStylesheets().addAll(
                getClass().getClassLoader().getResource("css//style.css").toExternalForm()
                , getClass().getClassLoader().getResource("css//MainStage.css").toExternalForm()
        );
        initComponents();
        initEvent();
    }

    private void initEvent() {

    }

    private Node createBody() {
        BorderPane borderPane = new BorderPane();

        //左边内容放在一个Vbox上
        VBox vBox = new VBox();
        vBox.getStyleClass().add("Body-left-Pane");
        FlowPane leftPane = new FlowPane();
        leftPane.setHgap(5);
        leftPane.setAlignment(Pos.CENTER);
        leftPane.getStyleClass().add("center-left-top-img");
        for (int i = 0; i < leftImg.length; i++) {
            leftImg[i] = new ImageView("images//nav-" + (i + 1) + ".png");
        }
        leftPane.getChildren().addAll(leftImg);


        vBox.getChildren().add(leftPane);

        //titledPane 需要添加在Accordion上显示
        Accordion accordion = new Accordion();
        accordion.getPanes().addAll(
                createTitledPane("图书管理", "images//图书管理.png"),
                createTitledPane("用户管理", "images//用户管理.png"),
                createTitledPane("系统管理", "images//系统管理.png")
        );
        createManageContent(accordion.getPanes().get(0), "图书管理", "分类管理");
        createManageContent(accordion.getPanes().get(1), "读者管理", "角色管理");
        createManageContent(accordion.getPanes().get(2), "更改密码", "日志管理");


        vBox.getChildren().add(accordion);
        vBox.setPrefWidth(NAV_WIDTH);//240


        borderPane.setLeft(vBox);
        //中间内容面板
         borderPane .setCenter(ContentPane);

        return borderPane;
    }


    /**
     * 创建titledPane下 用户管理的内容
     *
     * @param titledPane
     */
    private void createManageContent(TitledPane titledPane, String... BtnTit) {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(0));

        Button[] BtnArray = new Button[BtnTit.length];

        for (int i = 0; i < BtnTit.length; i++) {
            BtnArray[i] = new Button(BtnTit[i]);
            BtnArray[i].getStyleClass().add("TitledPane-Btn");
        }


        //为按钮添加事件
        initBtnEvent(BtnArray);
        vBox.getChildren().addAll(BtnArray);
        titledPane.setContent(vBox);

    }

    /**
     * 为左侧titledPane里面的按钮添加事件
     */
    private void initBtnEvent(Button... btn) {
        for (int i = 0; i < btn.length; i++) {
            btn[i].setOnMouseClicked(e -> {
                //顶部是否存在当前点击的按钮
                boolean isExist = false;
                ObservableList<Node> observableList = this.navButtonPane.getChildren();

                //被点击的按钮
                Button b = (Button) e.getSource();


                //遍历顶部的节点
                for (int j = 0; j < observableList.size(); j++) {
                    Node node = observableList.get(j);

                    if (node instanceof Button) {
                        node.getStyleClass().remove("header-nav-button-Selected");
                        //顶部的按钮
                        Button tbtn = (Button) node;
                        if (b.getText().equals(tbtn.getText())) {
                            //如果存在对应的按钮
                            isExist = true;
                            //将按钮样式设置为已选中状态
                            tbtn.getStyleClass().add("header-nav-button-Selected");
                        }

                    }

                }
                //如果按钮不存在  向顶部添加按钮
                if (isExist == false) {
                    Button btnNav = new Button(b.getText());
                    btnNav.getStyleClass().add("header-nav-button");

                    //为头部的按钮添加事件
                    AddEvent(btnNav,observableList);
                    navButtonPane.getChildren().add(btnNav);
                }

                //点击按钮后创建右侧内容面板
                //1.先清空右侧面板
                ContentPane.getChildren().clear();
               ContentPane.getChildren().add(MainStageFactory.CreatePaneByText(b.getText())) ;

            });
        }






    }

    /**
     * 给头部按钮添加事件
     */
    private void AddEvent(Button btn,ObservableList<Node> oblist) {
        for(int i = 0 ;i < oblist.size() ;i ++){
            oblist.get(i).getStyleClass().remove("header-nav-button-Selected");

        }
        btn.getStyleClass().add("header-nav-button-Selected");
        btn.setOnMouseClicked(e->{
            for(int i = 0 ;i < oblist.size() ;i ++){
                oblist.get(i).getStyleClass().remove("header-nav-button-Selected");

            }
            ((Button)e.getSource()).getStyleClass().add("header-nav-button-Selected");


        });



    }

    /**
     * 创建头部面板
     *
     * @return
     */
    private Node createHeader() {
        BorderPane header = new BorderPane();
        header.setPrefHeight(55);
        Label logo = new Label("狗娃图书管理系统");
        logo.getStyleClass().add("logo");
        logo.setPrefWidth(240);
        logo.setPrefHeight(55);
        header.setLeft(logo);
        header.getStyleClass().add("headPane-BackGround");
        //设置中间部分 - 导航按钮面板
        //默认放置一个首页按钮
        header.setCenter(navButtonPane);
        Button BtnHome = new Button("首页");
        InitBtnHoneEvent(BtnHome,navButtonPane.getChildren());
        BtnHome.getStyleClass().addAll("header-nav-button", "header-nav-button-Selected");
        navButtonPane.getChildren().add(BtnHome);


        //右边图标和欢迎界面
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(vBox);
        //两个盒子实现居中效果
        ImageView userImg_r = new ImageView("images//admin.png");
        userImg_r.setFitWidth(28);
        userImg_r.setFitHeight(28);
        vBox.getChildren().add(userImg_r);

        Label lblUserName = new Label("欢迎您，" + LoginUser.getUserName());
        lblUserName.getStyleClass().add("header-nav-Right-userlbl");
        hbox.getChildren().add(lblUserName);
        header.setRight(hbox);
        return header;
    }

    private void InitBtnHoneEvent(Button btnHome, ObservableList<Node> children) {
        btnHome.setOnMouseClicked(e->{
            for(int i =0 ;i <children.size() ;i ++){
                //删除 被选中的样式
                children.get(i).getStyleClass().remove("header-nav-button-Selected");
            }

            //其他事件



            btnHome.getStyleClass().add("header-nav-button-Selected");
        });
    }

    /**
     * 创建底部面板
     *
     * @return
     */
    private Node createFooter() {
        BorderPane borderPane = new BorderPane();
        borderPane.getStyleClass().add("headPane-BackGround");
        borderPane.setPrefHeight(40);
        return borderPane;

    }

    /**
     * 初始化组件
     */
    private void initComponents() {

        BorderPane root = (BorderPane) MainScene.getRoot();
        root.setTop(createHeader());
        root.setCenter(createBody());
        root.setBottom(createFooter());

    }

    /**
     * 给titledPane设置图标
     *
     * @param tit
     * @param ImgPath
     * @return
     */
    private TitledPane createTitledPane(String tit, String ImgPath) {
        TitledPane titledPane = new TitledPane(tit, null);
        ImageView img = new ImageView(ImgPath);

        //设置图标高度宽度
        img.setFitHeight(18);
        img.setFitWidth(18);

        //为titledPane 设置图标
        titledPane.setGraphic(img);
        //设置图标向左对齐
        titledPane.setContentDisplay(ContentDisplay.LEFT);

        return titledPane;

    }

}
