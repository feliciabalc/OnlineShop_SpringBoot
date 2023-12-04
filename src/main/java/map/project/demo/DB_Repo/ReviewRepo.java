package map.project.demo.DB_Repo;

import map.project.demo.Entities.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepo extends CrudRepository<Review,Long> {
}
