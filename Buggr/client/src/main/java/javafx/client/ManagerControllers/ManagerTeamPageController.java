package javafx.client.ManagerControllers;

import DTO.EmployeeResponse;
import DTO.TaskDTO;
import DTO.TeamDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.client.Utils.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

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

    public TableColumn<TaskRowTable, TaskRowTable> titleColumn;
    public TableColumn<TaskRowTable, String> checkboxColumn;
    public TableColumn<TaskRowTable, String> taskColumn;
    public TableColumn<TaskRowTable, String> statusColumn;
    public TableColumn<TaskRowTable, String> priorityColumn;
    public TableView tableView;

    public ArrayList<TaskRowTable> selectedTasks = new ArrayList<>();

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

        ArrayList<TaskDTO> tasks = new ArrayList<>();
        while (tasks.isEmpty()) {
            tasks = getAllTasks();
        }

        ArrayList<TaskRowTable> taskRowTables = new ArrayList<>();
        for (var task : tasks) {
            taskRowTables.add(new TaskRowTable(task));
        }

        addTasksToTable(taskRowTables);

    }

    private void addTasksToTable(ArrayList<TaskRowTable> tasks) {

        checkboxColumn.setCellValueFactory(new PropertyValueFactory<>("checkbox"));
        checkboxColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(null);
                    Label checkbox = new Label();
                    ImageView imageView = new ImageView();
                    imageView.setFitHeight(20);
                    imageView.setFitWidth(20);
                    checkbox.setGraphic(imageView);

                    if ("true".equals(item)) {
                        imageView.setImage(new Image("javafx/client/assets/images/filled_checkbox.png"));
                    } else {
                        imageView.setImage(new Image("javafx/client/assets/images/checkbox.png"));
                    }

                    checkboxColumn.setCellFactory(column -> new TableCell<>() {
                        @Override
                        protected void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty || item == null) {
                                setText(null);
                                setGraphic(null);
                            } else {
                                setText(null);
                                Label checkbox = new Label();
                                ImageView imageView = new ImageView();
                                imageView.setFitHeight(20);
                                imageView.setFitWidth(20);
                                checkbox.setGraphic(imageView);

                                if ("true".equals(item)) {
                                    imageView.setImage(new Image("javafx/client/assets/images/filled_checkbox.png"));
                                } else {
                                    imageView.setImage(new Image("javafx/client/assets/images/checkbox.png"));
                                }

                                checkbox.setOnMouseClicked(event -> {
                                    TaskRowTable taskRowTable = getTableView().getItems().get(getIndex());
                                    if ("true".equals(taskRowTable.getCheckbox())) {
                                        imageView.setImage(new Image("javafx/client/assets/images/checkbox.png"));
                                        taskRowTable.setCheckbox("false");
                                        selectedTasks.remove(taskRowTable);
                                        System.out.println(selectedTasks.size());
                                    } else {
                                        imageView.setImage(new Image("javafx/client/assets/images/filled_checkbox.png"));
                                        taskRowTable.setCheckbox("true");
                                        selectedTasks.add(taskRowTable);
                                        System.out.println(selectedTasks.size());
                                    }
                                    getTableView().refresh();
                                });

                                setGraphic(checkbox);
                            }
                        }
                    });

                    setGraphic(checkbox);
                }
            }
        });

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue()));
        titleColumn.setCellFactory(new Callback<TableColumn<TaskRowTable, TaskRowTable>, TableCell<TaskRowTable, TaskRowTable>>() {
            @Override
            public TableCell<TaskRowTable, TaskRowTable> call(TableColumn<TaskRowTable, TaskRowTable> param) {
                return new TaskTitleTableCell();
            }
        });

        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumn.setCellFactory(new Callback<TableColumn<TaskRowTable, String>, TableCell<TaskRowTable, String>>() {
            @Override
            public TableCell<TaskRowTable, String> call(TableColumn<TaskRowTable, String> param) {
                return new TaskStatusTableCell();
            }
        });

        taskColumn.setCellValueFactory(new PropertyValueFactory<>("taskName"));



        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        priorityColumn.setCellFactory(new Callback<TableColumn<TaskRowTable, String>, TableCell<TaskRowTable, String>>() {
            @Override
            public TableCell<TaskRowTable, String> call(TableColumn<TaskRowTable, String> param) {
                return new TaskPriorityTableCell();
            }
        });

        tableView.getItems().addAll(tasks);

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

    public void handleAddNewTask(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/javafx/client/views/manager-task-controller.fxml"));
        try {
            AnchorPane root = fxmlLoader.load();
            ManagerTaskController controller = fxmlLoader.getController();
            controller.setData( employeeResponse, team, "add");
            Stage stage = new Stage();
            stage.setScene(new javafx.scene.Scene(root));
            stage.show();
            tableView.getItems().clear();
            tableView.getItems().addAll(getAllTasks());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleUpdateTask(ActionEvent actionEvent) {
    }

    public void handleCloseTask(ActionEvent actionEvent) {
    }

    public void handleAddAccount(ActionEvent actionEvent) {
    }

    public void handleUpdateAccount(ActionEvent actionEvent) {
    }

    public void handleRemoveAccount(ActionEvent actionEvent) {
    }
}
