package Processors;

import DataContract.IReview;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.ArrayList;
import java.util.List;

public class StemReview implements IProcessor{

    // region Private fields
    private IReview _review;
    private StanfordCoreNLP _pipeline;
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

    public void SetPipeline(StanfordCoreNLP pipeline) {
        ValidateNotNull(pipeline);
        _pipeline = pipeline;
    }

    public List<String> Process() {
        return new ArrayList<String>();
    }
    // endregion

    // region Private methods
    private void ValidateNotNull(IReview review) {
        if (review == null) {
            throw new IllegalArgumentException("Review passed is null");
        }
    }

    private void ValidateNotNull(StanfordCoreNLP pipeline) {
        if (pipeline == null) {
            throw new IllegalArgumentException("Pipline passed is null");
        }
    }
    // endregion
}
