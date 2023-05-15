package controller;
import au.edu.uts.ap.javafx.ViewLoader;
import au.edu.uts.ap.javafx.Controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import model.Players;
import model.Player;
import controller.Validator;


public class PlayerUpdateController extends Controller<Players> {
    @FXML
    private TextField playerNameTextField;
    @FXML
    private TextField playerAgeTextField;
    @FXML
    private TextField playerCreditTextField;
    @FXML
    private TextField playerNumberTextField;
    @FXML
    private Button updatePlayerButton;
    @FXML
    private Button addPlayerButton;
    @FXML
    private Button closeButton;

    private String playerName;

    @FXML
    private void initialize() {

    }
    @FXML public void addPlayer() throws Exception{
        Validator validator = new Validator();
        try {
            validator.generateErrors(playerNameTextField.getText(),playerCreditTextField.getText(), playerAgeTextField.getText(),  playerNumberTextField.getText());
            if (getPlayers().hasPlayer(playerNameTextField.getText())) {
                validator.addError("Player already exists");
            }else if (playerNameTextField.getText().length() > 0) {
                Player player = new Player(playerNameTextField.getText(), Double.parseDouble(playerCreditTextField.getText()) ,Integer.parseInt(playerAgeTextField.getText()), Integer.parseInt(playerNumberTextField.getText()));
                getPlayers().addPlayer(player);
                stage.close();
            } else {
                validator.addError("Player name cannot be empty");
            }
            if (!validator.errors().isEmpty()) {
                throw new Exception();
            }
        } catch (Exception  e) {
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/view/error.png"));
            ViewLoader.showStage(validator, "/view/error.fxml", "Input Errors", stage);
        }
    }
    @FXML public void updatePlayer(){
        try {
            getPlayers().getPlayer(playerName).setName(playerNameTextField.getText());
            getPlayers().getPlayer(playerName).setAge(Integer.parseInt(playerAgeTextField.getText()));
            getPlayers().getPlayer(playerName).setCredit(Double.parseDouble(playerCreditTextField.getText()));
            getPlayers().getPlayer(playerName).setNo(Integer.parseInt(playerNumberTextField.getText()));
        } catch (Exception e) {
            stage.close();
        }
        stage.close();
    }
    @FXML public void close(){
        stage.close();
    }
    private Players getPlayers(){
        return this.model;
    }
    public void setupUpdatePlayer(String name){
        playerName = name;
        addPlayerButton.setDisable(true);
        playerNameTextField.setText(getPlayers().getPlayer(playerName).getName());
        playerAgeTextField.setText(Integer.toString(getPlayers().getPlayer(playerName).getAge()));
        playerCreditTextField.setText(Double.toString(getPlayers().getPlayer(playerName).getCredit()));
        playerNumberTextField.setText(Integer.toString(getPlayers().getPlayer(playerName).getNo()));
    }
    public void setupAddPlayer(){
        updatePlayerButton.setDisable(true);

    }
}
