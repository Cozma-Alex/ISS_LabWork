package javafx.server.API.Controllers;

import DTO.TaskDTO;
import javafx.server.Services.ServiceTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class ControllerTask {

    @Autowired
    private ServiceTask serviceTask;

    @GetMapping("task/{idTeam}")
    public ResponseEntity<ArrayList<TaskDTO>> getTasksByTeam(@PathVariable UUID idTeam) {
        var tasks = serviceTask.getTasksByTeam(idTeam);
        var taskDTOs = new ArrayList<TaskDTO>();
        for (var task : tasks) {
            taskDTOs.add(new TaskDTO(task));
        }
        return ResponseEntity.ok(taskDTOs);
    }

}
