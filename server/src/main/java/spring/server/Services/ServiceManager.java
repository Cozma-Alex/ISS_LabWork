package spring.server.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.server.Models.Manager;
import spring.server.Repositories.RepositoryManager;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ServiceManager {

    private final RepositoryManager repositoryManager;

    public Manager createManager(Manager manager) {
        return repositoryManager.save(manager);
    }

    public Manager getManager(UUID id) {
        return repositoryManager.findById(id).orElse(null);
    }

    public void updateManager(UUID id, Manager manager) {
        Manager managerToUpdate = repositoryManager.findById(id).orElse(null);
        if (managerToUpdate != null) {
            managerToUpdate.setName(manager.getName());
            repositoryManager.save(managerToUpdate);
        } else {
            throw new IllegalArgumentException("Manager not found");
        }
    }

    public void deleteManager(UUID id) {
        repositoryManager.deleteById(id);
    }

    public Iterable<Manager> getAllManagers() {
        return repositoryManager.findAll();
    }


}
