package Processors;

import Data.DataFactory;
import DataContract.IMovie;
import DataContract.IReview;
import DataContract.IReviewer;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.junit.Test;

import java.net.ProxySelector;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import static org.junit.Assert.*;

public class StemReviewTest {

  @Test
  public void testStem() throws Exception {

  }

  private IReview GetNewReview(String text){

    IMovie movie = DataFactory.NewMovie(2000, UUID.randomUUID().toString(),
            true);
    IReviewer reviewer = DataFactory.NewReviewer(UUID.randomUUID().toString(),
            true);

    return DataFactory.NewReview(10, movie, text, reviewer,
            LocalDate.now(), true);
  }

  private void AssertStems(List<String> expectedStems,
                           List<String> actualStems) {
    for(int i = 0; i < expectedStems.size(); i++) {
      assertTrue("Stems did not match: " +
                  "Expected: " + expectedStems.get(i) +
                  "Acutal: " + actualStems.get(i),
                  expectedStems.get(i).equals(actualStems.get(i)));
    }
  }
}