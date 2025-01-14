package Model.Stmt;
import Model.ProgramState.PrgState;
import Model.Types.Type;
import Utils.Exceptions.MyException;
import Utils.Collections.MyIDic;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException;
    MyIDic<String, Type> typecheck(MyIDic<String, Type> typeEnv) throws MyException;
}