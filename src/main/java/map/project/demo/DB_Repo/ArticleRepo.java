package map.project.demo.DB_Repo;


import map.project.demo.Entities.Articles;
import map.project.demo.Entities.Specifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public interface ArticleRepo extends JpaRepository<Articles, Long> {

    default List<Articles> findByBrand(String brand){
        List<Articles> articles = findAll();
        List<Articles> filteredArticles = new ArrayList<>();
        for (Articles item : articles) {
            if (Objects.equals(item.getBrand(), brand))
                filteredArticles.add(item);
        }
        return filteredArticles;
    }

    default List<Articles> filteredByMaterial(String material) {
        List<Articles> articles = findAll();
        List<Articles> filteredArticles = new ArrayList<>();
        for (Articles item : articles) {
            if (Objects.equals(item.getMaterial(), material))
                filteredArticles.add(item);
        }
        return filteredArticles;
    }

    List<Articles> findByMaterial(String material);
    Articles save(Articles article);


    public default void addSpecification(Articles article, Specifications specification) {
        article.addSpecification(specification);
    }

    public default void removeSpecifications(Articles article, Specifications specification) {

        article.removeSpecifications(specification);
    }
}
