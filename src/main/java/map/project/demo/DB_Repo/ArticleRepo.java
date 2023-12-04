package map.project.demo.DB_Repo;


import map.project.demo.Entities.Articles;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ArticleRepo extends CrudRepository<Articles,Long> {

    List<Articles> findByBrand(String brand);

    List<Articles> findByMaterial(String material);


}