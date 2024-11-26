package Model.Stmt;

import Model.PrgState;
import Utils.MyException;
import Utils.MyIStack;
import Utils.State.MyExeStack;

public class CompStmt implements IStmt {
    private IStmt firstStmt;
    private IStmt secondStmt;
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
            return state;
    }

    @Override
    public String toString() {
        return "(" + firstStmt + ", " + secondStmt + ")";
    }
}
