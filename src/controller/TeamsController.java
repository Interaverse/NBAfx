package controller;
import au.edu.uts.ap.javafx.ViewLoader;
import au.edu.uts.ap.javafx.Controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.fxml.FXML;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Teams;
import model.Team;
public class TeamsController extends Controller<Teams>{
    @FXML
    private TableView<Team> teamsTable;

    @FXML
    private TableColumn<Team,String> teamNameColumn;

    @FXML
    private TableColumn<Team,Integer> numOfPlayersColumn;

    @FXML
    private TableColumn<Team,Double> avgPlayerCreditColumn;

    @FXML
    private TableColumn<Team,Double> avgPlayerAgeColumn;

    @FXML
    private Button addTeamButton;

    @FXML
    private Button manageTeamButton;

    @FXML
    private Button deleteTeamButton;

    @FXML
    private Button closeButton;
    @FXML
    private void initialize() {
        // Set cell value factories for each TableColumn
        teamNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        numOfPlayersColumn.setCellValueFactory(cellData -> cellData.getValue().countPlayerProperty().asObject());
        avgPlayerCreditColumn.setCellValueFactory(cellData -> cellData.getValue().countAvgCreditProperty().asObject());
        avgPlayerAgeColumn.setCellValueFactory(cellData -> cellData.getValue().countAvgAgeProperty().asObject());

        // Set custom cell factories for avgPlayerCreditColumn and avgPlayerAgeColumn
        avgPlayerCreditColumn.setCellFactory(column -> new TableCell<Team, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.format("%.2f", item));
                }
            }
        });

        avgPlayerAgeColumn.setCellFactory(column -> new TableCell<Team, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.format("%.2f", item));
                }
            }
        });

        // Bind TableView's items property to the ObservableList of Teams
        teamsTable.setItems(getTeams().currentTeams());

        teamsTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    addTeamButton.setDisable(true);
                    manageTeamButton.setDisable(false);
                    deleteTeamButton.setDisable(false);
                }
        );
        teamsTable.widthProperty().addListener((observable, oldValue, newValue) -> {
            double tableWidth = newValue.doubleValue();
            double columnWidth = tableWidth / 4; // Divide the table width by the number of columns

            teamNameColumn.setPrefWidth(columnWidth);
            numOfPlayersColumn.setPrefWidth(columnWidth);
            avgPlayerCreditColumn.setPrefWidth(columnWidth);
            avgPlayerAgeColumn.setPrefWidth(columnWidth);
        });
    }

    public Teams getTeams(){
        return this.model;
    }
    @FXML
    public void addTeam(){
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/edit.png"));
            ViewLoader.showStage(getTeams(), "/view/AddTeam.fxml", "Adding New Team", stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void manageTeam(){
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/edit.png"));
            ViewLoader.showStage(getTeams().getTeam(getSelectedTeam().getName()), "/view/ManageTeamView.fxml", "Managing Team: " + getSelectedTeam().getName(), stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void deleteTeam(){
        getTeams().remove(getSelectedTeam());
        addTeamButton.setDisable(false);
        manageTeamButton.setDisable(true);
        deleteTeamButton.setDisable(true);
    }
    private Team getSelectedTeam() {
        return teamsTable.getSelectionModel().getSelectedItem();
    }
    @FXML
    public void close() {
        //close the window
        stage.close();
    }
}

