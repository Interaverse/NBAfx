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
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Player;
import model.Players;
import model.Team;


public class ManageTeamController extends Controller<Team> {
    @FXML
    private TextField teamNameTextField;
    @FXML
    private Button addPlayerButton;
    @FXML
    private Button deletePlayerButton;
    @FXML
    private Button updatePlayerButton;
    @FXML
    private Button saveAndCloseButton;
    @FXML
    private TableView<Player> playerTable;
    @FXML
    private TableColumn<Player, String> playerNameColumn;
    @FXML
    private TableColumn<Player, Double> playerCreditColumn;
    @FXML
    private TableColumn<Player, Integer> playerAgeColumn;
    @FXML
    private TableColumn<Player, Integer> playerNumberColumn;
    @FXML
    private void initialize() {
        teamNameTextField.setText(getTeam().getName());
        // Set cell value factories for each TableColumn
        playerNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        playerCreditColumn.setCellValueFactory(cellData -> cellData.getValue().getPlayerCreditProperty().asObject());
        playerAgeColumn.setCellValueFactory(cellData -> cellData.getValue().getPlayerAgeProperty().asObject());
        playerNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getPlayerNoProperty().asObject());

        // Bind TableView's items property to the ObservableList of Teams
        playerTable.setItems(getTeam().getPlayers().getPlayersList());

        playerTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    addPlayerButton.setDisable(true);
                    updatePlayerButton.setDisable(false);
                    deletePlayerButton.setDisable(false);
                }
        );
        playerTable.widthProperty().addListener((observable, oldValue, newValue) -> {
            double tableWidth = newValue.doubleValue();
            double columnWidth = tableWidth / 4; // Divide the table width by the number of columns

            playerNameColumn.setPrefWidth(columnWidth);
            playerCreditColumn.setPrefWidth(columnWidth);
            playerAgeColumn.setPrefWidth(columnWidth);
            playerNumberColumn.setPrefWidth(columnWidth);
        });
    }
    private Team getTeam(){
        return this.model;
    }
    private Players getPlayers(){
        return getTeam().getPlayers();
    }
    private Player getSelectedPlayer(){
        return playerTable.getSelectionModel().getSelectedItem();
    }
    @FXML
    public void addPlayer() {
        try {
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/view/edit.png"));
            PlayerUpdateController controller = ViewLoader.showStageWithController(getPlayers(), "/view/PlayerUpdateView.fxml", "Adding New Player", stage, () -> {});
            controller.setupAddPlayer();
        } catch (IOException ex) {
            Logger.getLogger(ManageTeamController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void deletePlayer() {
        getPlayers().removePlayer(getSelectedPlayer());
        addPlayerButton.setDisable(false);
        updatePlayerButton.setDisable(true);
        deletePlayerButton.setDisable(true);
    }
    @FXML
    public void saveAndClose(){
        getTeam().setName(teamNameTextField.getText());
        stage.close();
    }
    @FXML
    public void updatePlayer() {
        try {
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/view/edit.png"));
            PlayerUpdateController controller = ViewLoader.showStageWithController(getPlayers(), "/view/PlayerUpdateView.fxml", "Updating Player: " + getSelectedPlayer().getName(), stage, () -> {});
            controller.setupUpdatePlayer(getSelectedPlayer().getName());
        } catch (IOException ex) {
            Logger.getLogger(ManageTeamController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
