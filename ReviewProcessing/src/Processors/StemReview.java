package Processors;

import DataContract.IReview;
import java.util.ArrayList;
import java.util.List;

public class StemReview {

  // region Private fields
  private IReview _review;
  // endregion

  // region Constructor
  public StemReview(IReview review) {
    this.SetReview(review);
  }
  // end region

  // region Public methods
  public void SetReview(IReview review) {
    ValidateNotNull(review);
    _review = review;
  }


  public List<String> Stem() {
    return new ArrayList<String>();
  }
  // endregion

  // region Private methods
  private void ValidateNotNull(IReview review) {
    if (review == null) {
      throw new IllegalArgumentException("Review passed is null");
    }
  }
  // endregion
}
