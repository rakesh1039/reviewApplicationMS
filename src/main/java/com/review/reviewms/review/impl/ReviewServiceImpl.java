package com.review.reviewms.review.impl;

import com.review.reviewms.review.Review;
import com.review.reviewms.review.ReviewRepository;
import com.review.reviewms.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReview(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean createReview(Review review, Long companyId) {
        if(companyId != null && review != null) {
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return  true;
        }
        return false;
    }

    @Override
    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview(Long reviewId, Review updateReview) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review != null) {
            review.setTitle(updateReview.getTitle());
            review.setDescription(updateReview.getDescription());
            review.setRating(updateReview.getRating());
            review.setCompanyId(updateReview.getCompanyId());
            reviewRepository.save(updateReview);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        try {
            Review review = reviewRepository.findById(reviewId).orElse(null);
            if(review != null) {
                reviewRepository.delete(review);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
