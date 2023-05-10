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
import model.Players;
import model.Teams;
import model.Team;
import model.Player;
import model.Players;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ViewPlayersController extends Controller<Teams>{
    @FXML private TableView<Player> playersTable;
    @FXML private TableColumn<Player,String> teamNameColumn;
    @FXML private TableColumn<Player, Integer> playerNumberColumn;
    @FXML private TableColumn<Player, String> playerNameColumn;
    @FXML private TableColumn<Player, Double> playerCreditColumn;
    @FXML private TableColumn<Player, Integer> playerAgeColumn;
    @FXML private TableColumn<Player, String> playerLevelColumn;
    @FXML private TextField filterByLevelTextField;
    @FXML private TextField filterByNameTextField;
    @FXML private TextField fromTextField;
    @FXML private TextField toTextField;
    private String currentName;
    private String currentLevel;
    private Integer startingNumber = 0;
    private Integer endingNumber = 0;
    @FXML private void initialize() {
        playersTable.widthProperty().addListener((observable, oldValue, newValue) -> {
            double tableWidth = newValue.doubleValue();
            double columnWidth = tableWidth / 6; // Divide the table width by the number of columns

            teamNameColumn.setPrefWidth(columnWidth);
            playerNumberColumn.setPrefWidth(columnWidth);
            playerNameColumn.setPrefWidth(columnWidth);
            playerCreditColumn.setPrefWidth(columnWidth);
            playerAgeColumn.setPrefWidth(columnWidth);
            playerLevelColumn.setPrefWidth(columnWidth);

        });
        //set value factories for each table column
        teamNameColumn.setCellValueFactory(cellData -> cellData.getValue().getTeam().nameProperty());
        playerNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getPlayerNoProperty().asObject());
        playerNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        playerCreditColumn.setCellValueFactory(cellData -> cellData.getValue().getPlayerCreditProperty().asObject());
        playerAgeColumn.setCellValueFactory(cellData -> cellData.getValue().getPlayerAgeProperty().asObject());
        playerLevelColumn.setCellValueFactory(cellData -> cellData.getValue().levelProperty());

        ObservableList<Player> allPlayers = FXCollections.observableArrayList();

        for (int i = 0; i < getTeams().currentTeams().size(); i++) {
            allPlayers.addAll(getTeams().currentTeams().get(i).getCurrentPlayers());
        }

        playersTable.setItems(allPlayers);
        filterByLevelTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            allPlayers.clear();
            currentLevel = newValue;
            for (int i = 0; i < getTeams().currentTeams().size(); i++) {
                getTeams().currentTeams().get(i).filterList(currentName, currentLevel, startingNumber,endingNumber);
                allPlayers.addAll(getTeams().currentTeams().get(i).getPlayers().getFilteredPlayersList());
            }
            playersTable.setItems(allPlayers);
        });
        filterByNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            allPlayers.clear();
            currentName = newValue;
            for (int i = 0; i < getTeams().currentTeams().size(); i++) {
                getTeams().currentTeams().get(i).filterList(currentName, currentLevel, startingNumber,endingNumber);
                allPlayers.addAll(getTeams().currentTeams().get(i).getPlayers().getFilteredPlayersList());
            }
            playersTable.setItems(allPlayers);
        });
        fromTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            allPlayers.clear();
            if (newValue.isEmpty()){
                startingNumber = 0;
            } else {
                startingNumber = Integer.parseInt(newValue);
            }
            if (endingNumber == null) {
                endingNumber = 0;
            }
            for (int i = 0; i < getTeams().currentTeams().size(); i++) {
                getTeams().currentTeams().get(i).filterList(currentName, currentLevel, startingNumber,endingNumber);
                allPlayers.addAll(getTeams().currentTeams().get(i).getPlayers().getFilteredPlayersList());
            }
            playersTable.setItems(allPlayers);
        });
        toTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            allPlayers.clear();
            if (newValue.isEmpty()){
                endingNumber = 0;
            } else {
                endingNumber = Integer.parseInt(newValue);
            }
            if (startingNumber == null) {
                startingNumber = 0;
            }
            for (int i = 0; i < getTeams().currentTeams().size(); i++) {
                getTeams().currentTeams().get(i).filterList(currentName, currentLevel, startingNumber,endingNumber);
                allPlayers.addAll(getTeams().currentTeams().get(i).getPlayers().getFilteredPlayersList());
            }
            playersTable.setItems(allPlayers);
        });
    }
    @FXML
    public void close(){
        stage.close();
    }
    private Teams getTeams() {
        return model;
    }

}

