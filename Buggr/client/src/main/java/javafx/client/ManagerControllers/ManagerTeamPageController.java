package javafx.client.ManagerControllers;

import DTO.EmployeeResponse;
import DTO.TeamDTO;
import javafx.stage.Stage;

public class ManagerTeamPageController {

    private Stage primaryStage;
    private EmployeeResponse employeeResponse;
    private TeamDTO team;

    public void setData(Stage primaryStage, EmployeeResponse employeeResponse, TeamDTO team) {
        this.primaryStage = primaryStage;
        this.employeeResponse = employeeResponse;
        this.team = team;
    }
}
