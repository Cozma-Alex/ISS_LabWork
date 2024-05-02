package javafx.client;

import DTO.EmployeeResponse;
import DTO.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.client.ManagerControllers.TeamsPageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class LoginController {

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    private Stage primaryStage;


    public void setData(Stage stage) {
        this.primaryStage = stage;
    }

    public void handleClose(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void handleLogin(ActionEvent actionEvent) {
        var email = "";
        var password = "";

        try {
            email = emailField.getText();
            password = passwordField.getText();

            HttpClient client = HttpClient.newHttpClient();
            LoginRequest loginRequest = new LoginRequest(email, password);
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(loginRequest);

            var request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/account/login"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            var responseBody = response.body();

            EmployeeResponse employeeResponse = objectMapper.readValue(responseBody, EmployeeResponse.class);

            switch (employeeResponse.getRole()) {
                case "Manager":
                    Font.loadFont(getClass().getResourceAsStream("/javafx/client/assets/fonts/Roboto/Roboto-Medium.ttf"), 12);
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/javafx/client/views/teams-page-view.fxml"));
                    AnchorPane root = fxmlLoader.load();
                    TeamsPageController controller = fxmlLoader.getController();
                    controller.setData(primaryStage, employeeResponse);
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    Platform.runLater(() -> {
                        primaryStage.setMaximized(false);
                        primaryStage.setMaximized(true);
                    });
                case "Programmer":
                    break;
                case "Tester":
                    break;
                default:
                    break;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}