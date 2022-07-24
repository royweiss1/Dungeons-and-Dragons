package UI;

import callBacks.InputQuery;
import logic.Action;

public abstract class InputProvider {
    private InputQuery inputQuery;
    public InputProvider() {
        inputQuery = this::getAction;
        //()->getInput
    }

    public InputQuery getInputQuery() {
        return inputQuery;
    }

    abstract protected Action getAction();

}
