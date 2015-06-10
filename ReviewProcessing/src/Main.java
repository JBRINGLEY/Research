import DAO.ReviewDao;
import Workers.LemmaWorker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

  public static void main(String[] args) {

    ExecutorService lemmaExecutor = Executors.newFixedThreadPool(20);
    List<Integer> reviewIds = null;
    try {
      reviewIds = ReviewDao.GetReviewIDs();
    } catch (Exception e) {
    }
    List<List<Integer>> reviewIdSubsets = SplitReviews(reviewIds, 20);

    for(List<Integer> reviewIdSubset : reviewIdSubsets) {
      lemmaExecutor.execute(new LemmaWorker(reviewIdSubset));
    }

    try {
      lemmaExecutor.wait();
    } catch (Exception e) {}
  }

  private static List<List<Integer>> SplitReviews(List<Integer> reviews, int
          length) {
    List<List<Integer>> returnList = new ArrayList<>();
    List<Integer> subset = new ArrayList<>();
    for (int i = 0; i < reviews.size(); i++) {
      if (i != 0 && i % length == 0) {
        returnList.add(subset);
        subset = new ArrayList<>();
      }
      subset.add(reviews.get(i));
    }
    if (subset.size() > 0) {
      returnList.add(subset);
    }
    return returnList;
  }
}
