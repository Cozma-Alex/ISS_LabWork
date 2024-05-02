package javafx.server.Repositories;

import Models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
public interface RepositoryTeam extends JpaRepository<Team, UUID> {

    @Query("SELECT t FROM Team t WHERE t.manager.id = ?1")
    ArrayList<Team> findByManagerId(UUID id);
}
