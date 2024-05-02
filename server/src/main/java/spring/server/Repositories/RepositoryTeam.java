package spring.server.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.server.Models.Team;

import java.util.UUID;

@Repository
public interface RepositoryTeam extends JpaRepository<Team, UUID> {

}
