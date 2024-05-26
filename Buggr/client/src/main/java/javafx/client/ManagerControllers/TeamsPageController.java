package javafx.client.ManagerControllers;

import DTO.EmployeeResponse;
import DTO.TeamDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.client.Utils.HeaderController;
import javafx.client.Utils.TeamCardController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Objects;
import java.net.http.HttpResponse;
import java.util.concurrent.atomic.AtomicReference;


public class TeamsPageController {

    public AnchorPane header;
    public VBox mainContent;
    public AnchorPane mainPane;
    private Stage primaryStage;
    private EmployeeResponse employeeResponse;

    public void setData(Stage primaryStage, EmployeeResponse employeeResponse) {
        this.primaryStage = primaryStage;
        this.employeeResponse = employeeResponse;
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

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        initializeTeams();

    }

    private void initializeTeams() {

        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/team/manager/" + this.employeeResponse.getId()))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            var teamList = convertJsonToTeamDTOList(response.body());
            final int[] i = {0};
            AtomicReference<HBox> usedHBox = new AtomicReference<>(new HBox());
            teamList.forEach(team -> {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/javafx/client/views/team-card-view.fxml"));
                try {
                    AnchorPane root = fxmlLoader.load();
                    TeamCardController teamCard = fxmlLoader.getController();
                    teamCard.setData(team, primaryStage, employeeResponse);
                    if (i[0] % 3 == 0) {
                        Region space = new Region();
                        space.setPrefHeight(40);
                        Pane spacerPane = new Pane(space);
                        mainContent.getChildren().add(spacerPane);
                        var hbox = createNewLineOfCards();
                        mainContent.getChildren().add(hbox);
                        usedHBox.set(hbox);
                    }

                    if (i[0] % 3 == 0) {
                        usedHBox.get().getChildren().set(1, root);
                    } else if (i[0] % 3 == 1) {
                        usedHBox.get().getChildren().set(3, root);
                    } else {
                        usedHBox.get().getChildren().set(5, root);
                    }

                    i[0]++;

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private HBox createNewLineOfCards() {
        var hbox = new HBox();
        Region space = new Region();
        space.setPrefWidth(111);
        hbox.getChildren().add(space);
        int i = 0;
        while (i < 3) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/javafx/client/views/team-card-view.fxml"));
            try {
                AnchorPane root = fxmlLoader.load();
                root.setVisible(false);
                hbox.getChildren().add(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (i < 2) {
                Region spacer = new Region();
                spacer.setPrefWidth(222);
                hbox.getChildren().add(spacer);
            }
            i++;
        }

        return hbox;

    }

    public static ArrayList<TeamDTO> convertJsonToTeamDTOList(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<TeamDTO> teamDTOs = null;
        try {
            teamDTOs = objectMapper.readValue(json, new TypeReference<ArrayList<TeamDTO>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teamDTOs;
    }

}

