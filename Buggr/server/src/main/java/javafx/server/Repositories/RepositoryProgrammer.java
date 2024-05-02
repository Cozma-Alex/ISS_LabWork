package javafx.server.Repositories;

import Models.Programmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositoryProgrammer extends JpaRepository<Programmer, UUID> {

}
