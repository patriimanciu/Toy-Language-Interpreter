package Model.Stmt;
import Model.PrgState;
import Model.Types.Type;
import Model.Values.Value;
import Utils.MyException;
import Utils.MyIDic;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException;
    MyIDic<String, Type> typecheck(MyIDic<String, Type> typeEnv) throws MyException;
}