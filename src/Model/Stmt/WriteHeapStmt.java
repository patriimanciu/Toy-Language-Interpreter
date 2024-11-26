package Model.Stmt;

import Model.Exp.Exp;
import Model.PrgState;
import Model.Types.RefType;
import Model.Values.RefValue;
import Model.Values.Value;
import Utils.MyException;

public class WriteHeapStmt implements IStmt{
    String varName;
    Exp valueExpression;

    public WriteHeapStmt(String name, Exp valueExpression) {
        this.varName = name;
        this.valueExpression = valueExpression;
    }

    @Override
    public String toString() {
        return "writeHeap(" + varName + ", " + valueExpression.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        Value v=state.getSymTable().lookUp(varName);
        if(v==null) throw new MyException("The variable is not declared!");
        if(v instanceof RefValue rv){
            if(state.getMyHeapTable().lookUp(rv.getAddr())==null) throw new MyException("The memory is not allocated!");
            Value newV=valueExpression.eval(state.getSymTable(),state.getMyHeapTable());
            if (newV.getType().equals(rv.getLocationType())) {
                state.getMyHeapTable().update(rv.getAddr(), newV);
                return state;
            }
            else throw new MyException("The type of the variable is not the same as the type of the location! Expected type: " + rv.getType() + "; Actual type: " + newV.getType());

        }
        else throw new MyException("The variable is not a RefValue!");
    }
}
