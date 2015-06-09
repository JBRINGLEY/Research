package Data;

import DataContract.IUrl;
import Validation.ValueValidation;

class Url implements IUrl {

    // region Private instance variables
    private int _id;
    private String _url;
    private boolean _isTest;
    // endregion

    // region Constructor(s)
    public Url(String url, boolean isTest) {
        this(0, url, isTest);
    }

    public Url(int id, String url, boolean isTest) {
        ValueValidation.Id(id);
        ValueValidation.Url(url);

        _id = id;
        _url = url.trim();
        _isTest = isTest;
    }
    // endregion

    // region Public methods
    public int GetId() {
        return _id;
    }

    public String GetUrl() {
        return _url;
    }

    public boolean IsTest() {
        return _isTest;
    }
    // endregion
}
