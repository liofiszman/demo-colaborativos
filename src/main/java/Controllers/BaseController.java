package Controllers;

import home.HelloApplication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class BaseController implements Initializable {


    @FXML
    protected void backToHome(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        HelloApplication.home(stage);
    }

    protected static void backToHomeStatic(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        HelloApplication.home(stage);
    }

    private static Button getBackButton() {
        var backButton = new Button();
        backButton.setAlignment(Pos.BOTTOM_CENTER);

        backButton.setText("X");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    backToHomeStatic(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return backButton;
    }
}
