package spring.server.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.server.Models.Feature;

import java.util.UUID;

@Repository
public interface RepositoryFeature extends JpaRepository<Feature, UUID> {

}
