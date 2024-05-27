package DTO;

import Models.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    private UUID id;//
    private String taskName;//
    private String title;
    private String status;
    private String priority;
    private String type;
    private UUID teamID;

    public TaskDTO(Task task) {
        this.id = task.getId();
        this.taskName = task.getTaskName();
        this.title = task.getDetails();
        this.status = task.getStatus();
        this.priority = task.getPriority();
        this.type = task.getType();
        this.teamID = task.getTeam().getId();
    }
}
