package spring.server.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.server.Models.Task;
import spring.server.Repositories.RepositoryTask;

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

}
