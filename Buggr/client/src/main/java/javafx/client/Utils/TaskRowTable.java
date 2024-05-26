package javafx.client.Utils;

import DTO.TaskDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRowTable{

    private UUID id;
    private String taskName;
    private String title;
    private String status;
    private String priority;
    private String type;
    private UUID teamID;
    private String checkbox;

    public TaskRowTable(TaskDTO task) {
        this.id = task.getId();
        this.taskName = task.getTaskName();
        this.title = task.getTitle();
        this.status = task.getStatus();
        this.priority = task.getPriority();
        this.type = task.getType();
        this.teamID = task.getTeamID();
        this.checkbox = "false";
    }
}
