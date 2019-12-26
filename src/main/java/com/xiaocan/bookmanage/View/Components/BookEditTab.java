package com.xiaocan.bookmanage.View.Components;

import com.mysql.cj.util.StringUtils;
import com.xiaocan.bookmanage.entity.BookInfo;
import com.xiaocan.bookmanage.entity.BookSearchCondition;
import com.xiaocan.bookmanage.service.impl.BookInfoServiceImpl;
import com.xiaocan.bookmanage.util.PinYinUtil;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.web.HTMLEditor;

import java.sql.SQLException;
import java.time.LocalDate;

public class BookEditTab extends BorderPane {
    private Label lblIsbn = new Label("ISBN:");
    private TextField txtISBN = new TextField();
    private Label LblExist = new Label("ISBN已存在");
    private Label lblBookName = new Label("书名:");
    private TextField txtBookName = new TextField();
    private Label lblInputCode = new Label("输入码:");
    private TextField txtInputCode = new TextField();
    private Label lblAuthor = new Label("作者:");
    private TextField txtAuthor = new TextField();
    private Label lblPrice = new Label("定价:");
    private TextField txtPrice = new TextField();
    private Label lblCount = new Label("库存:");
    private TextField txtCount = new TextField();
    private Label lblCateNum = new Label("分类号:");
    private TextField txtCateNum = new TextField();
    private Label lblPublish = new Label("出版社:");
    private TextField txtPublish = new TextField();
    private Label lblKeyWords = new Label("关键字:");
    private TextField txtKeyWords = new TextField();
    private Label lblSummary = new Label("摘要:");
    private TextField txtSummary = new TextField();
    private Label lblContentInfo = new Label("内容简介:");
    private HTMLEditor htmlContentInfo = new HTMLEditor();
    private ImageView imgCover = new ImageView(); //封面图片
    private DatePicker dpPublish = new DatePicker(LocalDate.now());
    private Label lblimg = new Label("封面图片");
    private Button btnSubmit = new Button("添加");
    private Button btnReset = new Button("重置");
    private ChangeListener txtIsbnListener = null;
    private EventHandler<MouseEvent> BtnSubmitEven = null;


    public BookEditTab() {
        initComponents();
    }

    private void initComponents() {
        setCenter(createCenterEditPane());
        setBottom(CreateBottomBtnPane());
        setRight(createRightImgPane());
        initEvent();
        LblExist.setVisible(false);
        btnSubmit.setText("提交");
        btnSubmit.setDisable(true);


    }

    public BookEditTab(BookManagePane.FxBook book) {
        initComponents();
        initEvent();
        btnSubmit.setText("修改");
    }


