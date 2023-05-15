package controller;
import au.edu.uts.ap.javafx.ViewLoader;
import au.edu.uts.ap.javafx.Controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Season;
import model.Game;


public class CurrentRoundTeamsController  extends Controller<Season>{
    @FXML private TableView<Game> scheduleTable;
    @FXML private TableColumn<Game,String> teamOneColumn;
    @FXML private TableColumn<Game,String> teamTwoColumn;
    @FXML private TableColumn versusColumn;
    @FXML private VBox mainVBox;
    public Season getSeason() { return this.model; }

    public String getRound() { return "Round: "+ (getSeason().round() + 1); }

    @FXML public void close(){ stage.close(); }

    @FXML public void initialize(){
        scheduleTable.prefWidthProperty().bind(mainVBox.widthProperty().multiply(0.7));

        teamOneColumn.prefWidthProperty().bind(scheduleTable.widthProperty().divide(3));
        versusColumn.prefWidthProperty().bind(scheduleTable.widthProperty().divide(3));
        teamTwoColumn.prefWidthProperty().bind(scheduleTable.widthProperty().divide(3));

        teamOneColumn.setCellValueFactory(cellData -> cellData.getValue().team1());
        teamTwoColumn.setCellValueFactory(cellData -> cellData.getValue().team2());
        versusColumn.setCellValueFactory(cellData -> new SimpleStringProperty("VS"));

        scheduleTable.setItems(getSeason().getCurrentSchedule());
    }

    public double getTableWidth() {
        return mainVBox.getWidth();  // or replace mainVBox with the id of your VBox
    }

}







