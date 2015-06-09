package Data;

import DataContract.IReviewer;
import Validation.ValueValidation;

class Reviewer implements IReviewer {

    // region Private instance variables
    private int _id;
    private String _name;
    private boolean _isTest;
    // endregion

    // region Constructors
    public Reviewer(String name, boolean isTest) {
        this(0, name, isTest);
    }

    public Reviewer(int id, String name, boolean isTest) {
        ValueValidation.Id(id);
        ValueValidation.Name(name);

        _id = id;
        _name = name;
        _isTest = isTest;
    }
    // endregion

    // region Public methods
    public int GetId() {
        return _id;
    }

    public String GetName() {
        return _name;
    }

    public boolean IsTest() {
        return _isTest;
    }
    // endregion
}
