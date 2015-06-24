package Controller;

import Contracts.IWebScrapeController;
import DAO.*;
import Data.DataFactory;
import DataContract.IUrl;
import DataContract.IUrlData;

import java.util.List;

public class WebScrapeController implements IWebScrapeController {

    public boolean UrlsToVisitExist() {
        IUrlData urlDao= new UrlDao();
        int urlCount = urlDao.UrlsToVisitCount();
        if (urlCount > 0) {
            return true;
        }
        return false;
    }

    public void AddUrlToVisit(String urlToVisit) {
        IUrlData urlDao= new UrlDao();

        try {
            IUrl urlData = DataFactory.NewUrl(urlToVisit, false);
            if(!urlDao.UrlVisited(urlData)) {
                urlDao.AddUrlToVisit(urlData);
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
        IUrlData urlDao= new UrlDao();
        return urlDao.GetUrlToVisit().GetUrl();
    }

    public boolean UrlVisited(String url) {
        IUrlData urlDao= new UrlDao();
        return urlDao.UrlVisited(DataFactory.NewUrl(url, false));
    }

    public void AddVisitedUrl(String url) {
        IUrlData urlDao= new UrlDao();
        try {
            urlDao.AddUrl(DataFactory.NewUrl(url, false));
        } catch (Exception e) {
            System.out.println("Unable to add to visited list: " + url);
        }
    }
}
