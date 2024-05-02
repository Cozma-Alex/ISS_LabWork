package spring.server.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.server.Models.Tester;

import java.util.UUID;

@Repository
public interface RepositoryTester extends JpaRepository<Tester, UUID> {

}
