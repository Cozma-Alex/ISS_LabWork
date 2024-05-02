package javafx.server.Services;

import Models.Test;
import javafx.server.Repositories.RepositoryTest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ServiceTest {

    private final RepositoryTest repositoryTest;

    public Test createTest(Test test) {
        return repositoryTest.save(test);
    }

    public Test getTest(UUID id) {
        return repositoryTest.findById(id).orElse(null);
    }

    public void updateTest(UUID id, Test test) {
        Test testToUpdate = repositoryTest.findById(id).orElse(null);
        if (testToUpdate != null) {
            testToUpdate.setName(test.getName());
            testToUpdate.setDescription(test.getDescription());
            testToUpdate.setLinkToUpload(test.getLinkToUpload());
            testToUpdate.setUploadMoment(test.getUploadMoment());
            testToUpdate.setTask(test.getTask());
            repositoryTest.save(testToUpdate);
        } else {
            throw new IllegalArgumentException("Test not found");
        }
    }

    public void deleteTest(UUID id) {
        repositoryTest.deleteById(id);
    }

    public Iterable<Test> getAllTests() {
        return repositoryTest.findAll();
    }

}
