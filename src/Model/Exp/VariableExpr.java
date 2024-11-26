package Model.Exp;

import Model.Types.Type;
import Model.Values.Value;
import Utils.MyException;
import Utils.MyIDic;
import Utils.State.IHeap;

public class VariableExpr implements Exp {
    private String variableName;

    public VariableExpr(String variableName) {
        this.variableName = variableName;
    }

    public String toString() {
        return variableName;
    }

    @Override
    public Value eval(MyIDic<String, Value> tbl, IHeap<Value> heap) throws MyException {
        return tbl.lookUp(variableName);
    }

    @Override
    public Type typecheck(MyIDic<String, Value> typeTbl) throws MyException {
        return null;
    }
}
