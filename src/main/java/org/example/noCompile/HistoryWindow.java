package org.example.noCompile;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.models.Record;

import java.util.List;

public class HistoryWindow {

    ListView<Record> historyListView;
    Button deleteButton;

    public static void displayHistory(List<Record> historyList) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Calculation History");

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> );

        ListView<String> historyListView = new ListView<>();

        for (Record record : historyList) {
            historyListView.getItems().add(record.getExpression() + " = " + record.getResult());
        }

        VBox layout = new VBox(10);
        layout.getChildren().addAll(historyListView);

        Scene scene = new Scene(layout, 400, 300);
        window.setScene(scene);
        window.showAndWait();
    }
}
