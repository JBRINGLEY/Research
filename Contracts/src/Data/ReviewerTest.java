package Data;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class ReviewerTest {

    @Test
    public void testConstructor() {
        // region Valid
        new Reviewer("adfads", true);
        new Reviewer("adfads", false);
        new Reviewer(1, "adfads", true);
        new Reviewer(1, "adfads", false);
        // endregion

        // region Invalid
        try {
            new Reviewer(null, true);
            fail();
        } catch (Exception e) {}

        try {
            new Reviewer(null, false);
            fail();
        } catch (Exception e) {}

        try {
            new Reviewer(1, null, true);
            fail();
        } catch (Exception e) {}

        try {
            new Reviewer(1, null, false);
            fail();
        } catch (Exception e) {}

        try {
            new Reviewer("", true);
            fail();
        } catch (Exception e) {}

        try {
            new Reviewer("", false);
            fail();
        } catch (Exception e) {}

        try {
            new Reviewer(1, "", true);
            fail();
        } catch (Exception e) {}

        try {
            new Reviewer(1, "", false);
            fail();
        } catch (Exception e) {}

        try {
            new Reviewer(-1, "adf", true);
            fail();
        } catch (Exception e) {}

        try {
            new Reviewer(-1, "asdf", false);
            fail();
        } catch (Exception e) {}
        // endregion
    }

    @Test
    public void testGetId() throws Exception {
        Reviewer reviewer = new Reviewer("adfdasfa", true);
        assertTrue(reviewer.GetId() == 0);

        reviewer = new Reviewer("adfdasfa", false);
        assertTrue(reviewer.GetId() == 0);

        int id = 2;
        reviewer = new Reviewer(id, "adfdasfa", true);
        assertTrue(reviewer.GetId() == id);

        reviewer = new Reviewer(id, "adfdasfa", true);
        assertTrue(reviewer.GetId() == id);
    }

    @Test
    public void testGetName() throws Exception {
        String name = "afdasfadsf";
        Reviewer reviewer = new Reviewer(name, true);
        assertTrue(name.equals(reviewer.GetName()));

        reviewer = new Reviewer(name, false);
        assertTrue(name.equals(reviewer.GetName()));

        reviewer = new Reviewer(1, name, true);
        assertTrue(name.equals(reviewer.GetName()));

        reviewer = new Reviewer(1, name, false);
        assertTrue(name.equals(reviewer.GetName()));
    }

    @Test
    public void testIsTest() throws Exception {
        Reviewer reviewer = new Reviewer("adf", true);
        assertTrue(reviewer.IsTest());

        reviewer = new Reviewer("adf", false);
        assertFalse(reviewer.IsTest());

        reviewer = new Reviewer(1, "adf", true);
        assertTrue(reviewer.IsTest());

        reviewer = new Reviewer(1, "adf", false);
        assertFalse(reviewer.IsTest());
    }
}