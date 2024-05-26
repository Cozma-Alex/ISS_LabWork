package javafx.client.ManagerControllers;

import DTO.EmployeeResponse;
import DTO.TeamDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;

import java.util.Objects;

public class ManagerTaskController {
    public RadioButton highPriorityRadioButton;
    public RadioButton mediumPriorityRadioButton;
    public RadioButton lowPriorityRadioButton;
    public RadioButton featureRadioButton;
    public RadioButton bugFixRadioButton;

    private EmployeeResponse employeeResponse;
    private TeamDTO team;
    private String type;

    public void setData(EmployeeResponse employeeResponse, TeamDTO team, String type) {
        this.employeeResponse = employeeResponse;
        this.team = team;
        this.type = type;
    }

    public void handleHighPriority(ActionEvent actionEvent) {
    }

    public void handleMediumPriority(ActionEvent actionEvent) {
    }

    public void handleLowPriority(ActionEvent actionEvent) {
    }

    public void handleFeature(ActionEvent actionEvent) {
    }

    public void handleBugFix(ActionEvent actionEvent) {
    }

    public void handleSaveChanges(ActionEvent actionEvent) {
        if (Objects.equals(this.type, "add")) {
            // add task //todo
        } else {
            // edit task //todo
        }
    }
}
