package map.project.demo.DB_Controller;


import jakarta.websocket.server.PathParam;
import map.project.demo.DB_Repo.ArticleRepo;
import map.project.demo.Entities.Articles;
import map.project.demo.Entities.Review;
import map.project.demo.Entities.Specifications;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/articles")
public class ArticleControllerDB {
    private ArticleRepo articleRepo;
    //private SpecificationsRepoDB specificationsRepo;
    //private ReviewRepoDB reviewRepo;

    public ArticleControllerDB(ArticleRepo articleRepo/*, SpecificationsRepoDB specificationsRepo, ReviewRepoDB reviewRepo*/) {
        this.articleRepo = articleRepo;
        //this.specificationsRepo = specificationsRepo;
        //this.reviewRepo = reviewRepo;
    }



//    @GetMapping("/{id}")
//    public ResponseEntity<Articles> getArticleById(@PathVariable Long id) {
//        // Your implementation to retrieve and return the article by id
//        Optional<Articles> article = articleRepo.findById(id);
//
//            return new ResponseEntity<>(article, HttpStatus.NOT_FOUND);
//    }

    @GetMapping
    public ResponseEntity<List<Articles>> getAllArticles() {
        // Your implementation to retrieve and return articles
        List<Articles> articles = (List<Articles>) articleRepo.findAll();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

//    @RequestMapping(method = RequestMethod.DELETE, path="api/articles/id")
//    public void delete(int Id) {
//        articleRepo.delete(Id);
//    }

    @GetMapping("/filterByBrand")
    public List<Articles> filterByBrand(@RequestParam String brand) {
        return articleRepo.findByBrand(brand);
    }

}
