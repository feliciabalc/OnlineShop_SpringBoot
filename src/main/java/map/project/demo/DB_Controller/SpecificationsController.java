package map.project.demo.DB_Controller;

import map.project.demo.Entities.*;
import map.project.demo.Service.ArticleService;
import map.project.demo.Service.CartService;
import map.project.demo.Service.SpecificationsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specifications")
public class SpecificationsController {
    private final SpecificationsService specificationsService;
    private final ArticleService articleService;

    public SpecificationsController(SpecificationsService specificationsService, ArticleService articleService) {
        this.specificationsService = specificationsService;
        this.articleService = articleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Specifications> getSpecificationsById(@PathVariable Long id) {
        Specifications specifications = specificationsService.getSpecificationsById(id);

        if (specifications != null) {
            return new ResponseEntity<>(specifications, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Specifications>> getAllSpecifications() {
        List<Specifications> specifications = (List<Specifications>) specificationsService.getAllSpecificationss();
        return new ResponseEntity<>(specifications, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecifications(@PathVariable Long id) {
        specificationsService.deleteSpecifications(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Specifications> updateSpecifications(@PathVariable Long id, @RequestBody Specifications updatedSpecifications) {
        Specifications existingSpecifications = specificationsService.getSpecificationsById(id);
        if (existingSpecifications != null) {
            updatedSpecifications.setId(id);
            Specifications savedSpecifications = specificationsService.saveSpecifications(updatedSpecifications);
            return new ResponseEntity<>(savedSpecifications, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{specificationId}/articles")
    public ResponseEntity<Articles> getArticleForSpecifications(@PathVariable Long specificationId) throws Exception {
        Articles articles= specificationsService.getArticleForSpecifications(specificationId);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Specifications> createSpecifications(@RequestBody Specifications newSpecifications) {
        Specifications savedSpecifications = specificationsService.saveSpecifications(newSpecifications);
        return new ResponseEntity<>(savedSpecifications, HttpStatus.OK);
    }

    @GetMapping("/filterByColor")
    public List<Specifications> filterByColor(@RequestParam String color) {
        return specificationsService.filteredByColor(color);
    }

    @GetMapping("/filterBySize")
    public List<Specifications> filterBySize(@RequestParam String size) {
        return specificationsService.filteredBySize(size);
    }

    @PostMapping("/{specId}/addArticle")
    public ResponseEntity<String> addArticleToSpecification(@PathVariable Long specId, @RequestBody Long articleId) throws Exception {
        Specifications specifications = specificationsService.getSpecificationsById(specId);
        Articles article = articleService.getArticleById(specId);

        if (article == null || specifications == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article or Specification not found");
        }

        specificationsService.addArticleToSpecification(specId, article);

        return ResponseEntity.ok("Article added to the specification successfully");
    }

}
