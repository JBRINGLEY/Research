package Data;

import DataContract.IMovie;
import DataContract.IReview;
import DataContract.IReviewer;
import Validation.ValueValidation;

import java.time.LocalDate;

class Review implements IReview {

    //region Private fields
    private int _id;
    private int _rating;
    private IMovie _movie;
    private String _review;
    private IReviewer _reviewer;
    private LocalDate _reviewDate;
    private boolean _isTest;
    // endregion

    // region Constructors
    public Review(int rating, IMovie movie, String review,
                  IReviewer reviewer, LocalDate reviewDate, boolean isTest) {
        this(0, rating, movie, review, reviewer, reviewDate, isTest);
    }

    public Review(int id, int rating, IMovie movie, String review,
                  IReviewer reviewer, LocalDate reviewDate, boolean isTest) {
        ValueValidation.Id(id);
        _id = id;
        _rating = rating;
        _movie = movie;
        _review = review;
        _reviewer = reviewer;
        _reviewDate = reviewDate;
        _isTest = isTest;
    }
    // endregion

    // region Public methods
    public int GetId() {
        return _id;
    }

    public int GetRating() {
        return _rating;
    }

    public IMovie GetMovie() {
        return _movie;
    }

    public String GetReview() {
        return _review;
    }

    public IReviewer GetReviewer() {
        return _reviewer;
    }

    public LocalDate GetReviewDate() {
        return _reviewDate;
    }

    public boolean IsTest() {
        return _isTest;
    }
    //endregion
}
