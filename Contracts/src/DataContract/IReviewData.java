package DataContract;

import java.util.List;

public interface IReviewData {
    int AddReview(IReview review) throws Exception;
    List<Integer> AddReviews(List<IReview> reviews) throws Exception;
    void DeleteReview(IReview review) throws Exception;
    List<Integer> GetReviewIds() throws Exception;
}