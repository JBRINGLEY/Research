package Processors;

import DataContract.IReview;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.List;

public interface IProcessor {
    void SetReview(IReview review);
    void SetPipeline(StanfordCoreNLP pipeline);
    List<String> Process();
}
