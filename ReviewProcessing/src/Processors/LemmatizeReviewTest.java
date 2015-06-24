package Processors;

import Data.DataFactory;
import DataContract.IMovie;
import DataContract.IReview;
import DataContract.IReviewer;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import static org.junit.Assert.assertTrue;

public class LemmatizeReviewTest {

    @Test
    public void testLemmatize() throws Exception {
        Properties props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos, lemma");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        String reviewText = "I would like to go to the movies";

        List<String> expectedLemma = new ArrayList<String>() {{
            add("I");
            add("would");
            add("like");
            add("to");
            add("go");
            add("to");
            add("the");
            add("movie");
        }};

        IReview review = GetNewReview(reviewText);
        LemmatizeReview lemmaProcess = new LemmatizeReview(review, pipeline);
        AssertLemmas(expectedLemma, lemmaProcess.Process());


        reviewText = "There are lots of cows in the roads around Wisconsin";
        expectedLemma = new ArrayList<String>() {{
            add("there");
            add("be");
            add("lot");
            add("of");
            add("cow");
            add("in");
            add("the");
            add("road");
            add("around");
            add("Wisconsin");
        }};
        review = GetNewReview(reviewText);
        lemmaProcess.SetReview(review);
        AssertLemmas(expectedLemma, lemmaProcess.Process());

    }

    private IReview GetNewReview(String text) {

        IMovie movie = DataFactory.NewMovie(2000, UUID.randomUUID().toString(),
                true);
        IReviewer reviewer = DataFactory.NewReviewer(UUID.randomUUID().toString(),
                true);

        return DataFactory.NewReview(10, movie, text, reviewer,
                LocalDate.now(), true);
    }

    private void AssertLemmas(List<String> expectedLemmas,
                              List<String> actualLemmas) {
        for (int i = 0; i < expectedLemmas.size(); i++) {
            assertTrue("Lemmas did not match: " +
                            "Expected: " + expectedLemmas.get(i) +
                            "Actual: " + actualLemmas.get(i),
                    expectedLemmas.get(i).equals(actualLemmas.get(i)));
        }
    }
}