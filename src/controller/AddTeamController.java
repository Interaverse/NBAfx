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
import controller.Validator;


public class AddTeamController extends Controller<Teams> {
    @FXML
    private TextField teamNameField;
    @FXML
    public void addTeam() throws Exception {
        Validator validator = new Validator();
        try {
            if (getTeams().hasTeam(teamNameField.getText())) {
                throw new Exception(teamNameField.getText() + " Team already exists");
            }else if (teamNameField.getText().length() > 0) {
                getTeams().addTeam(new Team(teamNameField.getText()));
                stage.close();
            } else {
                throw new Exception("Team name cannot be empty");
            }
        } catch (Exception e) {
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/view/error.png"));
            validator.addError(e.getMessage());
            ViewLoader.showStage(validator, "/view/error.fxml", "Error", stage);
        }

    }
    public Teams getTeams(){
        return this.model;
    }
    
}
