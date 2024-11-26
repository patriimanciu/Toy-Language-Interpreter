package Model.Stmt;

import Model.PrgState;
import Utils.MyException;

public class NopStmt implements IStmt{

    @Override
    public PrgState execute(PrgState state) throws MyException {
        return state;
    }
    public String toString() {
        return "";
    }
}
