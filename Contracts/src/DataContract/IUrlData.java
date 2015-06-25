package DataContract;

import java.util.List;

public interface IUrlData {
    List<Integer> AddUrls(List<IUrl> urls) throws Exception;
    void DeleteUrls(List<IUrl> urls) throws Exception;
    int AddUrl(IUrl url) throws Exception;
    void DeleteUrl(IUrl url) throws Exception;
    List<IUrl> GetUrls() throws Exception;
    boolean UrlVisited(IUrl url);

    int UrlsToVisitCount();
    void AddUrlToVisit(IUrl url);
    IUrl GetUrlToVisit();

}
