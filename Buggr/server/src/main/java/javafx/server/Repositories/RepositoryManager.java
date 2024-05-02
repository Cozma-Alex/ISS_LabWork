package javafx.server.Repositories;

import Models.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositoryManager extends JpaRepository<Manager, UUID> {

}
