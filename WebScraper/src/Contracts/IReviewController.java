package Contracts;

import java.time.LocalDate;

public interface IReviewController{
    public int AddReview(String reviewerName, LocalDate reviewDate,
                         String movieTitle, int yearProduced,
                         String reviewText, int rating, boolean isTest);

    public void AddVisitedUrl(String url, boolean isTest);
}
