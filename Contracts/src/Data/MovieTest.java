package Data;

import DataContract.IMovie;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MovieTest {

    @Test
    public void testMovieConstructorArgumentId() {
        try {
            new Movie(-1, 2007, "test", true);
            fail();
        } catch (Exception ignored) {}
    }

    @Test
    public void testMovieConstructorArgumentTitle() {
        try {
            new Movie(1, 2007, null, true);
            fail();
        } catch (Exception ignored) {}

        try {
            new Movie(1, 2007, "tes.t", true);
            fail();
        } catch (Exception ignored) {}

        try {
            new Movie(1, 2007, " ", true);
            fail();
        } catch (Exception ignored) {}

        try {
            new Movie(1, 2007, "<", true);
            fail();
        } catch (Exception ignored) {}

        try {
            new Movie(1, 2007, ".", true);
            fail();
        } catch (Exception ignored) {}

        try {
            new Movie(1, 2007, ">", true);
            fail();
        } catch (Exception ignored) {}

        try {
            new Movie(1, 2007, "[", true);
            fail();
        } catch (Exception ignored) {}

        try {
            new Movie(1, 2007, "]", true);
            fail();
        } catch (Exception ignored) {}

        try {
            new Movie(1, 2007, "\\", true);
            fail();
        } catch (Exception ignored) {}

        try {
            new Movie(1, 2007, "/", true);
            fail();
        } catch (Exception ignored) {}

        try {
            new Movie(1, 2007, "{", true);
            fail();
        } catch (Exception ignored) {}

        try {
            new Movie(1, 2007, "}", true);
            fail();
        } catch (Exception ignored) {}

        try {
            new Movie(1, 2007, "", true);
            fail();
        } catch (Exception ignored) {}

    }

    @Test
    public void testMovieConstructorArgumentYear() {
        try{
            new Movie(1, 1869, "test", true);
            fail();
        } catch (Exception ignored) {}

        try{
            new Movie(1, 2016, "test", true);
            fail();
        } catch (Exception ignored) {}

        try{
            new Movie(1, 2017, "test", true);
            fail();
        } catch (Exception ignored) {}

    }

    @Test
    public void testGetId() throws Exception {
        IMovie movie = new Movie(1, 2007, "test", true);
        assertTrue(movie.GetId() == 1);
    }

    @Test
     public void testGetYear() throws Exception {
        IMovie movie = new Movie(1, 2007, "test", true);
        assertTrue(movie.GetYear() == 2007);
    }

    @Test
    public void testGetTitle() throws Exception {
        IMovie movie =new Movie(1, 2007, "test", true);
        assertTrue(movie.GetTitle().equals("test"));

        movie = new Movie(1, 2007, "   test  ", true);
        System.out.println(movie.GetTitle());
        assertTrue(movie.GetTitle().equals("test"));
    }

    /*
        Test Scenarios:
            - Same collection reference
            - Different collection reference same key/value pairs
            - Different collection reference different key/value pairs
     */

    @Test
    public void testSameReference() {
        Map<String, Integer> refOne = new HashMap<String, Integer>() {{
            put("1", 1);
            put("2", 2);
            put("3", 3);
        }};

        Map<String, Integer> refTwo = refOne;

        assertEquals(refOne, refTwo);
    }

    @Test
    public void testDifferentReferenceSameValues() {
        Map<String, Integer> refOne = new HashMap<String, Integer>() {{
            put("1", 1);
            put("2", 2);
            put("3", 3);
        }};

        Map<String, Integer> refTwo = new HashMap<String, Integer>() {{
            put("1", 1);
            put("2", 2);
            put("3", 3);
        }};

        assertEquals(refOne, refTwo);
    }

    @Test
    public void testDifferentReferenceDifferentValues() {
        Map<String, Integer> refOne = new HashMap<String, Integer>() {{
            put("1", 1);
            put("2", 2);
            put("3", 3);
        }};

        Map<String, Integer> refTwo = new HashMap<String, Integer>() {{
            put("1", 1);
            put("2", 2);
            put("4", 3);
        }};

        assertEquals(refOne, refTwo);
    }
}