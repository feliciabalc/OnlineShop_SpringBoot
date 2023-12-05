package map.project.demo.Service;

import map.project.demo.Entities.Articles;
import map.project.demo.DB_Repo.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepo articleRepo;

    @Autowired
    public ArticleService(ArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }

    public List<Articles> getAllArticles() {
        return (List<Articles>) articleRepo.findAll();
    }

    public Articles getArticleById(Long id) {
        return articleRepo.findById(id).orElse(null);
    }

    public Articles saveArticle(Articles article) {
        return articleRepo.save(article);
    }

    public void deleteArticle(Long id) {
        articleRepo.deleteById(id);
    }

    public List<Articles> filterByBrand(String brand){
        return articleRepo.findByBrand(brand);
    }


}
