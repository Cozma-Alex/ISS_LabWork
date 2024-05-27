package javafx.server.API.Controllers;

import DTO.TaskDTO;
import Models.Task;
import javafx.server.Services.ServiceTask;
import javafx.server.Services.ServiceTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class ControllerTask {

    @Autowired
    private ServiceTask serviceTask;

    @Autowired
    private ServiceTeam serviceTeam;

    @GetMapping("/task/{idTeam}")
    public ResponseEntity<ArrayList<TaskDTO>> getTasksByTeam(@PathVariable UUID idTeam) {
        var tasks = serviceTask.getTasksByTeam(idTeam);
        var taskDTOs = new ArrayList<TaskDTO>();
        for (var task : tasks) {
            taskDTOs.add(new TaskDTO(task));
        }
        return ResponseEntity.ok(taskDTOs);
    }

    @PostMapping("/task")
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        Task task = new Task(taskDTO);
        var team = serviceTeam.getTeam(taskDTO.getTeamID());
        task.setTeam(team);
        var tasks = serviceTask.createTask(task);
        return ResponseEntity.ok(new TaskDTO(tasks));
    }

}
