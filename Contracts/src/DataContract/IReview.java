package DataContract;

import java.time.LocalDate;

public interface IReview {
    int GetId();
    int GetRating();
    IMovie GetMovie();
    String GetReview();
    IReviewer GetReviewer();
    LocalDate GetReviewDate();
    boolean IsTest();
}
