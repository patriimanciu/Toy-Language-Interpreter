package Model.Stmt;

import Model.PrgState;
import Model.Types.Type;
import Model.Values.Value;
import Utils.MyException;
import Utils.MyIDic;
import Utils.State.MyExeStack;

public class CompStmt implements IStmt {
    private final IStmt firstStmt;
    private final IStmt secondStmt;
    public CompStmt(IStmt firstStmt, IStmt secondStmt) {
        this.firstStmt = firstStmt;
        this.secondStmt = secondStmt;
    }

    public IStmt getFirstStmt() {
        return firstStmt;
    }

    public IStmt getSecondStmt() {
        return secondStmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
            MyExeStack stack = state.getExeStack();
            stack.push(secondStmt);
            stack.push(firstStmt);
            return null;
    }

    @Override
    public MyIDic<String, Type> typecheck(MyIDic<String, Type> typeEnv) throws MyException {
        return secondStmt.typecheck(firstStmt.typecheck(typeEnv));
    }

    @Override
    public String toString() {
        return "(" + firstStmt + ", " + secondStmt + ")";
    }
}
