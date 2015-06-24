package DAO.UnitTests;

import DAO.UrlDao;
import Data.DataFactory;
import DataContract.IUrl;
import DataContract.IUrlData;
import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UrlDaoTest {

    @Test
    public void testAddUrl() {
        IUrl urlForAdd = DataFactory.NewUrl("http://test" + new Random().nextInt()
                + ".com", true);
        IUrlData urlDao= new UrlDao();

        try {
            int id = urlDao.AddUrl(urlForAdd);
            Assert.assertTrue(id > 0);
        } catch (Exception ignored) {
            Assert.fail();
        }
    }

    @Test
    public void testAddUrls() {
        List<IUrl> urlsForAdd = new ArrayList<IUrl>();
        IUrlData urlDao= new UrlDao();

        for(int i = 0; i <= 20; i++) {
            urlsForAdd.add(DataFactory.NewUrl(("http://test" + new Random().nextInt()
                    +".com"), true));
        }
        List<Integer> idsFromAdd = null;
        try {
            idsFromAdd = urlDao.AddUrls(urlsForAdd);
        } catch (Exception e) {
            Assert.fail();
        }

        for(Integer idFromAdd : idsFromAdd) {
            Assert.assertTrue(idFromAdd > 0);
        }
    }

    @Test
    public void testUrlVisited() {
        IUrl urlForAdd = DataFactory.NewUrl("http://www.rottentomatoes.com"
                , false);
        IUrlData urlDao= new UrlDao();

        try {
           urlDao.UrlVisited(urlForAdd);
        } catch (Exception ignored) {
            Assert.fail();
        }
    }

    @Test
    public void testDeleteUrl() {
        testAddUrl();
        IUrlData urlDao= new UrlDao();

        try {
            List<IUrl> urlList = urlDao.GetUrls();
            for(IUrl url : urlList) {
                if(url.GetUrl().toLowerCase().contains("test")) {
                    urlDao.DeleteUrl(url);
                    break;
                }
            }
            List<IUrl> urlListPostDelete = urlDao.GetUrls();
            Assert.assertTrue(urlList.size() - 1 == urlListPostDelete.size());
        } catch (Exception ignored) {
            Assert.fail();
        }
    }

    @Test
    public void testDeleteUrls() {
        IUrlData urlDao= new UrlDao();
        try {
            List<IUrl> urlList = urlDao.GetUrls();
            List<IUrl> urlsForDelete = new ArrayList<IUrl>();
            for(IUrl url : urlList) {
                if(url.GetUrl().toLowerCase().contains("test")) {
                    urlsForDelete.add(url);
                }
            }
            urlDao.DeleteUrls(urlsForDelete);
            List<IUrl> urlListPostDelete = urlDao.GetUrls();
            Assert.assertTrue(urlList.size() - urlsForDelete.size() ==
                    urlListPostDelete.size());
        } catch (Exception ignored) {
            Assert.fail();
        }
    }
}
