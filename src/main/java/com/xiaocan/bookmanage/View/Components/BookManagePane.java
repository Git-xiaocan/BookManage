package com.xiaocan.bookmanage.View.Components;

import com.xiaocan.bookmanage.dao.BookInfoDAO;
import com.xiaocan.bookmanage.dao.DAOFactory;
import com.xiaocan.bookmanage.dao.impl.BookCateDAOImpl;
import com.xiaocan.bookmanage.dao.impl.BookInfoDAOImpl;
import com.xiaocan.bookmanage.entity.BookInfo;
import com.xiaocan.bookmanage.entity.BookSearchCondition;
import com.xiaocan.bookmanage.service.impl.BookInfoServiceImpl;
import com.xiaocan.bookmanage.util.Configurations;
import com.xiaocan.bookmanage.util.SystemConstant;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;

import javafx.scene.Node;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.util.Callback;

import java.time.LocalDate;
import java.util.List;

public class BookManagePane extends StackPane {
    private TableView tableView = new TableView();
    private ComboBox<String> cmbKind = new ComboBox<>();//分类
    private DatePicker FromDate = new DatePicker(LocalDate.now());
    private DatePicker dateTo = new DatePicker(LocalDate.now());
    private TextField textSearch = new TextField("请输入关键字...");
    private Button btnSearch = new Button("搜索");
    private Button btnClear = new Button("清空");
    private String[] btnTexts = {"新增", "修改", "导出", "删除"};
    private Button[] buttons = new Button[btnTexts.length];
    private Pagination pagination = new Pagination();
    private final int ITEMPERPAGE = 18;//表格每页显示的数量

    /**
     * Creates a StackPane layout with default CENTER alignment.
     */
    public BookManagePane() {


            TabPane tabPane = new TabPane();
            tabPane.getStyleClass().add("tab-pane>*.tab-header-area>*.tab-header-background");
            //设置tabpane不能被关闭
            tabPane.setPadding(new Insets(10));
            tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

            Tab tab_bookManage = new Tab("图书管理", CreateTabBookManage());
            tab_bookManage.getStyleClass().add("tab-Item");
            tabPane.getTabs().add(tab_bookManage);
            getChildren().add(tabPane);

            InitTableViewColumn();
            //初始化搜索框
            initSearchBtn();
            initClearBtn();





    }

    private Node CreateTabBookManage() {
        //作为中间右侧tabPan的内容面板
        BorderPane root = new BorderPane();
        HBox searchPane = new HBox(3);
        /**
         * comboBox的数据来自数据库
         */

        searchPane.getChildren().addAll(new Label("分类:"), cmbKind, new Label("时间:"), FromDate, new Label("-"), dateTo, new Label("关键字:"), textSearch, btnSearch, btnClear);


        //美化控件
        btnSearch.getStyleClass().add("button-All");
        btnClear.getStyleClass().add("button-Special");
        //给标签添加样式
        for (int i = 0; i < searchPane.getChildren().size(); i++) {

            if (searchPane.getChildren().get(i) instanceof Label) {
                searchPane.getChildren().get(i).getStyleClass().add("center-right-content-search-lable");
            }
        }
        //给searchBox设置样式
        searchPane.setPadding(new Insets(10));
        searchPane.setStyle("-fx-background-color: rgb(240,241,216);-fx-border-color: #cccccc;");

        //tableView 面板
        BorderPane tableViewPane = new BorderPane();
        tableViewPane.setTop(createButtonPane());
        BorderPane.setMargin(tableViewPane, new Insets(10));

        tableViewPane.setCenter(createPagination(ITEMPERPAGE, 0));
        //tableViewPane.setBottom(createButtonPane());
        //给搜索面板cmbbox添加内容
        Platform.runLater(() -> {

            CmbBookCateData();
        });
        //将搜索面板添加到跟面板中
        root.setTop(searchPane);
        root.setCenter(tableViewPane);
        return root;
    }
    //与tableview 控件绑定的图书集合

    private ObservableList<FxBook> bookList = FXCollections.observableArrayList();


    //1. 书写图书实体类 ，fxbook实现类
    //2.书写bookDAO以及实现类 -进行查询的测试
    //3.设置tabView 控件的列 以便填充数据

    /**
     * 初始化表格控件的列
     */
    /**
     * private IntegerProperty bookId;
     * private StringProperty ""ISBN"";
     * private StringProperty bookName;
     * private StringProperty inputCode;
     * private StringProperty author;
     * private StringProperty KeyWords;
     * private StringProperty CateCode;
     * private StringProperty Publisher;
     * private StringProperty Summary;
     * private StringProperty ContentInfo;
     * private StringProperty Price;
     * private IntegerProperty StoreCount;
     * private StringProperty RegDate;
     * private StringProperty Memo;
     */
    private void InitTableViewColumn() {
        Platform.runLater(() -> {
            String[] colNames = {"图书编号", "图书名称", "出版号", "拼音输入码", "作者", "关键字", "内容摘要", "单价", "库存", "上架时间"};
            String[] fields = {"bookId", "bookName", "ISBN", "inputCode", "author", "KeyWords", "ContentInfo", "Price", "StoreCount", "RegDate"};
            TableColumn[] columns = new TableColumn[colNames.length];
            int[] colWidths = {80, 120, 80, 120, 100, 120, 200, 40, 40, 80};
            for (int i = 0; i < colNames.length; i++) {
                columns[i] = new TableColumn(colNames[i]);
                //绑定tableview控件和实体类中的属性
                columns[i].setCellValueFactory(new PropertyValueFactory<FxBook, String>(fields[i]));
                columns[i].setPrefWidth(colWidths[i]);
            }
            tableView.getColumns().addAll(columns);


            InitTableViewData();
        });


    }

