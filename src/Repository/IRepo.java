package Repository;

import Model.PrgState;
import Utils.MyException;

import java.util.List;

public interface IRepo {
    void add(PrgState currState);
    void logPrgStateExec(PrgState p) throws MyException;
    public List<PrgState> getPrgList();
    public void setPrgList(List<PrgState> prgList);
}
