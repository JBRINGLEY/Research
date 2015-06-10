import Contracts.IWebScrapeController;
import Controller.WebScrapeController;
import Workers.ReviewWorker;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

public class Main {

    private static String protocol = "http://";
    private static String hostName = "www.rottentomatoes.com";
    private static String urlPattern = "^http://www.rottentomatoes.com/m/(.*)/reviews/?(.*)type=user(.*)";

    public static void main(String args[]) {
        //Executor used to strip the html of the review data and insert in to db
        ExecutorService reviewExecutor  = Executors.newFixedThreadPool(10);
        IWebScrapeController controller = new WebScrapeController();

        // Scrape the site
        while(controller.UrlsToVisitExist()) {
            String urlForProcessing = controller.GetUrlToVisit();
            controller.AddVisitedUrl(urlForProcessing);
            System.out.println("Processing: " + urlForProcessing);
            Connection connection = Jsoup.connect(urlForProcessing);
            connection.timeout(10000);
            connection.ignoreHttpErrors(true);
            Document htmlData = null;
            try {
                htmlData = connection.get();
                // Scrape the links from the current page and add them to the list
                // of urls to visit
                Elements linkElements = htmlData.select("a[href]");
                controller.AddUrlsToVisit(GetUrlList(linkElements, controller));
                // If the url contains movie reviews scrape them
                if(Pattern.matches(urlPattern, urlForProcessing)) {
                    reviewExecutor.execute(new ReviewWorker(htmlData));
                }
            } catch (Exception e) {
                System.out.println("Unable to process: " + urlForProcessing);
            }
        }

        try {
            reviewExecutor.wait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<String> GetUrlList(Elements linkElements,
                                           IWebScrapeController webController) {
        List<String> urlList = new ArrayList<String>();
        for(Element linkElement : linkElements) {
            String url = linkElement.attr("href");
            if(url.startsWith("/")) {
                url = protocol + hostName + url;
                if(!webController.UrlVisited(url)) {
                    urlList.add(url);
                }
            }
        }
        return  urlList;
    }

}
