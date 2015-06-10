package RegularExpression;

import com.sun.tools.javac.util.Assert;

import java.util.*;
import java.util.regex.Pattern;

public class RegularExpression {
    private static Map<String, Boolean> urlsForTest;

    public static void main(String args[]) {
        buildUrlList();
        String pattern = "^http://www.rottentomatoes.com/m/(.*)/reviews/?(.*)type=user(.*)";

        for (Map.Entry<String, Boolean> urlELement : urlsForTest.entrySet()) {
            Boolean urlMatches = Boolean.valueOf(Pattern.matches(pattern, urlELement.getKey()));
            if (!(urlMatches == urlELement.getValue())) {
                System.out.println(urlELement.getKey() + " url does not match the expected");
            }
        }
    }

    private static void buildUrlList() {
        urlsForTest = new HashMap<String, Boolean>();
        urlsForTest.put("http://www.rottentomatoes.com/m/caddyshack/reviews/?type=user", Boolean.TRUE);
        urlsForTest.put("http://www.rottentomatoes.com/m/caddyshack/reviews/?page=6&type=user&sort=", Boolean.TRUE);
        urlsForTest.put("http://www.rottentomatoes.com/m/insurgent/reviews/?type=user", Boolean.TRUE);
        urlsForTest.put("http://www.rottentomatoes.com/m/insurgent/reviews/?page=3&type=user&sort=", Boolean.TRUE);
        urlsForTest.put("http://www.rottentomatoes.com/browse/dvd-all/?genres=9", Boolean.FALSE);
        urlsForTest.put("http://www.rottentomatoes.com/tv/better-call-saul/s01/", Boolean.FALSE);
        urlsForTest.put("http://www.rottentomatoes.com/tv/better-call-saul/s01/reviews/?type=user", Boolean.FALSE);
        urlsForTest.put("http://www.rottentomatoes.com/celebrity/donald_faison/", Boolean.FALSE);
        urlsForTest.put("http://www.rottentomatoes.com/m/something_new/", Boolean.FALSE);
        urlsForTest.put("http://www.rottentomatoes.com/user/id/780462553/", Boolean.FALSE);
        urlsForTest.put("http://www.rottentomatoes.com/user/id/780462553/ratings", Boolean.FALSE);
    }
}
