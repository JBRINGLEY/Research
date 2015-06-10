package WebPageScrape;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.IOException;

public class ScrapePage {
  public static void main(String[] args) throws IOException {
    String protocol = "http://";
    String rootUrl = "www.rottentomatoes.com";
    String absoluteRootUrl = protocol + rootUrl;

//    List<String> processedUrls = processWebPage(absoluteRootUrl, 3);
//    for(String processedUrl : processedUrls) {
//      System.out.println(processedUrl);
//    }
  }

  private static void processWebPage(String rootUrl, int depth){
    if (depth != 0) {
      Elements scrapedLinks = scrapeLinks(rootUrl);
      depth -= 1;
      for(Element scrapedLink : scrapedLinks) {

        processWebPage(scrapedLink.attr("href"), depth);
      }
    }

  }

  private static Elements scrapeLinks(String absoluteUrl) {
    Elements elementsForReturn = null;
    try {
      Document pageDocument = Jsoup.connect(absoluteUrl).get();
      elementsForReturn = pageDocument.select("a[href]");
    } 
    catch (Exception e) {
      System.out.println("Unable to process " + absoluteUrl + " received " +
              "error message : " + e.getMessage());
    }
    return elementsForReturn;
  }

  private static List<String> convertAndFormatElementsToUrlStringList
          (Elements linkElements, String rootUrl) {
    List<String> returnList = new ArrayList<String>();
    for (Element linkElement : linkElements) {
      try {
        returnList.add(formatUrl(linkElement.attr("href").toString(), rootUrl));
      } catch (Exception e) {
      }
    }
    return returnList;
  }

  private static String formatUrl(String rawUrl, String rootUrl) throws
          Exception {
    if (rawUrl.startsWith(rootUrl))
      return rawUrl;
    else if (rawUrl.startsWith("/") && rawUrl.length() > 1)
      return rootUrl + rawUrl;
    throw new Exception("Raw Url passed does not meet the required criteria");
  }

}
