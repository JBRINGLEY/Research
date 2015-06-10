package ReviewScraper;
import ReviewScraper.Review;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ReviewScrape {
    public static void main(String[] args) {
        String url = "http://www.rottentomatoes" +
                ".com/m/big_hero_6/reviews/?page=4&type=user";
        try {
            Document page = Jsoup.connect(url).get();
            List<Review> reviews = ScrapeReviews(page);

            for(Review review : reviews){
                System.out.println(review);
            }
        } catch (IOException e) {}

    }

    public static List<Review> ScrapeReviews(Document page) {
        Elements reviewTable = page.select("#reviews > table");
        Elements reviewRows = reviewTable.select("tr");
        List<Review> userReviews = new ArrayList<Review>();
        for(Element reviewRow : reviewRows) {
            Review reviewData = null;
            try {
                reviewData = ProcessReviewElement(reviewRow);
            } catch (ParseException e) {}
            userReviews.add(reviewData);
        }
        return userReviews;
    }

    public static Review ProcessReviewElement(Element reviewElement) throws
            ParseException {
        Elements reviewElements = reviewElement.select("td");
        String username = reviewElements.get(1).text();

        Element data = reviewElements.get(3);

        Elements ratings = data.select("span").get(0).select("span");
        int rating = (ratings.size() - 1) * 2;
        if (data.select("span").get(0).hasText())
            rating += 1;



        String review = data.select("div.user_review").text();
        String reviewDate = data.getElementsByAttributeValueStarting
                ("class", "fr").text();

        return new Review(username, rating, review, reviewDate);
    }
}