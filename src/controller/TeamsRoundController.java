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
import model.Team;


public class TeamsRoundController  extends Controller<Season>{
    @FXML private ListView<String> roundList;
    @FXML private TableView gameTable;
    @FXML private TableColumn gameColumn;
    @FXML private TableColumn firstTeamColumn;
    @FXML private TableColumn secondTeamColumn;
    @FXML private Button arrangeSeasonButton;
    @FXML private Button transferButton;
    private ObservableList<String> items;
    private int count = 0;
    private ArrayList<ObservableList<Team>> teamsInGame = new ArrayList<ObservableList<Team>>();
    @FXML public void transfer(){
        ObservableList<String> selected = roundList.getSelectionModel().getSelectedItems();
        ObservableList<Team> teams = FXCollections.observableArrayList();
        if(selected.size() == 2){
            count++;
            // Assuming that count represents the game number
            Game game = new Game(""+count, selected.get(0), selected.get(1));
            gameTable.getItems().add(game);
            for (String string : selected) {
                for (Team team : getSeason().getCurrentTeams()) {
                    if(team.getName().equals(string)){
                        teams.add(team);
                    }
                }
            }
            teamsInGame.add(teams);
            roundList.getItems().removeAll(selected);
        }
    }
    @FXML public void arrangeSeason(){
        for (ObservableList<Team> observableList : teamsInGame) {
            getSeason().addTeams(observableList);
        }
        stage.close();
    }
    @FXML public void initialize(){
        items = roundList.getItems();

        for (Team team : getSeason().getCurrentTeams()) {
            items.add(team.getName());
        }

        roundList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        gameColumn.setCellValueFactory(new PropertyValueFactory<Game, String>("game"));
        firstTeamColumn.setCellValueFactory(new PropertyValueFactory<Game, String>("firstTeam"));
        secondTeamColumn.setCellValueFactory(new PropertyValueFactory<Game, String>("secondTeam"));

        items.addListener((ListChangeListener<String>) change -> {
            if (items.isEmpty()) {
                roundList.setPlaceholder(new Label("All teams added to round."));
                arrangeSeasonButton.setDisable(false);
                transferButton.setDisable(true);
            } else {

            }
        });
    }
    private Season getSeason(){
        return model;
    }
    public class Game {
        private SimpleStringProperty game;
        private SimpleStringProperty firstTeam;
        private SimpleStringProperty secondTeam;

        public Game(String game, String firstTeam, String secondTeam) {
            this.game = new SimpleStringProperty(game);
            this.firstTeam = new SimpleStringProperty(firstTeam);
            this.secondTeam = new SimpleStringProperty(secondTeam);
        }

        public String getGame() {
            return game.get();
        }

        public String getFirstTeam() {
            return firstTeam.get();
        }

        public String getSecondTeam() {
            return secondTeam.get();
        }
    }
    public String getRound() { return "Round: "+ (getSeason().round() + 1); }

}



