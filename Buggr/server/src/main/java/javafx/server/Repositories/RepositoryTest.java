package javafx.server.Repositories;

import Models.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositoryTest extends JpaRepository<Test, UUID> {

}
