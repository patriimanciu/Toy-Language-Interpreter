package Model.Stmt;

import Model.Exp.Exp;
import Model.PrgState;
import Model.Types.Bool;
import Model.Values.BoolValue;
import Model.Values.Value;
import Utils.MyException;
import Utils.MyIDic;
import Utils.State.IHeap;
import Utils.State.MyExeStack;

public class IfStmt implements IStmt{
    Exp expression;
    IStmt thenStmt;
    IStmt elseStmt;

    public IfStmt(Exp expression, IStmt thenStmt, IStmt elseStmt){
        this.expression = expression;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
    }

    public Exp getExpression() {
        return expression;
    }

    public IStmt getThenStmt() {
        return thenStmt;
    }

    public IStmt getElseStmt() {
        return elseStmt;
    }

    public String toString(){
        return "(IF("+ expression.toString()+") THEN(" +thenStmt.toString() +")ELSE("+elseStmt.toString()+"))";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyExeStack stack = state.getExeStack();
        MyIDic<String, Value> symTable = state.getSymTable();
        IHeap<Value> heap = state.getMyHeapTable();
        Value condition = expression.eval(symTable, heap);
        if (!condition.getType().equals(new Bool()))
            throw new MyException("Condition is not boolean");
        BoolValue boolCondition = (BoolValue) condition;
        if (boolCondition.getValue())
            stack.push(thenStmt);
        else
            stack.push(elseStmt);
        return null;
    }
}
