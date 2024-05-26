package javafx.client.UtilsControllers;

import DTO.EmployeeResponse;
import javafx.application.Platform;
import javafx.client.LoginController;
import javafx.client.ManagerControllers.TeamsPageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class HeaderController {
    public Button closeButton;
    public Button logoutButton;
    public AnchorPane mainPane;
    public Circle profilePictureCircle;
    public Label usernameLabel;
    public Button backButton;

    private Stage primaryStage;
    private EmployeeResponse employeeResponse;


    public void handleClose(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void handleLogout(ActionEvent actionEvent) throws IOException {
        Font.loadFont(getClass().getResourceAsStream("/javafx/client/assets/fonts/Roboto/Roboto-Medium.ttf"), 12);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/javafx/client/views/login-view.fxml"));
        AnchorPane root = fxmlLoader.load();
        LoginController controller = fxmlLoader.getController();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        controller.setData(primaryStage);
        Platform.runLater(() -> {
            primaryStage.setMaximized(false);
            primaryStage.setMaximized(true);
        });
    }

    public void setData(Stage primaryStage, EmployeeResponse employeeResponse) {
        this.primaryStage = primaryStage;
        this.employeeResponse = employeeResponse;

        usernameLabel.setText(this.employeeResponse.getName());
        var url = "/javafx/client/database/users/" + this.employeeResponse.getProfilePicture();

        Image profilePicture = new Image(url);

        ImagePattern imagePattern = new ImagePattern(profilePicture);
        this.profilePictureCircle.setFill(imagePattern);
        closeButton.setStyle("-fx-cursor: hand;");
        logoutButton.setStyle("-fx-cursor: hand;");
        backButton.setStyle("-fx-cursor: hand;");
        backButton.setVisible(false);

    }

    public void handleGoBack(ActionEvent actionEvent) throws IOException {
        Font.loadFont(getClass().getResourceAsStream("/javafx/client/assets/fonts/Roboto/Roboto-Medium.ttf"), 12);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/javafx/client/views/teams-page-view.fxml"));
        AnchorPane root = fxmlLoader.load();
        TeamsPageController controller = fxmlLoader.getController();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        controller.setData(primaryStage, employeeResponse);
        Platform.runLater(() -> {
            primaryStage.setMaximized(false);
            primaryStage.setMaximized(true);
        });
    }

    public void setBackButton(boolean b) {
        backButton.setVisible(b);
    }
}