    private BookInfoServiceImpl bookInfoService = null;

    public void InitTableViewData(List<BookInfo> bookinfoList) {
        Platform.runLater(() -> {
            if (bookinfoList != null) {
                this.bookList.clear();
                bookinfoList.forEach(book -> {
                    this.bookList.add(new FxBook(book));
                });
            }
            RefreshTableView();
        });


    }

    public void InitTableViewData() {

        try {
            bookInfoService = (BookInfoServiceImpl) Class.forName(Configurations.get(SystemConstant.SERVICE_BOOK)).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        InitTableViewData(bookInfoService.searchAll());


    }


    //用于与tableView绑定的内部类
    public class FxBook {

        private IntegerProperty bookId;
        private StringProperty ISBN;
        private StringProperty bookName;
        private StringProperty inputCode;
        private StringProperty author;
        private StringProperty KeyWords;
        private StringProperty CateCode;
        private StringProperty Publisher;
        private StringProperty Summary;
        private StringProperty ContentInfo;
        private StringProperty Price;
        private IntegerProperty StoreCount;
        private StringProperty RegDate;
        private StringProperty Memo;

        public FxBook(BookInfo bookInfo) {
            this.author = new SimpleStringProperty(bookInfo.getAuthor());
            this.bookId = new SimpleIntegerProperty(bookInfo.getBookId());
            this.bookName = new SimpleStringProperty(bookInfo.getBookName());
            this.CateCode = new SimpleStringProperty(bookInfo.getCateCode());
            this.ContentInfo = new SimpleStringProperty(bookInfo.getContentInfo());
            this.inputCode = new SimpleStringProperty(bookInfo.getInputCode());
            this.ISBN = new SimpleStringProperty(bookInfo.getISBN());
            this.KeyWords = new SimpleStringProperty(bookInfo.getKeyWords());
            this.Memo = new SimpleStringProperty(bookInfo.getMemo());
            this.Price = new SimpleStringProperty(bookInfo.getPrice());
            this.Publisher = new SimpleStringProperty(bookInfo.getPublisher());
            this.StoreCount = new SimpleIntegerProperty(bookInfo.getStoreCount());
            this.Summary = new SimpleStringProperty(bookInfo.getSummary());
            this.RegDate = new SimpleStringProperty(bookInfo.getRegDate());

        }

        public int getBookId() {
            return bookId.get();
        }

        public IntegerProperty bookIdProperty() {
            return bookId;
        }

        public void setBookId(int bookId) {
            this.bookId.set(bookId);
        }

        public String getISBN() {
            return ISBN.get();
        }

        public StringProperty ISBNProperty() {
            return ISBN;
        }

        public void setISBN(String ISBN) {
            this.ISBN.set(ISBN);
        }

        public String getBookName() {
            return bookName.get();
        }

        public StringProperty bookNameProperty() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName.set(bookName);
        }

        public String getInputCode() {
            return inputCode.get();
        }

        public StringProperty inputCodeProperty() {
            return inputCode;
        }

        public void setInputCode(String inputCode) {
            this.inputCode.set(inputCode);
        }

        public String getAuthor() {
            return author.get();
        }

        public StringProperty authorProperty() {
            return author;
        }

        public void setAuthor(String author) {
            this.author.set(author);
        }

        public String getKeyWords() {
            return KeyWords.get();
        }

        public StringProperty keyWordsProperty() {
            return KeyWords;
        }

        public void setKeyWords(String keyWords) {
            this.KeyWords.set(keyWords);
        }

        public String getCateCode() {
            return CateCode.get();
        }

        public StringProperty cateCodeProperty() {
            return CateCode;
        }

        public void setCateCode(String cateCode) {
            this.CateCode.set(cateCode);
        }

        public String getPublisher() {
            return Publisher.get();
        }

        public StringProperty publisherProperty() {
            return Publisher;
        }

        public void setPublisher(String publisher) {
            this.Publisher.set(publisher);
        }

        public String getSummary() {
            return Summary.get();
        }

        public StringProperty summaryProperty() {
            return Summary;
        }

        public void setSummary(String summary) {
            this.Summary.set(summary);
        }

        public String getContentInfo() {
            return ContentInfo.get();
        }

        public StringProperty contentInfoProperty() {
            return ContentInfo;
        }

        public void setContentInfo(String contentInfo) {
            this.ContentInfo.set(contentInfo);
        }

        public String getPrice() {
            return Price.get();
        }

        public StringProperty priceProperty() {
            return Price;
        }

        public void setPrice(String price) {
            this.Price.set(price);
        }

        public int getStoreCount() {
            return StoreCount.get();
        }

        public IntegerProperty storeCountProperty() {
            return StoreCount;
        }

        public void setStoreCount(int storeCount) {
            this.StoreCount.set(storeCount);
        }

        public String getRegDate() {
            return RegDate.get();
        }

        public StringProperty regDateProperty() {
            return RegDate;
        }

        public void setRegDate(String regDate) {
            this.RegDate.set(regDate);
        }

        public String getMemo() {
            return Memo.get();
        }

        public StringProperty memoProperty() {
            return Memo;
        }

        public void setMemo(String memo) {
            this.Memo.set(memo);
        }
    }

