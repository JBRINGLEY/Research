package DAO.UnitTests;

import DAO.*;
import Data.DataFactory;
import DataContract.ILemmatizedReview;
import DataContract.IMovie;
import DataContract.IReview;
import DataContract.IReviewer;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.UUID;


public class LemmaDaoTest {
    @Test
    public void addLemmatizedReviewTest() {

        // region Movie Data Setup
        IMovie movie = DataFactory.NewMovie(2000,
                "TESTMovie" + UUID.randomUUID().toString(), true);
        int movieId = 0;
        try {
            movieId = MovieDao.AddMovie(movie);
        } catch (Exception e) {
            Assert.fail("Unable to add movie : " + e.getMessage());
        }

        IMovie movieFromAdd = DataFactory.NewMovie(movieId,
                movie.GetYear(),  movie.GetTitle(),
                movie.IsTest());
        // endregion

        // region Reviewer Data Setup
        IReviewer reviewer = DataFactory.NewReviewer("TestReviewer" + UUID
                .randomUUID().toString(), true);
        int reviewerId = 0;
        try {
            reviewerId = ReviewerDao.AddReviewer(reviewer);
        } catch (Exception e) {
            Assert.fail("Unable to add reviewer : " + e.getMessage());
        }

        IReviewer reviewerFromAdd = DataFactory.NewReviewer(reviewerId,
                reviewer.GetName(), reviewer.IsTest());
        // endregion

        //region Review Data Setup
        IReview review = DataFactory.NewReview(5, movieFromAdd,
                UUID.randomUUID().toString(), reviewerFromAdd,
                LocalDate.now(), true);
        int reviewId = 0;
        try {
            reviewId = ReviewDao.AddReview(review);
        } catch (Exception e) {
            Assert.fail("Unable to add review : " + e.getMessage());
        }
        // endregion

        // Lemmatized review test
        ILemmatizedReview lemmaReview = DataFactory.NewLemmatizedReview
                (reviewId, review.GetReview(), true);

        int initialReviewCount = GetLemmatizedReviewCount();
        int lemmaReviewId = 0;
        try {
            lemmaReviewId = LemmaDao.AddLemmatizedReview(lemmaReview);
        } catch (Exception e) {
            Assert.fail("Unable to add lemmatized review : " + e.getMessage());
        }
        int finalReviewCount = GetLemmatizedReviewCount();
        Assert.assertTrue(lemmaReviewId > 0);
        Assert.assertTrue(initialReviewCount < finalReviewCount);
    }

    public static int GetLemmatizedReviewCount() {
        String countSql = "SELECT COUNT(*) FROM public.lemmareview";

        try {
            Class.forName(ConnectionHelper.DriverLocation);
            Connection connection = DriverManager.getConnection(
                    ConnectionHelper.ConnectionString,
                    ConnectionHelper.Username,
                    ConnectionHelper.Password);

            PreparedStatement insertStatement = connection.prepareStatement
                    (countSql);

            ResultSet insertResult = insertStatement.executeQuery();
            insertResult.next();
            connection.close();
            return insertResult.getInt(1);
        } catch (Exception e) {
            Assert.fail("Unable to return the count of " +
                    "rows in the table");
        }

        return 0;
    }

}