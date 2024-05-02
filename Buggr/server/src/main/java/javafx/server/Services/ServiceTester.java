package javafx.server.Services;

import Models.Tester;
import javafx.server.Repositories.RepositoryTester;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ServiceTester {

    private final RepositoryTester repositoryTester;

    public Tester createTester(Tester tester) {
        return repositoryTester.save(tester);
    }

    public Tester getTester(UUID id) {
        return repositoryTester.findById(id).orElse(null);
    }

    public void updateTester(UUID id, Tester tester) {
        Tester testerToUpdate = repositoryTester.findById(id).orElse(null);
        if (testerToUpdate != null) {
            testerToUpdate.setName(tester.getName());
            testerToUpdate.setAccount(tester.getAccount());
            testerToUpdate.setTeam(tester.getTeam());
            repositoryTester.save(testerToUpdate);
        } else {
            throw new IllegalArgumentException("Tester not found");
        }
    }

    public void deleteTester(UUID id) {
        repositoryTester.deleteById(id);
    }

    public Iterable<Tester> getAllTesters() {
        return repositoryTester.findAll();
    }

}
