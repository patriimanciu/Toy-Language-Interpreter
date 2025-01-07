package Model.Stmt;

import Model.PrgState;
import Model.Types.Type;
import Utils.MyException;
import Utils.MyIDic;

public class NopStmt implements IStmt{

    @Override
    public PrgState execute(PrgState state) throws MyException {
        return null;
    }

    @Override
    public MyIDic<String, Type> typecheck(MyIDic<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    public String toString() {
        return "";
    }
}
