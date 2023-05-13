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
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Season;
import model.Game;


public class CurrentRoundTeamsController  extends Controller<Season>{
    @FXML private TableView<Game> scheduleTable;
    @FXML private TableColumn teamOneColumn;
    @FXML private TableColumn teamTwoColumn;
    @FXML private TableColumn versusColumn;
    public Season getSeason() { return this.model; }

    public String getRound() { return "Round: "+ (getSeason().round() + 1); }

    @FXML public void close(){ stage.close(); }

    @FXML public void initialize(){
        scheduleTable.prefWidthProperty().bind(stage.widthProperty().divide(2));
    }
  
}







