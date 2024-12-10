package Model.Stmt;

import Model.Exp.Exp;
import Model.PrgState;
import Model.Types.Type;
import Model.Values.Value;
import Utils.MyException;
import Utils.MyIDic;
import Utils.State.IHeap;
import Utils.State.MyExeStack;

public class AssignStmt implements IStmt{
    String variableName;
    Exp expression;

    public AssignStmt(String variableName, Exp expression){
        this.variableName = variableName;
        this.expression = expression;
    }

    public String toString(){
        return variableName + " = " + expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyExeStack stack = state.getExeStack();
        MyIDic<String, Value> symTable = state.getSymTable();
        IHeap<Value> heap = state.getMyHeapTable();
        if (symTable.contains(variableName)){
            Value var = expression.eval(symTable, heap);
            Type typeId = symTable.lookUp(variableName).getType();
            if (var.getType().equals(typeId)){
                symTable.update(variableName, var);
            }
            else
                throw new MyException("Variable '" + variableName + "' is not assignable to type '" + typeId + "'");
        }
        else
            throw new MyException("Variable '" + variableName + "' not found");
        return null;
    }
}
