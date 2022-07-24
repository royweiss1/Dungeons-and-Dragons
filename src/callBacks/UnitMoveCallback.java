package callBacks;

import logic.Action;
import logic.Unit;

public interface UnitMoveCallback {
    void call(Unit u, Action action); // () -> levelManager.processStep(u,action)? maybe
}
