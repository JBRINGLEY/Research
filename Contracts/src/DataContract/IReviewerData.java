package DataContract;

import java.util.List;

public interface IReviewerData {
    List<Integer> AddReviewers(List<IReviewer> reviewers) throws Exception;
    void DeleteReviewers(List<IReviewer> reviewers) throws Exception;
    int AddReviewer(IReviewer reviewer) throws Exception;
    boolean ReviewerExists(IReviewer reviewer);
    List<IReviewer> GetReviewers() throws Exception;
    void DeleteReviewer(IReviewer reviewer) throws Exception;
}
