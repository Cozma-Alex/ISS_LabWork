package javafx.server.Repositories;

import Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositoryTask extends JpaRepository<Task, UUID> {

}
