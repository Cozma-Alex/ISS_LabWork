package javafx.server.Repositories;

import Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
public interface RepositoryTask extends JpaRepository<Task, UUID> {

    @Query("SELECT t FROM Task t WHERE t.team.id = ?1")
    ArrayList<Task> findByTeam(UUID idTeam);
}
