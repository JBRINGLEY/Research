package Data;

import DataContract.*;

import java.time.LocalDate;

public class DataFactory {

    public static IMovie NewMovie(int year, String title, boolean isTest) {
        return new Movie(year, title, isTest);
    }

    public static IMovie NewMovie(int id, int year, String title,
                                  boolean isTest) {
        return new Movie(id, year, title, isTest);
    }

    public static IReview NewReview(int rating, IMovie movie, String review,
                                    IReviewer reviewer, LocalDate reviewDate,
                                    boolean isTest) {
        return new Review(rating, movie, review, reviewer, reviewDate, isTest);
    }

    public static IReview NewReview(int id, int rating, IMovie movie,
                                    String review, IReviewer reviewer,
                                    LocalDate reviewDate, boolean isTest) {
        return new Review(id, rating, movie, review, reviewer, reviewDate,
                isTest);
    }

    public static IReviewer NewReviewer(String name, boolean isTest) {
        return new Reviewer(name, isTest);
    }

    public static IReviewer NewReviewer(int id, String name, boolean isTest){
        return new Reviewer(id, name, isTest);
    }

    public static IUrl NewUrl(String url, boolean isTest) {
        return new Url(url, isTest);
    }

    public static IUrl NewUrl(int id, String url, boolean isTest) {
        return new Url(id ,url, isTest);
    }

    public static ILemmatizedReview NewLemmatizedReview(int reviewId,
                                                        String review,
                                                        boolean isTest) {
        return new LemmatizedReview(reviewId, review, isTest);
    }

    public static ILemmatizedReview NewLemmatizedReview (int id,
                                                         int reviewId,
                                                         String review,
                                                         boolean isTest) {
        return new LemmatizedReview(id, reviewId, review, isTest);
    }
}
