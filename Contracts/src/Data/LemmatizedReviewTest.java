package Data;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class LemmatizedReviewTest {

    @Test
    public void testGetId() throws Exception {
        LemmatizedReview review = new LemmatizedReview(12, "", true);
        assertTrue(review.GetId() == 0);
        review = new LemmatizedReview(12, "", false);
        assertTrue(review.GetId() == 0);

        int id = 10;
        review = new LemmatizedReview(id, 12, "", true);
        assertTrue(review.GetId() == id);
        review = new LemmatizedReview(id, 12, "", true);
        assertTrue(review.GetId() == id);
    }

    @Test
    public void testGetReviewId() throws Exception {
        int reviewId = 10;
        LemmatizedReview review = new LemmatizedReview(reviewId, "", true);
        assertTrue(review.GetReviewId() == reviewId);
        review = new LemmatizedReview(reviewId, "", false);
        assertTrue(review.GetReviewId() == reviewId);

        review = new LemmatizedReview(1, reviewId, "", true);
        assertTrue(review.GetReviewId() == reviewId);
        review = new LemmatizedReview(1, reviewId, "", true);
        assertTrue(review.GetReviewId() == reviewId);
    }

    @Test
    public void testGetReview() throws Exception {
        String reviewText = UUID.randomUUID().toString();
        LemmatizedReview review = new LemmatizedReview(10, reviewText, true);
        assertTrue(review.GetReview().equals(reviewText));
        review = new LemmatizedReview(12, reviewText, false);
        assertTrue(review.GetReview().equals(reviewText));

        review = new LemmatizedReview(1, 12, reviewText, true);
        assertTrue(review.GetReview().equals(reviewText));
        review = new LemmatizedReview(1, 12, reviewText, true);
        assertTrue(review.GetReview().equals(reviewText));
    }

}