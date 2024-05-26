package javafx.client.Utils;

import DTO.EmployeeResponse;
import DTO.TeamDTO;
import javafx.application.Platform;
import javafx.client.ManagerControllers.ManagerTeamPageController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class TeamCardController {


    public Circle circleProfilePicture;
    public Label teamNameLabel;
    public AnchorPane mainPane;

    private TeamDTO team;
    private Stage primaryStage;
    private EmployeeResponse employeeResponse;


    public void setData(TeamDTO team, Stage primaryStage, EmployeeResponse employeeResponse) {
        this.team = team;
        this.primaryStage = primaryStage;
        this.employeeResponse = employeeResponse;
        initScene();
    }

    private void initScene() {

        mainPane.setStyle("-fx-cursor: hand;");

        teamNameLabel.setText(team.getTeamName());
        teamNameLabel.setAlignment(Pos.CENTER);

        AnchorPane.setTopAnchor(teamNameLabel, 190.0);
        AnchorPane.setLeftAnchor(teamNameLabel, 0.0);
        AnchorPane.setRightAnchor(teamNameLabel, 0.0);

        var url = "/javafx/client/database/teams/" + this.team.getProfilePicture();

        Image profilePicture = new Image(url);

        ImagePattern imagePattern = new ImagePattern(profilePicture);
        this.circleProfilePicture.setFill(imagePattern);
        this.circleProfilePicture.setStrokeWidth(0);

    }

    public void handleTeamPage(MouseEvent mouseEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/javafx/client/views/manager-team-page-view.fxml"));
        try {
            AnchorPane root = fxmlLoader.load();
            ManagerTeamPageController controller = fxmlLoader.getController();
            controller.setData(primaryStage, employeeResponse, team);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            Platform.runLater(() -> {
                primaryStage.setMaximized(false);
                primaryStage.setMaximized(true);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
