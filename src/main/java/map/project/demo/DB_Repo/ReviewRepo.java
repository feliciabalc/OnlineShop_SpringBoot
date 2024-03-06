package map.project.demo.DB_Repo;

import map.project.demo.Entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public interface ReviewRepo extends JpaRepository<Review,Long> {
    default List<Review> filteredByStars(String stars) {
        List<Review> review = findAll();
        List<Review> filteredReview = new ArrayList<>();
        for (Review item : review) {
            if (Objects.equals(item.getStars(), stars))
                filteredReview.add(item);
        }
        return filteredReview;

    }
    default List<Review> filteredByDate(String date) {
        List<Review> review = findAll();
        List<Review> filteredReview = new ArrayList<>();
        for (Review item : review) {
            if (Objects.equals(item.getDate(), date))
                filteredReview.add(item);
        }
        return filteredReview;
    }

}
