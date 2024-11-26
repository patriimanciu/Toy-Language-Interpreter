package Repository;

import Model.PrgState;
import Utils.MyException;

public interface IRepo {
    PrgState getPrgState();
    void add(PrgState currState);
    void logPrgStateExec(PrgState p) throws MyException;
}
