package map.project.demo.DB_Controller;

import map.project.demo.Service.ArticleService;
import map.project.demo.Entities.Articles;
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


}