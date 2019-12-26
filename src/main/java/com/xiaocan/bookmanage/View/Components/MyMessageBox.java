package com.xiaocan.bookmanage.View.Components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MyMessageBox extends Stage {
    private String MessageContent;
    private int Button_Type;
    public static final int BUTTON_TYPR_OK = 0;
    public static final int BUTTON_TYPR_CONFIRM_AND_CANCEL = 1;
    private Button btnCancel = new Button("取消");
    private Button btnConfirm = new Button("确定");
    private Button btnOk = new Button("OK");
    public static final int Type_Cancel = 0;
    public static final int Type_Confirm = 1;
    public static final int Type_Ok = 2;

    public int UserSelect = -1;


    //默认为0K提示框
    public MyMessageBox(String messageContent, int Button_Type) {

        this.MessageContent = messageContent;
        this.Button_Type = Button_Type;
        setTitle("提示信息");
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 200, 200);
        scene.setFill(null);
        root.setStyle("-fx-background-radius: 5px");

        setScene(scene);


        initEvent();
        initComponents();
        show();


    }

    private void initComponents() {
        BorderPane borderPane = (BorderPane) this.getScene().getRoot();

        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);


        Label centerContent = new Label(MessageContent);
        borderPane.setCenter(centerContent);


        if (Button_Type == BUTTON_TYPR_OK) {
            HBox hBox = new HBox();
            btnOk.setStyle("-fx-pref-width: 50px; -fx-pref-height: 30px; -fx-background-color: #363636; -fx-text-fill: white ");
            hBox.getChildren().addAll(btnOk);
            hBox.setAlignment(Pos.CENTER);
            hBox.setPadding(new Insets(10, 10, 18, 10));
            borderPane.setBottom(hBox);

        } else if (Button_Type == BUTTON_TYPR_CONFIRM_AND_CANCEL) {

            HBox hBox = new HBox(50);
            btnConfirm.setStyle("-fx-pref-width: 50px; -fx-pref-height: 30px; -fx-background-color: #363636; -fx-text-fill: white ");

            btnCancel.setStyle("-fx-pref-width: 50px; -fx-pref-height: 30px; -fx-background-color: #363636; -fx-text-fill: white ");
            hBox.getChildren().addAll(btnCancel,btnConfirm);
            hBox.setAlignment(Pos.CENTER_LEFT);
            borderPane.setBottom(hBox);
        }


    }

    public int getSelect() {


        return UserSelect;
    }


    void initEvent() {
        btnOk.setOnMouseClicked(e -> {
            UserSelect = Type_Ok;
            this.close();
        });
        btnCancel.setOnMouseClicked(e -> {
            UserSelect = Type_Cancel;
            this.close();
            ;
        });
        btnConfirm.setOnMouseClicked(e -> {
            UserSelect = Type_Confirm;
            this.close();
        });

    }


}
