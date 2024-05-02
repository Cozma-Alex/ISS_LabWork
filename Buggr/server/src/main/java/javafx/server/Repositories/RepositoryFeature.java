package javafx.server.Repositories;

import Models.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositoryFeature extends JpaRepository<Feature, UUID> {

}
