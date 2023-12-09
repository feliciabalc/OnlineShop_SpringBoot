package map.project.demo.DB_Controller;

import map.project.demo.Entities.*;
import map.project.demo.Service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }
    //private SpecificationsRepoDB specificationsRepo;
    //private ReviewRepoDB reviewRepo;





    @GetMapping("/{id}")
    public ResponseEntity<Articles> getArticleById(@PathVariable Long id) {
        Articles article = articleService.getArticleById(id);

        if (article != null) {
            return new ResponseEntity<>(article, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Articles>> getAllArticles() {
        List<Articles> articles = (List<Articles>) articleService.getAllArticles();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/filterByBrand")
    public List<Articles> filterByBrand(@RequestParam String brand) {
        return articleService.filterByBrand(brand);
    }

    @GetMapping("/filterByMaterial")
    public List<Articles> filterByMaterial(@RequestParam String material) {
        return articleService.filterByMaterial(material);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Articles> updateArticle(@PathVariable Long id, @RequestBody Articles updatedArticle) {
        Articles existingArticle = articleService.getArticleById(id);
        if (existingArticle != null) {
            updatedArticle.setId(id);
            Articles savedArticle = articleService.saveArticle(updatedArticle);
            return new ResponseEntity<>(savedArticle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Articles> createArticle(@RequestBody Articles newArticle) {
            Articles savedArticle = articleService.saveArticle(newArticle);
            return new ResponseEntity<>(savedArticle, HttpStatus.OK);

    }



//    public ResponseEntity<Void> addSpecificationToArticle(
//            @PathVariable int articleId,
//            @RequestBody Specifications specification) {
//        articleService.addSpecificationToArticle(articleId, specification);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @GetMapping("/{articleId}/specifications")
    public ResponseEntity<List<Specifications>> getSpecificationsForArticle(@PathVariable Long articleId) throws Exception {
        List<Specifications> specifications = articleService.getSpecificationsForArticle(articleId);
        return new ResponseEntity<>(specifications, HttpStatus.OK);
    }


    @GetMapping("/{articleId}/review")
    public ResponseEntity<List<Review>> getReviewsForArticle(@PathVariable Long articleId) throws Exception {
        List<Review> review = articleService.getReviewForArticle(articleId);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }


    @GetMapping("/{articleId}/orders")
    public ResponseEntity<List<Orders>> getOrdersForArticle(@PathVariable Long articleId) throws Exception {
        List<Orders> order = articleService.getOrderForArticle(articleId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }


    @GetMapping("/{articleId}/warehouse")
    public ResponseEntity<Warehouse> getWarehouseForArticle(@PathVariable Long articleId) throws Exception {
        Warehouse warehouse = articleService.getWarehouseForArticle(articleId);
        return new ResponseEntity<>(warehouse, HttpStatus.OK);
    }


}
