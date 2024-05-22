package javafx.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginController {

    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private AnchorPane mainPane;


    public void handleClose(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void handleLogin(ActionEvent actionEvent) {
        var email = "";
        var password = "";

        try {
            email = emailField.getText();
            password = passwordField.getText();



        } catch (Exception e) {
            e.printStackTrace();

        }
    }
