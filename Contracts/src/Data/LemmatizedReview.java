package Data;

import DataContract.ILemmatizedReview;
import Validation.ValueValidation;

public class LemmatizedReview implements ILemmatizedReview {

    // region private fields
    private int _id;
    private int _reviewId;
    private String _review;
    private boolean _isTest;
    // endregion

    // region constructors
    public LemmatizedReview(int reviewId, String review, boolean isTest) {
        this(0, reviewId, review, isTest);
    }

    public LemmatizedReview(int id, int reviewId, String review,
                            boolean isTest) {
        ValueValidation.Id(id);
        ValueValidation.Id(reviewId);
        _id = id;
        _reviewId = reviewId;
        _review = review;
        _isTest = isTest;
    }
    // endregion

    // region public methods
    public int GetId() {
        return _id;
    }

    public int GetReviewId() {
        return _reviewId;
    }

    public String GetReview() {
        return _review;
    }

    public boolean IsTest() { return _isTest;}
    // endregion
}
