package spring.server.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.server.Repositories.RepositoryFeature;
import spring.server.Models.Feature;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ServiceFeature {

    private final RepositoryFeature repositoryFeature;

    public void deleteFeature(UUID id) {
        repositoryFeature.deleteById(id);
    }

    public Feature saveFeature(Feature feature) {
        return repositoryFeature.save(feature);
    }

    public Feature getFeature(UUID id) {
        return repositoryFeature.findById(id).orElse(null);
    }

    public Iterable<Feature> getAllFeatures() {
        return repositoryFeature.findAll();
    }

    public Feature updateFeature(UUID id, Feature feature) {
        Feature featureToUpdate = repositoryFeature.findById(id).orElse(null);
        if (featureToUpdate == null) {
            return null;
        }
        featureToUpdate.setName(feature.getName());
        featureToUpdate.setDescription(feature.getDescription());
        featureToUpdate.setLinkToUpload(feature.getLinkToUpload());
        featureToUpdate.setUploadMoment(feature.getUploadMoment());
        featureToUpdate.setTask(feature.getTask());
        return repositoryFeature.save(featureToUpdate);
    }

}
