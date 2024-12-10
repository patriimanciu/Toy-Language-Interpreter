package Model.Stmt;

import Model.PrgState;
import Model.Values.Value;
import Utils.*;
import Utils.State.MyExeStack;
import Utils.State.MyHeap;

import java.util.Map;

public class ForkStmt implements IStmt{
    private final IStmt statement;

    public ForkStmt(IStmt statement) {
        this.statement = statement;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyExeStack newStack = new MyExeStack();
        newStack.push(statement);
        MyIDic<String, Value> newSymTable = new MyDic<>();
        for (Map.Entry<String, Value> entry: state.getSymTable().getMap().entrySet()) {
            newSymTable.put(entry.getKey(), entry.getValue());
        }
        return new PrgState(newStack, newSymTable, state.getFileTable(), (MyHeap<Value>) state.getMyHeapTable(), state.getOut(), statement);
    }

    @Override
    public String toString() {
        return String.format("fork(%s)", statement.toString());
    }
}
