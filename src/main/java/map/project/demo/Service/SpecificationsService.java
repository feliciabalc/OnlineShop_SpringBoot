package map.project.demo.Service;

import map.project.demo.DB_Repo.ReviewRepo;
import map.project.demo.DB_Repo.SpecificationsRepo;
import map.project.demo.Entities.Review;
import map.project.demo.Entities.Specifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SpecificationsService {

    private final SpecificationsRepo specificationsRepo;

    @Autowired
    public SpecificationsService(SpecificationsRepo specificationsRepo) {
        this.specificationsRepo = specificationsRepo;
    }

    public List<Specifications> getAllSpecificationss() {
        return (List<Specifications>) specificationsRepo.findAll();
    }

    public Specifications getSpecificationsById(Long id) {
        return specificationsRepo.findById(id).orElse(null);
    }

    public Specifications saveSpecifications(Specifications specifications) {
        return specificationsRepo.save(specifications);
    }

    public void deleteSpecifications(Long id) {
        specificationsRepo.deleteById(id);
    }
}
