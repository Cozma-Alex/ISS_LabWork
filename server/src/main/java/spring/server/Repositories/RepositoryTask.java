package spring.server.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.server.Models.Task;

import java.util.UUID;

@Repository
public interface RepositoryTask extends JpaRepository<Task, UUID> {

}
