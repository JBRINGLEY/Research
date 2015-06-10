package Contracts;

import java.util.List;

public interface IWebScrapeController {

    // region Url to visit methods
    public boolean UrlsToVisitExist();
    public void AddUrlToVisit(String urlToVisit);
    public void AddUrlsToVisit(List<String> urlsToVisit);
    public String GetUrlToVisit();
    // endregion

    // region Visited url methods
    public boolean UrlVisited(String url);
    public void AddVisitedUrl(String url);
    //

}
