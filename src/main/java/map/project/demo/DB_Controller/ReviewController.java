package map.project.demo.DB_Controller;

import map.project.demo.Entities.Cart;
import map.project.demo.Entities.Review;
import map.project.demo.Service.CartService;
import map.project.demo.Service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        Review review = reviewService.getReviewById(id);

        if (review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReview() {
        List<Review> review = (List<Review>) reviewService.getAllReviews();
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review updatedReview) {
        Review existingReview = reviewService.getReviewById(id);
        if (existingReview != null) {
            updatedReview.setId(id);
            Review savedReview = reviewService.saveReview(updatedReview);
            return new ResponseEntity<>(savedReview, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
