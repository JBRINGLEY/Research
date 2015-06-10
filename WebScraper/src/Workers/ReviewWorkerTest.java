package Workers;

import Workers.ReviewWorker;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ReviewWorkerTest {
    public static void main(String args[]) {
        try {
            Connection connection = Jsoup.connect("http://www.rottentomatoes" +
                    ".com/m/mist/reviews/?type=user");
            Document htmlData = connection.get();
            ReviewWorker worker = new ReviewWorker(htmlData);
            worker.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
