package controller;
import au.edu.uts.ap.javafx.ViewLoader;
import au.edu.uts.ap.javafx.Controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import model.Record;
import model.Season;

public class RecordController extends Controller<Season>{
    @FXML
    private TableView<Record> recordTable;
    @FXML
    private TableColumn<Record, Integer> roundColumn;
    @FXML
    private TableColumn<Record, Integer> gameColumn;
    @FXML
    private TableColumn<Record, String> winningTeamColumn;
    @FXML
    private TableColumn<Record, String> losingTeamColumn;
    @FXML public void close() {
        stage.close();
    }
    @FXML public void initialize() {
        recordTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        roundColumn.setCellValueFactory(cellData -> cellData.getValue().roundProperty().asObject());
        gameColumn.setCellValueFactory(cellData -> cellData.getValue().gameNumberProperty().asObject());
        winningTeamColumn.setCellValueFactory(cellData -> cellData.getValue().winTeamProperty());
        losingTeamColumn.setCellValueFactory(cellData -> cellData.getValue().loseTeamProperty());

        recordTable.setItems(getSeason().record());
    }

    private Season getSeason() {
        return this.model;
    }
}







