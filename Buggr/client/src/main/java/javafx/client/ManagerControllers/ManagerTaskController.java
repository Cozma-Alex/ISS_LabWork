package javafx.client.ManagerControllers;

import DTO.EmployeeResponse;
import DTO.TaskDTO;
import DTO.TeamDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.UUID;

public class ManagerTaskController {
    public RadioButton highPriorityRadioButton;
    public RadioButton mediumPriorityRadioButton;
    public RadioButton lowPriorityRadioButton;
    public RadioButton featureRadioButton;
    public RadioButton bugFixRadioButton;
    public TextArea detailsTextArea;
    public TextField deadlineTextField;

    private EmployeeResponse employeeResponse;
    private TeamDTO team;
    private String type;

    private String priority;
    private String taskType;

    public void setData(EmployeeResponse employeeResponse, TeamDTO team, String type) {
        this.employeeResponse = employeeResponse;
        this.team = team;
        this.type = type;
    }

    public void handleHighPriority(ActionEvent actionEvent) {
        priority = "high";
        mediumPriorityRadioButton.setSelected(false);
        lowPriorityRadioButton.setSelected(false);
    }

    public void handleMediumPriority(ActionEvent actionEvent) {
        priority = "medium";
        highPriorityRadioButton.setSelected(false);
        lowPriorityRadioButton.setSelected(false);
    }

    public void handleLowPriority(ActionEvent actionEvent) {
        priority = "low";
        highPriorityRadioButton.setSelected(false);
        mediumPriorityRadioButton.setSelected(false);
    }

    public void handleFeature(ActionEvent actionEvent) {
        taskType = "feature";
        bugFixRadioButton.setSelected(false);
    }

    public void handleBugFix(ActionEvent actionEvent) {
        taskType = "bug";
        featureRadioButton.setSelected(false);
    }

    public void handleSaveChanges(ActionEvent actionEvent) {
        if (Objects.equals(this.type, "add")) {
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setPriority(priority);
            taskDTO.setType(taskType);
            taskDTO.setTeamID(team.getId());
            taskDTO.setStatus("todo");
            taskDTO.setId(UUID.randomUUID());
            taskDTO.setTaskName("TASK" + taskDTO.getId().toString().substring(0, 5));
            taskDTO.setTitle(detailsTextArea.getText());

            addTask(taskDTO);

        } else {
            // edit task //todo
        }
    }

    private void addTask(TaskDTO taskDTO) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(taskDTO);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/task/task"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("Task added successfully");
            } else {
                System.out.println("Error occurred: " + response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
