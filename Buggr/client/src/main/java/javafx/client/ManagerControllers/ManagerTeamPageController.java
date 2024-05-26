package javafx.client.ManagerControllers;

import DTO.EmployeeResponse;
import DTO.TaskDTO;
import DTO.TeamDTO;
import Models.Task;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.client.UtilsControllers.HeaderController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.http.HttpResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Objects;

public class ManagerTeamPageController {

    public AnchorPane header;
    public Circle circleImageTeam;
    public Label teamNameLabel;
    public TableColumn titleColumn;
    public TableColumn checkboxColumn;
    public TableColumn taskColumn;
    public TableColumn statusColumn;
    public TableColumn priorityColumn;
    private Stage primaryStage;
    private EmployeeResponse employeeResponse;
    private TeamDTO team;

    public void setData(Stage primaryStage, EmployeeResponse employeeResponse, TeamDTO team) {
        this.primaryStage = primaryStage;
        this.employeeResponse = employeeResponse;
        this.team = team;
        initScene();
    }

    private void initScene() {

        Font.loadFont(getClass().getResourceAsStream("/javafx/client/assets/fonts/Roboto/Roboto-Medium.ttf"), 12);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/javafx/client/views/header.fxml"));
        try {
            AnchorPane root = fxmlLoader.load();
            HeaderController controller = fxmlLoader.getController();
            header.getChildren().setAll(root.getChildren());
            controller.setData(primaryStage, employeeResponse);
            String css = Objects.requireNonNull(getClass().getResource("/javafx/client/style/header-style.css")).toExternalForm();
            header.getStylesheets().add(css);
            header.setStyle("-fx-background-color: #e9eae9;");
            controller.setBackButton(true);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var url = "/javafx/client/database/teams/" + this.team.getProfilePicture();
        var profilePicture = new Image(url);
        var imagePattern = new ImagePattern(profilePicture);
        this.circleImageTeam.setFill(imagePattern);

        this.teamNameLabel.setText(this.team.getTeamName());

        setHeaderAlignment(titleColumn, "Title");
        setHeaderAlignment(checkboxColumn, "");
        setHeaderAlignment(taskColumn, "Task");
        setHeaderAlignment(statusColumn, "Status");
        setHeaderAlignment(priorityColumn, "Priority");

        ArrayList<TaskDTO> tasks = getAllTasks();

        for (TaskDTO task : tasks) {
            System.out.println(task);
        }

    }

    private ArrayList<TaskDTO> getAllTasks() {

        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/task/task/" + this.team.getId()))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return convertJsonToTeamDTOList(response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();

        }

        return new ArrayList<>();

    }

    private ArrayList<TaskDTO> convertJsonToTeamDTOList(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<TaskDTO> taskDTOs = null;
        try {
            taskDTOs = objectMapper.readValue(json, new TypeReference<ArrayList<TaskDTO>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskDTOs;
    }

    private void setHeaderAlignment(TableColumn<?, ?> column, String text) {
        Label label = new Label(text);
        label.setAlignment(Pos.CENTER_LEFT);
        label.setMaxWidth(Double.MAX_VALUE);

        HBox hbox = new HBox(label);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setMaxWidth(Double.MAX_VALUE);

        column.setGraphic(hbox);
        column.setStyle("-fx-alignment: CENTER-LEFT;");
    }
}
