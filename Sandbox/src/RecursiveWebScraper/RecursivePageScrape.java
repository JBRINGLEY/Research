import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecursivePageScrape {

  private static List<String> processedUrls = new ArrayList<String>();

  public static void main(String[] args) throws IOException {
    String protocol = "http://";
    String hostName = "www.rottentomatoes.com";
    String domain = "rottentomatoes.com";
    String rootUrl = protocol + hostName;

    int searchDepth = 4;
    long startTime = System.currentTimeMillis();
    processPage(rootUrl, rootUrl, searchDepth);
    long endTime = System.currentTimeMillis();
    printProcessedUrls();
    System.out.println("Process took: " + (endTime - startTime)/1000 +
            "seconds");

  }

  private static void processPage(String rootUrl,
                                  String urlToProcess,
                                  int searchDepth)
          throws IOException {
    // Add the url to be processed to the list of processed urls
    processedUrls.add(urlToProcess);
    // Verify that the process is to continue
    if (searchDepth != 0) {
      // Scrape the links at the urlToProcess
      List<String> scrapedUrls = scrapeAndFormatUrls(rootUrl, urlToProcess);
      // Iterate through each returned url, recursively calling process page
      for (String scrapedUrl : scrapedUrls) {
        if (!processedUrls.contains(scrapedUrl)) {
          System.out.println("Processing : " + scrapedUrl);
          processPage(rootUrl, scrapedUrl, searchDepth - 1);
        }
      }
    }
  }

  private static List<String> scrapeAndFormatUrls(String rootUrl,
                                                  String url)
                                                  throws IOException {
    Connection conn = Jsoup.connect(url).timeout(30000);
    conn.ignoreHttpErrors(true);
    Document page = conn.get();
    Elements scrapedUrls = page.select("a[href]");
    List<String> returnUrls = new ArrayList<String>();
    for (Element scrapedUrl : scrapedUrls) {
      String rawUrl = scrapedUrl.attr("href").toString();
      if (rawUrl.startsWith(rootUrl))
        returnUrls.add(rawUrl);
      else if (rawUrl.startsWith("/") && rawUrl.length() > 1)
        returnUrls.add(rootUrl + rawUrl);
    }
    return returnUrls;
  }

  private static void printProcessedUrls() {
    for (String processedUrl : processedUrls) {
      System.out.println(processedUrl);
    }
    System.out.println(processedUrls.size());
  }
}
