package controller;
import au.edu.uts.ap.javafx.ViewLoader;
import au.edu.uts.ap.javafx.Controller;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;


public class ErrorController extends Controller<Validator>{

    @FXML
    private GridPane errorMessageLabel;

    @FXML
    private void initialize() {
        int count = 0;
        for (String error : getException().errors()) {
            Label messageLabel = new Label(error);
            messageLabel.getStyleClass().add("text-out");
            messageLabel.setStyle("-fx-text-fill: yellow;");
            GridPane.setConstraints(messageLabel, 0, count);
            errorMessageLabel.getChildren().add(messageLabel);
            count++;
        }
    }

    public Validator getException(){
        return this.model;
    }

    @FXML
    public void close(){
        stage.close();
    }
    
}
