package javafx.server.Services;

import Models.Task;
import javafx.server.Repositories.RepositoryTask;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ServiceTask {

    private final RepositoryTask repositoryTask;

    public void deleteTask(UUID id) {
        repositoryTask.deleteById(id);
    }

    public Iterable<Task> getAllTasks() {
        return repositoryTask.findAll();
    }

    public Task getTask(UUID id) {
        return repositoryTask.findById(id).orElse(null);
    }

    public Task createTask(Task task) {
        return repositoryTask.save(task);
    }

    public void updateTask(UUID id, Task task) {
        Task taskToUpdate = repositoryTask.findById(id).orElse(null);
        if (taskToUpdate != null) {
            taskToUpdate.setTaskName(task.getTaskName());
            taskToUpdate.setDetails(task.getDetails());
            taskToUpdate.setDeadline(task.getDeadline());
            taskToUpdate.setStatus(task.getStatus());
            taskToUpdate.setPriority(task.getPriority());
            taskToUpdate.setType(task.getType());
            taskToUpdate.setTeam(task.getTeam());
            repositoryTask.save(taskToUpdate);
        } else {
            throw new IllegalArgumentException("Task not found");
        }
    }

    public ArrayList<Task> getTasksByTeam(UUID idTeam) {
        return repositoryTask.findByTeam(idTeam);
    }
}
