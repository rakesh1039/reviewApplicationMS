package com.review.reviewms.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReview(@RequestParam Long companyId) {
        return new ResponseEntity<>(reviewService.getAllReview(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createReview(@RequestParam Long companyId, @RequestBody Review review) {
        boolean isReview = reviewService.createReview(review, companyId);
        if(isReview) {
            return new ResponseEntity<>("Review is added successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review is not added", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId) {
        Review isReview = reviewService.getReviewById(reviewId);
        if(isReview != null) {
            return new ResponseEntity<>(isReview, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId, @RequestBody Review updateReview) {
        boolean isUpdated = reviewService.updateReview(reviewId, updateReview);
        if(isUpdated) {
            return new ResponseEntity<>("Review is updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review is not updated", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
        boolean isDeleted = reviewService.deleteReview(reviewId);
        if(isDeleted) {
            return new ResponseEntity<>("Review is deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review is not deleted", HttpStatus.NOT_FOUND);
    }
}
