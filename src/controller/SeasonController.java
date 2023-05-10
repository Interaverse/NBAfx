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
import model.Season;

public class SeasonController  extends Controller<Season>{
    //add private fields from SeasonView.fxml here named on fx:id
    @FXML
    private Button roundButton;

    @FXML
    private Button currentButton;

    @FXML
    private Button gameButton;

    @FXML
    private Button resultButton;

    @FXML
    private Button closeButton;

    @FXML
    public void addRound() {
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            ViewLoader.showStage(getSeason(), "/view/SeasonRoundView.fxml", "Season Rounds", stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void displayRound() {

    }

    @FXML
    public void playGame() {

    }

    @FXML
    public void displayResult() {

    }

    @FXML
    public void close() {

    }
    private Season getSeason(){
        return this.model;
    }

}

