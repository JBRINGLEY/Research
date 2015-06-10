package Workers;

import Contracts.IReviewController;
import Controller.ReviewController;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDate;

public class ReviewWorker implements Runnable {

    private Document _pageData;

    public ReviewWorker(Document pageData) {
        if(pageData == null) {
            throw new IllegalArgumentException();
        }
        _pageData = pageData;
    }

    public void run() {
        String movieTitle = _pageData.select("#head > div.container > div.col.col-left > div.panel.panel-rt.panel-box > div > div.center > h2 > a").text();
        String movieYear = _pageData.select("#head > div.container > div.col" +
                ".col-left > div.panel.panel-rt.panel-box > div > " +
                "div:nth-child(2) > ul > li:nth-child(4)").text().split(" ")[4];
        IReviewController controller = new ReviewController();
        Elements reviewRows = _pageData.select("#reviews > table").select("tr");
        for (Element reviewRow : reviewRows) {
            Elements reviewColumns = reviewRow.select("td");
            String reviewerName = reviewColumns.get(1).text();
            LocalDate reviewDate = ParseDate(reviewColumns.get(3).select
                    ("span.fr.small.subtle").text());
            String review = reviewColumns.get(3).select("div").text();
            Elements ratings = reviewColumns.get(3).select("span").get(0).select
                    ("span");
            int movieRating = (ratings.size()) * 2;
            if (reviewColumns.get(3).select("span").get(0).hasText())
                movieRating += 1;
            controller.AddReview(reviewerName, reviewDate, movieTitle,
                    FormatMovieYear(movieYear),
                    review, movieRating, false);
        }
    }


    private int FormatMovieYear(String yearText) {
         return Integer.parseInt(yearText);
    }

    private LocalDate ParseDate(String dateText) {
        String[] splitDate = dateText.split(" ");
        String monthText = splitDate[0];
        int month = GetMonthFromText(monthText);
        String dayText = splitDate[1].replace(",", "");
        int day = Integer.parseInt(dayText);
        int year = Integer.parseInt(splitDate[2]);

        return LocalDate.of(year, month, day);
    }

    private int GetMonthFromText(String month) {
        if (month == null) {
            throw new IllegalArgumentException();
        }
//        switch (month) {
//            case "January":
//                return 1;
//            case "February":
//                return 2;
//            case "March":
//                return 3;
//            case "April":
//                return 4;
//            case "May":
//                return 5;
//            case "June":
//                return 6;
//            case "July":
//                return 7;
//            case "August":
//                return 8;
//            case "September":
//                return 9;
//            case "October":
//                return 10;
//            case "November":
//                return 11;
//            case "December":
//                return 12;
//            default:
//                throw new IllegalArgumentException();
//        }
        return 1;
    }
}

