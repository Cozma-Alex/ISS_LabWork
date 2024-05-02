package spring.server.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.server.Models.Programmer;

import java.util.UUID;

@Repository
public interface RepositoryProgrammer extends JpaRepository<Programmer, UUID> {

}
