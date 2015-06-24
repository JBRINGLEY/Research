package Processors;

import DataContract.IReview;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.ArrayList;
import java.util.List;

public class LemmatizeReview implements IProcessor {
    // region Private fields
    private IReview _review;
    private StanfordCoreNLP _pipeline;
    // endregion


    // region constructor
    public LemmatizeReview(IReview review, StanfordCoreNLP pipeline) {
        ValidateNotNull(review);
        ValidateNotNull(pipeline);

        _review = review;
        _pipeline = pipeline;
    }
    // endregion

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
        List<String> lemmas = new ArrayList<String>();
        String reviewText = _review.GetReview();
        if (reviewText.length() > 0) {
            Annotation document = new Annotation(reviewText);
            _pipeline.annotate(document);
            List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
            for (CoreMap sentence : sentences) {
                List<CoreLabel> tokens = sentence.get(CoreAnnotations.TokensAnnotation
                        .class);
                for (CoreLabel token : tokens) {
                    lemmas.add(token.get(CoreAnnotations.LemmaAnnotation.class));
                }
            }
            return lemmas;
        }
        return null;
    }
    // endregion

    // region private fields
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
