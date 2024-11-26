package Model.Stmt;
import Model.PrgState;
import Utils.MyException;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException;
}