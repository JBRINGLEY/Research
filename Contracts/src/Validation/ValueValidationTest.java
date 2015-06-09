package Validation;
import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.*;

public class ValueValidationTest {

    @Test
    public void testId() throws Exception {
        // region Valid Tests
        ValueValidation.Id(0);
        // endregion

        //region Invalid Tests
        try {
            ValueValidation.Id(-1);
            fail();
        } catch (Exception e) {
        }

        try {
            ValueValidation.Id(-10);
            fail();
        } catch (Exception e) {
        }
        // endregion
    }

    @Test
    public void testTitle() throws Exception {
        // region Valid Tests
        ValueValidation.Title("test");
        // endregion

        // region Invalid Tests
        try {
            ValueValidation.Title(null);
            fail();
        } catch (Exception e) {
        }
        try {
            ValueValidation.Title("");
            fail();
        } catch (Exception e) {
        }
        try {
            ValueValidation.Title("adfa.");
            fail();
        } catch (Exception e) {
        }
        try {
            ValueValidation.Title("adsfasf,adsf");
            fail();
        } catch (Exception e) {
        }
        try {
            ValueValidation.Title("asdfdasfas>adsfadsf");
            fail();
        } catch (Exception e) {
        }
        try {
            ValueValidation.Title("adsf<asdfads");
            fail();
        } catch (Exception e) {
        }
        try {
            ValueValidation.Title("adfa{asdf");
            fail();
        } catch (Exception e) {
        }
        try {
            ValueValidation.Title("adfa}ad");
            fail();
        } catch (Exception e) {
        }
        try {
            ValueValidation.Title("afsd/adsfasd");
            fail();
        } catch (Exception e) {
        }
        try {
            ValueValidation.Title("fadsfasd\\adfadsf");
            fail();
        } catch (Exception e) {
        }
        try {
            ValueValidation.Title("adfdasf(");
            fail();
        } catch (Exception e) {
        }
        try {
            ValueValidation.Title("asdfads)asdfadf");
            fail();
        } catch (Exception e) {
        }
        // endregion
    }

    @Test
    public void testYear() throws Exception {
        // region Valid
        ValueValidation.Year(1870);
        ValueValidation.Year(LocalDate.now().getYear());
        // endregion

        //region Invalid
        try {
            ValueValidation.Year(1869);
            fail();
        } catch (Exception e) {
        }
        try {
            ValueValidation.Year(LocalDate.now().getYear() + 1);
            fail();
        } catch (Exception e) {
        }
        // endregion
    }

    @Test
    public void testUrl() throws Exception {
        // region Valid
        ValueValidation.Url("http://test");
        // endregion

        // region Invalid
        try {
            ValueValidation.Url(null);
            fail();
        } catch (Exception e) {
        }
        try {
            ValueValidation.Url("");
            fail();
        } catch (Exception e) {
        }
        try {
            ValueValidation.Url("test.com");
            fail();
        } catch (Exception e) {
        }
        try {
            ValueValidation.Url(" http://test.com");
            fail();
        } catch (Exception e) {
        }
        // endregion
    }

    @Test
    public void testName() throws Exception {
        // region Valid
        ValueValidation.Name("test");
        ValueValidation.Name("test1");
        ValueValidation.Name("tes,t");
        ValueValidation.Name("tes.t");
        ValueValidation.Name("tes-t");
        ValueValidation.Name("tes)t");
        // endregion

        // region Invalid
        try {
            ValueValidation.Name(null);
            fail();
        } catch (Exception e) {}
        try {
            ValueValidation.Name("");
            fail();
        } catch (Exception e) {}
        // endregion
    }
}