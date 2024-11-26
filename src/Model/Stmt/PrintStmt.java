package Model.Stmt;

import Model.Exp.Exp;
import Model.PrgState;
import Model.Values.Value;
import Utils.MyException;
import Utils.MyIList;

public class PrintStmt implements IStmt{
    private Exp expression;

    public PrintStmt(Exp exp){
        this.expression = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        Value val = expression.eval(state.getSymTable(), state.getMyHeapTable());
        MyIList<Value> out = state.getOut();
        out.add(val);
        return state;
    }

    public String toString(){
        return "print(" + expression.toString() + ")";
    }
}