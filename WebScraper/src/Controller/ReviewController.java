package Controller;

import Contracts.*;
import Contracts.IReviewController;
import DAO.MovieDao;
import DAO.ReviewDao;
import DAO.ReviewerDao;
import DAO.UrlDao;
import Data.DataFactory;
import DataContract.*;

import java.time.LocalDate;

public class ReviewController implements IReviewController {

    public int AddReview(String reviewerName, LocalDate reviewDate,
                         String movieTitle, int yearProduced,
                         String reviewText, int rating, boolean isTest) {

        // Wrap the entire addition of the review in the try/catch
        // if one operation throws an exception, skip the entire addition of
        // the review.
        IReviewerData reviewerDao = new ReviewerDao();
        IMovieData movieDao = new MovieDao();
        IReviewData reviewDao = new ReviewDao();
        try {
            // Create an instance of the Reviewer & add them to the DB
            IReviewer reviewer = DataFactory.NewReviewer(reviewerName, isTest);
            int reviewerId = reviewerDao.AddReviewer(reviewer);
            reviewer = DataFactory.NewReviewer(reviewerId, reviewerName, isTest);
            // Create an instance of the Movie & add it to the DB
            IMovie movie = DataFactory.NewMovie(yearProduced, movieTitle, isTest);
            int movieId = movieDao.AddMovie(movie);
            movie = DataFactory.NewMovie(movieId, yearProduced, movieTitle, isTest);
            // Create in instance of the review & add it to the DB
            IReview review = DataFactory.NewReview(rating, movie, reviewText,
                    reviewer,
                    reviewDate, isTest);
            int reviewId = reviewDao.AddReview(review);
            return reviewId;
        } catch (Exception e) {
            return 0;
        }
    }

    public void AddVisitedUrl(String url, boolean isTest) {
        IUrlData urlDao = new UrlDao();
        try {
            IUrl urlForAdd = DataFactory.NewUrl(url, isTest);
            urlDao.AddUrl(urlForAdd);
        } catch (Exception ignored) {}
    }
}
