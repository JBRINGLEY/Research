package ReviewScraper;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

public class ReviewTest {

    @Test
    public void testConstructorArguments() throws Exception {
        new Review("testUser", 4, "TEST", "February 6, 2015");
        try {
            new Review(null, 4, "TEST", "February 6, 2015");
        } catch (NullPointerException e) {}
        try {
            new Review("testUser", -1, "TEST", "February 6, 2015");
        } catch (IllegalArgumentException e) {}
        try {
            new Review("testUser", 4, null, "February 6, 2015");
        } catch (NullPointerException e) {}
        try {
            new Review("testUser", 4, "TEST", null);
        } catch (NullPointerException e) {}
        try {
            new Review("testUser", 4, "TEST", "10/22/1964");
        } catch (ParseException e) {}
    }
}