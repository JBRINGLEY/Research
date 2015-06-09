package Data;

import DataContract.IMovie;
import Validation.ValueValidation;

class Movie implements IMovie {

    // region Private instance variables
    private int _id;
    private int _year;
    private String _title;
    private boolean _isTest;
    // endregion

    // region Constructor(s)
    public Movie(int year, String title, boolean isTest) {
        this(0, year, title, isTest);
    }

    public Movie(int id, int year, String title, boolean isTest) {
        ValueValidation.Id(id);
        ValueValidation.Year(year);
        ValueValidation.Title(title.trim());

        _id = id;
        _year = year;
        _title = title.trim();
        _isTest = isTest;
    }
    // endregion

    // region Public methods
    public int GetId() {
        return _id;
    }

    public int GetYear() {
        return _year;
    }

    public String GetTitle() {
        return _title;
    }

    public boolean IsTest() {
        return _isTest;
    }
    // endregion
}