    /**
     * @param PageCount     //分页的总页面数
     * @param itemPerPage   //每页显示的数据条数
     * @param currPageIndex //当前显示分页的下标
     * @return
     */
    private Node createPagination(int PageCount, int currPageIndex) {
        RefreshTableView();
        return pagination;
    }

    public void RefreshTableView() {
        Platform.runLater(() -> {
            if (bookList != null) {
                int count = bookList.size() / ITEMPERPAGE;
                if (bookList.size() % ITEMPERPAGE != 0) {
                    count++;
                }


                pagination.setPageCount(count == 0 ? 1 : count);
            } else {
                pagination.setPageCount(1);
            }

            pagination.setCurrentPageIndex(0);
            pagination.setPageFactory(new Callback<Integer, Node>() {
                @Override
                public Node call(Integer param) {
                    int pageIndex = param.intValue();//获得当前分页的分页下标
//                bookList.clear();
                    tableView.getItems().clear();  //为了方便显示不同的查询结果
                    //链接数据库  根据分页下标返回队形的集合数据
                    //为了实现分页的效果 所以需要sublist
                    int fromIndex = pageIndex * ITEMPERPAGE;//起始下标
                    int toIndex = (pageIndex + 1) * ITEMPERPAGE;
                    if (toIndex >= bookList.size()) toIndex = bookList.size();
                    List sublist = bookList.subList(fromIndex, toIndex);
                    tableView.setItems(FXCollections.observableArrayList(sublist));
                    return tableView;
                }
            });
        });

    }

    //创建tableview上方的按钮
    private Node createButtonPane() {
        HBox ButtonPane = new HBox(10);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button(btnTexts[i]);
            if (i != 3) {
                buttons[i].getStyleClass().add("button-All");
            } else {
                buttons[i].getStyleClass().add("button-Special");
            }
        }
        ButtonPane.getChildren().addAll(buttons);
        return ButtonPane;
    }

    /**
     * 给搜索框的分类combBox设置分类信息
     */
    BookCateDAOImpl bookCateDAO = null;

    public void CmbBookCateData() {
        try {
            bookCateDAO = (BookCateDAOImpl) Class.forName(Configurations.get(SystemConstant.DAOIMPL_BOOKCATEIMPL)).newInstance();
            bookCateDAO.GetAllParents().forEach(bookcate -> {
                String code = bookcate.getBookCateCode();
                String name = bookcate.getBookCateName();
                if (name.length() > 6) {
                    name = name.substring(0, 6);
                }
                cmbKind.getItems().add(code + "-" + name);


            });

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        cmbKind.setValue("请选择");

    }

    public void initSearchBtn() {
        this.btnSearch.setOnMouseClicked(e -> {
            BookSearchCondition condition = null;
            try {
                condition = (BookSearchCondition) Class.forName(Configurations.get(SystemConstant.ENTITY_BOOK_SEARCH_CONDITION)).newInstance();
                condition.setToDate(dateTo.getValue().toString());
                condition.setFromDate(FromDate.getValue().toString());
                condition.setIsbn(textSearch.getText());
                condition.setKeyWords(textSearch.getText());
                condition.setBookName(textSearch.getText());
                condition.setCateCode(cmbKind.getValue().split("-")[0]);
                System.out.println(cmbKind.getValue());
                BookInfoDAO bookInfoDAO = (BookInfoDAOImpl) DAOFactory.GetDAO(SystemConstant.DAOIMPL_BOOKINFOIMPL);
                // tableView.getItems().clear();


                List<BookInfo> list = bookInfoDAO.search(condition);
                if (list != null) {
                    bookList.clear();
                    list.forEach(book -> {
                        bookList.add(new FxBook(book));
                    });
                }
                RefreshTableView();

            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (InstantiationException ex) {
                ex.printStackTrace();
            }


        });


    }

    public void initClearBtn() {

        btnClear.setOnMouseClicked(e -> {

            cmbKind.setValue("请选择");
            textSearch.setText("请输入关键字...");
            dateTo.setValue(LocalDate.now());
            FromDate.setValue(LocalDate.now());

        });
    }

}
