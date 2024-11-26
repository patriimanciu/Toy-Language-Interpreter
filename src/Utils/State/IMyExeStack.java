package Utils.State;

import Model.Stmt.IStmt;
import Utils.MyException;

import java.util.List;

public interface IMyExeStack {
    IStmt pop() throws Utils.MyException;
    void push(IStmt i);
    boolean isEmpty();
    int size();
    String toString();
    List<IStmt> toList();
}
