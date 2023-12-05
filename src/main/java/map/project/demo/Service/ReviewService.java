package map.project.demo.Service;

import map.project.demo.DB_Repo.ReviewRepo;
import map.project.demo.Entities.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewService {

    private final ReviewRepo reviewRepo;

    @Autowired
    public ReviewService(ReviewRepo reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    public List<Review> getAllReviews() {
        return (List<Review>) reviewRepo.findAll();
    }

    public Review getReviewById(Long id) {
        return reviewRepo.findById(id).orElse(null);
    }

    public Review saveReview(Review review) {
        return reviewRepo.save(review);
    }

    public void deleteReview(Long id) {
        reviewRepo.deleteById(id);
    }
}
