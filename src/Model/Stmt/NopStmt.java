package Model.Stmt;

import Model.PrgState;
import Utils.MyException;

public class NopStmt implements IStmt{

    @Override
    public PrgState execute(PrgState state) throws MyException {
        return null;
    }
    public String toString() {
        return "";
    }
}
