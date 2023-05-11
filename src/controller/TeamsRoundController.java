package controller;
import au.edu.uts.ap.javafx.ViewLoader;
import au.edu.uts.ap.javafx.Controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Season;
import model.Team;


public class TeamsRoundController  extends Controller<Season>{
    @FXML private ListView<String> roundList;
    @FXML public void transfer(){
        ObservableList<String> selected = roundList.getSelectionModel().getSelectedItems();
        if(selected.size() == 2){
            String team1 = selected.get(0);
            String team2 = selected.get(1);
            
        }
    }
    @FXML public void arrangeSeason(){

    }
    @FXML public void initialize(){
        ObservableList<String> items = roundList.getItems();

        for (Team team : getSeason().getCurrentTeams()) {
            items.add(team.getName());
        }

        roundList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    private Season getSeason(){
        return model;
    }
}



