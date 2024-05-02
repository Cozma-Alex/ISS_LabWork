package spring.server.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.server.Models.Manager;

import java.util.UUID;

@Repository
public interface RepositoryManager extends JpaRepository<Manager, UUID> {

}
