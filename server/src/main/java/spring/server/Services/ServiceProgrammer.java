package spring.server.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.server.Models.Programmer;
import spring.server.Repositories.RepositoryProgrammer;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ServiceProgrammer {

    private final RepositoryProgrammer repositoryProgrammer;

    public Programmer createProgrammer(Programmer programmer) {
        return repositoryProgrammer.save(programmer);
    }

    public Programmer getProgrammer(UUID id) {
        return repositoryProgrammer.findById(id).orElse(null);
    }

    public void updateProgrammer(UUID id, Programmer programmer) {
        Programmer programmerToUpdate = repositoryProgrammer.findById(id).orElse(null);
        if (programmerToUpdate != null) {
            programmerToUpdate.setName(programmer.getName());
            repositoryProgrammer.save(programmerToUpdate);
        } else {
            throw new IllegalArgumentException("Programmer not found");
        }
    }

    public void deleteProgrammer(UUID id) {
        repositoryProgrammer.deleteById(id);
    }

    public Iterable<Programmer> getAllProgrammers() {
        return repositoryProgrammer.findAll();
    }

}
