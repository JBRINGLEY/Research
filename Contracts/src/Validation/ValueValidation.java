package Validation;

import java.time.LocalDate;

public class ValueValidation {

    private static String illegalCharacters = ".,<>{}[]/\\()";

    public static void Id(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id passed was negative");
        }
    }

    public static void Title(String title) {
        if (title == null) {
            throw new IllegalArgumentException("Title passed was null");
        }
        if (title.length() == 0) {
            throw new IllegalArgumentException("Title passed was empty");
        }
        for (char character : title.toCharArray()) {
            if (illegalCharacters.indexOf(character) != -1) {
                throw new IllegalArgumentException("Illegal character found " +
                        "in title");
            }
        }
    }

    public static void Year(int year) {
        if (year < 1870) {
            throw new IllegalArgumentException("Year passed pre-dates film");
        }
        if (year > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("Year passed was in the future");
        }
    }

    public static void Url(String url) {
        if (url == null) {
            throw new IllegalArgumentException("Url passed was null");
        }
        if (url.length() == 0) {
            throw new IllegalArgumentException("Url passed was empty");
        }
        if (!url.startsWith("http://")) {
            throw new IllegalArgumentException("Url passed did not start " +
                    "with http://");
        }
    }

    public static void Name(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name passed was null");
        }
        if (name.length() == 0) {
            throw new IllegalArgumentException("Name passed contained no " +
                    "characters");
        }
    }
}
