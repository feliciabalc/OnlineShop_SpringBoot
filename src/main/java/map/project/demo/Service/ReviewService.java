package map.project.demo.Service;

import map.project.demo.DB_Repo.ReviewRepo;
import map.project.demo.Entities.*;
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

    public Client getClientForReview(Long reviewId) throws Exception {
        Review review = reviewRepo.findById(reviewId)
                .orElseThrow(() -> new Exception("Review not found with id " + reviewId));

        return review.getClient();
    }

    public Articles getArticleForReview(Long reviewId) throws Exception {
        Review review = reviewRepo.findById(reviewId)
                .orElseThrow(() -> new Exception("Review not found with id " + reviewId));

        return review.getArticle();
    }

    public List<Review> filteredByStars(String stars){
        return reviewRepo.filteredByStars(stars);
    }

    public List<Review> filteredByDate(String date){
        return reviewRepo.filteredByDate(date);
    }

    public void addClientToReview(Long reviewId, Client client) throws Exception {
        Review review= reviewRepo.findById(reviewId)
                .orElseThrow(() -> new Exception("Review not found with id " + reviewId));

        review.setClient(client);
        reviewRepo.save(review);
    }

    public void addArticleToReview(Long reviewId, Articles article) throws Exception {
        Review review= reviewRepo.findById(reviewId)
                .orElseThrow(() -> new Exception("Review not found with id " + reviewId));

        review.setArticle(article);
        reviewRepo.save(review);
    }
}
