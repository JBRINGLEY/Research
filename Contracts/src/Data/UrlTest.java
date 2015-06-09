package Data;

import org.junit.Test;

import static org.junit.Assert.*;

public class UrlTest {

    @Test
    public void testConstructors() throws Exception {
        // region Valid
        new Url("http://teste", true);
        new Url("https://adsf", true);
        new Url("http://teste", false);
        new Url("https://adsf", false);

        new Url(0, "http://teste", true);
        new Url(0, "https://adsf", true);
        new Url(0, "http://teste", false);
        new Url(0, "https://adsf", false);
        // endregion

        // region Invalid
        try {
            new Url("adf", true);
            fail();
        } catch (Exception e) {
        }
        try {
            new Url("adfasf", false);
            fail();
        } catch (Exception e) {
        }
        try {
            new Url(-1, "http://asdf", true);
            fail();
        } catch (Exception e) {
        }
        try {
            new Url(-1, "https://adfadsf", false);
            fail();
        } catch (Exception e) {
        }
        try {
            new Url(0, "httasdf", true);
            fail();
        } catch (Exception e) {
        }
        try {
            new Url(0, "h//adfadsf", false);
            fail();
        } catch (Exception e) {
        }
        // endregion
    }

    @Test
    public void testGetId() throws Exception {
        Url testUrl = new Url("http://asdf", false);
        assertTrue(testUrl.GetId() == 0);

        testUrl = new Url("http://test", true);
        assertTrue(testUrl.GetId() == 0);

        int id = 2;
        testUrl = new Url(id, "http://adsf", true);
        assertTrue(testUrl.GetId() == id);

        testUrl = new Url(id, "http://adsf", false);
        assertTrue(testUrl.GetId() == id);
    }

    @Test
    public void testGetUrl() throws Exception {
        String url = "http://test";
        Url testUrl = new Url(url, true);
        assertTrue(url.equals(testUrl.GetUrl()));

        testUrl = new Url(url, false);
        assertTrue(url.equals(testUrl.GetUrl()));

        testUrl = new Url(1, url, true);
        assertTrue(url.equals(testUrl.GetUrl()));

        testUrl = new Url(1, url, false);
        assertTrue(url.equals(testUrl.GetUrl()));
    }

    @Test
    public void testIsTest() throws Exception {
        Url testUrl = new Url("http://afda", true);
        assertTrue(testUrl.IsTest());

        testUrl = new Url("http://afda", false);
        assertFalse(testUrl.IsTest());

        testUrl = new Url(1, "http://afda", true);
        assertTrue(testUrl.IsTest());

        testUrl = new Url(1, "http://afda", false);
        assertFalse(testUrl.IsTest());
    }
}