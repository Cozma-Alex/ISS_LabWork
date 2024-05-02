package spring.server.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.server.Models.Test;

import java.util.UUID;

@Repository
public interface RepositoryTest extends JpaRepository<Test, UUID> {

}
