package Controller;

import Contracts.IWebScrapeController;
import DAO.*;
import Data.DataFactory;
import DataContract.IUrl;

import java.util.List;

public class WebScrapeController implements IWebScrapeController {

    public boolean UrlsToVisitExist() {
        int urlCount = UrlDao.UrlsToVisitCount();
        if (urlCount > 0) {
            return true;
        }
        return false;
    }

    public void AddUrlToVisit(String urlToVisit) {
        try {
            IUrl urlData = DataFactory.NewUrl(urlToVisit, false);
            if(!UrlDao.UrlVisited(urlData)) {
                UrlDao.AddUrlToVisit(urlData);
            }
        } catch (Exception e) {
            System.out.println("Unable to add: " + urlToVisit);
        }
    }

    public void AddUrlsToVisit(List<String> urlsToVisit) {
        for (String urlToVist : urlsToVisit) {
            AddUrlToVisit(urlToVist);
        }
    }

    public String GetUrlToVisit() {
        return UrlDao.GetUrlToVisit().GetUrl();
    }

    public boolean UrlVisited(String url) {
        return UrlDao.UrlVisited(DataFactory.NewUrl(url, false));
    }

    public void AddVisitedUrl(String url) {
        try {
            UrlDao.AddUrl(DataFactory.NewUrl(url, false));
        } catch (Exception e) {
            System.out.println("Unable to add to visited list: " + url);
        }
    }
}
