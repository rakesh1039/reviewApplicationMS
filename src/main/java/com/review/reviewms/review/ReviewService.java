package com.review.reviewms.review;

import java.util.List;

public interface ReviewService{
    List<Review> getAllReview(Long companyId);

    boolean createReview(Review review, Long companyId);

    Review getReviewById(Long reviewId);

    boolean updateReview(Long reviewId, Review updateReview);

    boolean deleteReview(Long reviewId);
}
