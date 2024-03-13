package org.example.application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.utils.CalculatorUtil;
import org.example.service.RecordService;
import org.example.models.Record;

import java.sql.SQLException;


public class CalculatorApplication extends Application {
    Stage window;

    private Scene calculationScene;
    private TextArea inputTextArea;
    private Button calculateButton;
    private Button historyButton;

    private Scene historyScene;
    private ObservableList<Record> historyList;
    private ListView<Record> historyListView;
    private Button returnButton;
    private Button deleteButton;

    private RecordService recordService;

    @Override
    public void start(Stage primaryStage) throws Exception {
        initApp(primaryStage);
        primaryStage.setScene(calculationScene);
        primaryStage.show();
    }

    private void initApp(Stage primaryStage) {
        initializeDatabaseConnection();
        window = primaryStage;
        initCalculationScene();
        initHistoryScene();
    }

    private void initCalculationScene() {
        VBox root = new VBox();
        HBox buttonBox = new HBox();

        inputTextArea = new TextArea();
        calculateButton = new Button("Calculate");
        historyButton = new Button("Calculating History");

        inputTextArea.setPromptText("Enter your mathematical expression here");
        inputTextArea.setPrefHeight(100);
        calculateButton.setOnAction(e -> handleCalculate());
        historyButton.setOnAction(e -> handleHistory());

        buttonBox.getChildren().addAll(calculateButton, historyButton);
        root.getChildren().addAll(inputTextArea, buttonBox);

        calculationScene = new Scene(root, 400, 200);
    }

    private void initHistoryScene() {
        VBox root = new VBox();
        HBox buttonBox = new HBox();

        historyListView = new ListView<Record>();
        returnButton = new Button("Back");
        deleteButton = new Button("Delete");

        historyListView.setItems(historyList);
        historyListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Record item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getExpression() + " = " + item.getResult());
                }
            }
        });
        returnButton.setOnAction(e -> handleBackToCalculationScene());
        deleteButton.setOnAction(e -> handleDeleteRecord());
        deleteButton.setDisable(true);
        deleteButton.disableProperty().bind(historyListView.getSelectionModel().selectedItemProperty().isNull());

        buttonBox.getChildren().addAll(returnButton, deleteButton);
        root.getChildren().addAll(historyListView, buttonBox);

        historyScene = new Scene(root, 400, 300);
    }

    private void initializeDatabaseConnection() {
        recordService = new RecordService();
        try {
            historyList = FXCollections.observableList(recordService.getAllRecords());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleCalculate() {
        String expression = inputTextArea.getText();
        double result = CalculatorUtil.calculateExpression(expression);
        inputTextArea.setText(expression + " = " + result);
        Record record = new Record(expression, result);
        try {
            recordService.addRecord(record);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleHistory() {
        try {
            updateHistoryListView();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        window.setScene(historyScene);
    }

    private void handleBackToCalculationScene() {
        window.setScene(calculationScene);
    }

    private void handleDeleteRecord() {
        try {
            ObservableList<Record> selectedItems = historyListView.getSelectionModel().getSelectedItems();
            for (Record item : selectedItems) {
                recordService.deleteRecord(item);
            }
            updateHistoryListView();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void updateHistoryListView() throws SQLException{
        historyList.setAll(recordService.getAllRecords());
    }
}