    private void initEvent() {

        btnReset.setOnMouseClicked(e -> {
            ClearTxt();
        });
        txtBookName.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue == false && !txtBookName.getText().equals("")) {
                    txtInputCode.setText(PinYinUtil.getPingYin(txtBookName.getText()));

                }
            }
        });
       txtIsbnListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                //如果isbn失去焦点
                if (newValue == false && !txtISBN.getText().equals("")) {

                    BookSearchCondition con = new BookSearchCondition();
                    System.out.println(txtISBN.getText());
                    con.setIsbn(txtISBN.getText());
                    BookInfoServiceImpl bookInfoService = new BookInfoServiceImpl();

                    if (bookInfoService.FindByCondition(con) != null && bookInfoService.FindByCondition(con).size() != 0) {
                        LblExist.setText("ISBN已存在,请重新输入");
                        LblExist.setFont(new Font("微软雅黑", 14));
                        LblExist.setStyle("-fx-text-fill: red");
                        LblExist.setVisible(true);
                        btnSubmit.setDisable(true);

                    } else if (txtISBN.getText().length() > 20) {
                        LblExist.setText("ISBN过长，请重新输入");
                        LblExist.setFont(new Font("微软雅黑", 14));
                        LblExist.setStyle("-fx-text-fill: red;");
                        LblExist.setVisible(true);
                        btnSubmit.setDisable(true);

                    } else {
                        LblExist.setText("ISBN可用");
                        LblExist.setFont(new Font("微软雅黑", 14));
                        LblExist.setStyle("-fx-text-fill: green");
                        LblExist.setVisible(true);
                        btnSubmit.setDisable(false);

                    }


                }

            }
        };

        txtISBN.focusedProperty().addListener(txtIsbnListener);
         BtnSubmitEven = e -> {
            if (
                    txtBookName.getText().equals("") |
                            txtAuthor.getText().equals("") |
                            txtPrice.getText().equals("") |
                            txtCount.getText().equals("") |
                            txtCateNum.getText().equals("") |
                            txtKeyWords.getText().equals("")
            ) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "信息不完整", ButtonType.OK);
                alert.show();
            } else if (!IsNum(txtPrice.getText())) {
                new MyMessageBox("提示信息", MyMessageBox.BUTTON_TYPR_OK);

                System.out.println("11111");

            } else {

                BookInfoServiceImpl bookInfoService = new BookInfoServiceImpl();
                /**
                 * htmlContentInfo.getHtmlText() 里面的数据需要处理 一下 将一个' 替换为两个''
                 */
                String concent = htmlContentInfo.getHtmlText().replace("'", "''");

                BookInfo book = new BookInfo(1, txtISBN.getText(), txtBookName.getText(), txtInputCode.getText(), txtAuthor.getText(), txtKeyWords.getText(), txtCateNum.getText(), txtPublish.getText(), txtSummary.getText(), concent, txtPrice.getText(), Integer.parseInt(txtCount.getText()), "", dpPublish.getValue().toString());
                int count = bookInfoService.upData(book);
                if (count != -1) {
                   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                   alert.contentTextProperty().setValue("修改成功");
                    ClearTxt();

                }

            }

        };

        btnSubmit.setOnMouseClicked(BtnSubmitEven);
    }

    //判断是不是数字
    public boolean IsNum(String txt) {
        if (txt.equals("") || txt == null) return false;
        txt = txt.replace(".","");
        char[] arr = txt.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 57 || arr[i] < 48) {
                return false;
            }
        }
        return true;
    }


    public   void fillData(BookManagePane.FxBook book){
        txtISBN.setText(book.getISBN());
        txtPrice.setText(book.getPrice());
        txtSummary.setText(book.getSummary());
        txtInputCode.setText(book.getInputCode());
        txtPublish.setText(book.getPublisher());
        txtAuthor.setText(book.getAuthor());
        txtCount.setText(book.getStoreCount()+"");
        txtCateNum.setText(book.getCateCode());
        txtKeyWords.setText(book.getKeyWords());
        txtBookName.setText(book.getBookName());
        btnSubmit.setDisable(false);
        txtISBN.setEditable(false);
        txtISBN.focusedProperty().removeListener(txtIsbnListener);





    }
    void btnUpdataEven(){
        BtnSubmitEven = e->{

        };
    }

    /**
     * 清空输入框的内容
     */
    void ClearTxt() {
        txtBookName.setText("");
        txtKeyWords.setText("");
        txtCateNum.setText("");
        txtCount.setText("");
        txtPrice.setText("");
        txtAuthor.setText("");
        txtISBN.setText("");
        txtPublish.setText("");
        txtInputCode.setText("");
        txtSummary.setText("");
        htmlContentInfo.setHtmlText("");
        btnSubmit.setDisable(true);
        LblExist.setVisible(false);


    }

    /**
     * 创建右边的封面图片面板
     *
     * @return
     */
    private Node createRightImgPane() {
        VBox vBox = new VBox();
        vBox.getChildren().addAll(lblimg, imgCover, dpPublish);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(10, 30, 10, 10));
        return vBox;
    }

    /**
     * 创建底部提交 和重置按钮面板
     *
     * @return
     */
    private Node CreateBottomBtnPane() {
        HBox hBox = new HBox(130);
        btnReset.getStyleClass().add("button-All");
        btnSubmit.getStyleClass().add("button-All");

        hBox.getChildren().addAll(btnSubmit, btnReset);
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }

    private Node createCenterEditPane() {
        GridPane EditPane = new GridPane();
        EditPane.setPadding(new Insets(10, 10, 10, 80));
        EditPane.setVgap(10);
        EditPane.setHgap(10);
        EditPane.addRow(0, lblIsbn, txtISBN, LblExist);
        EditPane.addRow(1, lblBookName, txtBookName, lblInputCode, txtInputCode);
        EditPane.addRow(2, lblAuthor, txtAuthor, lblPrice, txtPrice, lblCount, txtCount);
        EditPane.addRow(3, lblCateNum, txtCateNum, lblPublish, txtPublish);
        EditPane.addRow(4, lblKeyWords, txtKeyWords);
        EditPane.addRow(5, lblSummary, txtSummary);
        EditPane.addRow(6, lblContentInfo, htmlContentInfo);


        GridPane.setColumnSpan(LblExist, 4);
        GridPane.setColumnSpan(txtInputCode, 3);
        GridPane.setColumnSpan(txtPublish, 3);
        GridPane.setColumnSpan(txtKeyWords, 5);
        GridPane.setColumnSpan(txtSummary, 5);
        GridPane.setColumnSpan(htmlContentInfo, 5);
        return EditPane;
    }

}
