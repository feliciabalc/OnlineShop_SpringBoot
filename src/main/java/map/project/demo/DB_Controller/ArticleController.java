package map.project.demo.DB_Controller;

import map.project.demo.Entities.*;
import map.project.demo.Service.ArticleService;
import map.project.demo.Service.SpecificationsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articlesService;
    private final SpecificationsService specificationsService;

    public ArticleController(ArticleService articlesService, SpecificationsService specificationsService) {
        this.articlesService = articlesService;
        this.specificationsService = specificationsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Articles> getArticleById(@PathVariable Long id) {
        Articles article = articlesService.getArticleById(id);

        if (article != null) {
            return new ResponseEntity<>(article, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Articles>> getAllArticles() {
        List<Articles> articles = (List<Articles>) articlesService.getAllArticles();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articlesService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/filterByBrand")
    public List<Articles> filterByBrand(@RequestParam String brand) {
        return articlesService.filterByBrand(brand);
    }

    @GetMapping("/filterByMaterial")
    public List<Articles> filterByMaterial(@RequestParam String material) {
        return articlesService.filterByMaterial(material);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Articles> updateArticle(@PathVariable Long id, @RequestBody Articles updatedArticle) {
        Articles existingArticle = articlesService.getArticleById(id);
        if (existingArticle != null) {
            updatedArticle.setId(id);
            Articles savedArticle = articlesService.saveArticle(updatedArticle);
            return new ResponseEntity<>(savedArticle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Articles> createArticle(@RequestBody Articles newArticle) {
            Articles savedArticle = articlesService.saveArticle(newArticle);
            return new ResponseEntity<>(savedArticle, HttpStatus.OK);

    }




    @GetMapping("/{articleId}/specifications")
    public ResponseEntity<List<Specifications>> getSpecificationsForArticle(@PathVariable Long articleId) throws Exception {
        List<Specifications> specifications = articlesService.getSpecificationsForArticle(articleId);
        return new ResponseEntity<>(specifications, HttpStatus.OK);
    }


    @GetMapping("/{articleId}/review")
    public ResponseEntity<List<Review>> getReviewsForArticle(@PathVariable Long articleId) throws Exception {
        List<Review> review = articlesService.getReviewForArticle(articleId);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }


    @GetMapping("/{articleId}/orders")
    public ResponseEntity<List<Orders>> getOrdersForArticle(@PathVariable Long articleId) throws Exception {
        List<Orders> order = articlesService.getOrderForArticle(articleId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }


    @GetMapping("/{articleId}/warehouse")
    public ResponseEntity<Warehouse> getWarehouseForArticle(@PathVariable Long articleId) throws Exception {
        Warehouse warehouse = articlesService.getWarehouseForArticle(articleId);
        return new ResponseEntity<>(warehouse, HttpStatus.OK);
    }

    @PostMapping("/{articleId}/addSpecification")
    public ResponseEntity<String> addSpecificationToArticle(@PathVariable Long articleId, @RequestBody Long specId) throws Exception {
        Articles article = articlesService.getArticleById(articleId);
        Specifications specification = specificationsService.getSpecificationsById(specId);

        if (article == null || specification == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article or Specification not found");
        }

        articlesService.addSpecificationToArticle(articleId, specification);

        return ResponseEntity.ok("Specification added to the article successfully");
    }




}
