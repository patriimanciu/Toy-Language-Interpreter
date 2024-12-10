package Model.Stmt;

import Model.Exp.Exp;
import Model.PrgState;
import Model.Values.BoolValue;
import Utils.MyException;

public class WhileStmt implements IStmt{
    Exp condition;
    IStmt statement;

    public WhileStmt(Exp condition, IStmt statement) {
        this.condition = condition;
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        if (condition.eval(state.getSymTable(), state.getMyHeapTable()) instanceof BoolValue boolVal) {
            if (boolVal.getValue()) {
                state.getExeStack().push(this);
                state.getExeStack().push(statement);
            }
            return null;
        }
        else
            throw new MyException("While condition should evaluate to a BooleanType");
    }

    @Override
    public String toString() {
        return "While(" + condition.toString() + "){" + statement.toString() + "};";
    }
}
