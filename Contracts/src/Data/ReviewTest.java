package Data;

import DataContract.IMovie;
import org.junit.Test;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.Assert.*;

public class ReviewTest {

    @Test
    public void testGetId() throws Exception {
        Review review = new Review(0, null, "", null, LocalDate.now(), true);
        assertTrue(review.GetId() == 0);
        review = new Review(0, null, "", null, LocalDate.now(), false);
        assertTrue(review.GetId() == 0);

        int id = 1;
        review = new Review(id, 0, null, "", null, LocalDate.now(), true);
        assertTrue(review.GetId() == id);
        review = new Review(id, 0, null, "", null, LocalDate.now(), false);
        assertTrue(review.GetId() == id);
    }

    @Test
    public void testGetRating() throws Exception {
        int rating = 2;
        Review review = new Review(rating, null, "", null, LocalDate.now(),
                true);
        assertTrue(review.GetRating() == rating);
        review = new Review(rating, null, "", null, LocalDate.now(), false);
        assertTrue(review.GetRating() == rating);

        review = new Review(1, rating, null, "", null, LocalDate.now(), true);
        assertTrue(review.GetRating() == rating);
        review = new Review(1, rating, null, "", null, LocalDate.now(), false);
        assertTrue(review.GetRating() == rating);
    }

    @Test
    public void testGetMovie() throws Exception {

        Movie movieTrue = new Movie(1, "TEST", true);
        Movie movieFalse = new Movie(1, "TEST", false);

        Review review = new Review(1, movieTrue, "", null, LocalDate.now(),
                true);
        AssertMoviesAreEqual(movieTrue, review.GetMovie());
        review = new Review(1, movieTrue, "", null, LocalDate.now(), false);
        AssertMoviesAreEqual(movieTrue, review.GetMovie());

        review = new Review(1, 1, movieTrue, "", null, LocalDate.now(), false);
        AssertMoviesAreEqual(movieTrue, review.GetMovie());
        review = new Review(1, 1, movieTrue, "", null, LocalDate.now(), false);
        AssertMoviesAreEqual(movieTrue, review.GetMovie());

        review = new Review(1, movieFalse, "", null, LocalDate.now(),
                true);
        AssertMoviesAreEqual(movieFalse, review.GetMovie());
        review = new Review(1, movieFalse, "", null, LocalDate.now(), false);
        AssertMoviesAreEqual(movieFalse, review.GetMovie());

        review = new Review(1, 1, movieFalse, "", null, LocalDate.now(), false);
        AssertMoviesAreEqual(movieTrue, review.GetMovie());
        review = new Review(1, 1, movieFalse, "", null, LocalDate.now(), false);
        AssertMoviesAreEqual(movieFalse, review.GetMovie());
    }

    private void AssertMoviesAreEqual(IMovie expectedMovie,
                                      IMovie actualMovie) {
        assertTrue("Movie ids do not match",
                expectedMovie.GetId() == actualMovie.GetId());
        assertTrue("Movie titles do not match",
                expectedMovie.GetTitle().equals(actualMovie.GetTitle()));
        assertTrue("Movie isTest do not match",
                expectedMovie.IsTest() == actualMovie.IsTest());
    }

    @Test
    public void testGetReview() throws Exception {
        String reviewText = UUID.randomUUID().toString();
        Review review = new Review(10, null,reviewText, null,
                LocalDate.now(), true );
        assertTrue(reviewText.equals(review.GetReview()));
        review = new Review(10, null,reviewText, null,
                LocalDate.now(), false);
        assertTrue(reviewText.equals(review.GetReview()));

        review = new Review(1, 10, null,reviewText, null,
                LocalDate.now(), true );
        assertTrue(reviewText.equals(review.GetReview()));
        review = new Review(1, 10, null,reviewText, null,
                LocalDate.now(), true );
        assertTrue(reviewText.equals(review.GetReview()));
    }

    @Test
    public void testGetReviewDate() throws Exception {
        LocalDate now = LocalDate.now();
        Review review = new Review(10, null, "" , null, now, true);
        assertTrue(now.isEqual(review.GetReviewDate()));

        review = new Review(10, null, "" , null, now, false);
        assertTrue(now.isEqual(review.GetReviewDate()));

        review = new Review(1, 10, null, "" , null, now, false);
        assertTrue(now.isEqual(review.GetReviewDate()));

        review = new Review(1, 10, null, "" , null, now, false);
        assertTrue(now.isEqual(review.GetReviewDate()));
    }

    @Test
    public void testIsTest() throws Exception {
        Review review = new Review(10, null, "", null, LocalDate.now(), true);
        assertTrue(review.IsTest());

        review = new Review(10, null, "", null, LocalDate.now(), false);
        assertFalse(review.IsTest());

        review = new Review(1, 10, null, "", null, LocalDate.now(), true);
        assertTrue(review.IsTest());

        review = new Review(1, 10, null, "", null, LocalDate.now(), false);
        assertFalse(review.IsTest());
    }
}