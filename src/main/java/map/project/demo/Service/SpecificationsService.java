package map.project.demo.Service;

import map.project.demo.DB_Repo.ReviewRepo;
import map.project.demo.DB_Repo.SpecificationsRepo;
import map.project.demo.Entities.*;
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


    public Articles getArticleForSpecifications(Long specificationId) throws Exception {
        Specifications specification = specificationsRepo.findById(specificationId)
                .orElseThrow(() -> new Exception("Specification not found with id " + specificationId));

        return specification.getArticle();
    }

    public List<Specifications> filteredByColor(String color) {
        return specificationsRepo.filteredByColor(color);
    }
    public List<Specifications> filteredBySize(String size) {
        return specificationsRepo.filteredBySize(size);
    }

    public void addArticleToSpecification(Long specificationsId, Articles article) throws Exception {
        Specifications specifications = specificationsRepo.findById(specificationsId)
                .orElseThrow(() -> new Exception("Specification not found with id " + specificationsId));

        specifications.setArticle(article);
        specificationsRepo.save(specifications);
    }
}
