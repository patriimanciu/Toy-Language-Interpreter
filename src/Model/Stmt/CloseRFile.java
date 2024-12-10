package Model.Stmt;

import Model.Exp.Exp;
import Model.PrgState;
import Model.Types.StringType;
import Model.Values.StringValue;
import Utils.MyException;

public class CloseRFile implements IStmt {
    private Exp expression;
    private StringType str;

    public CloseRFile(Exp expression) {
        this.expression = expression;
        this.str = new StringType();
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        var value = expression.eval(state.getSymTable(), state.getMyHeapTable());
        if (!value.getType().equals(str))
            throw new MyException("Invalid expression: not a string.");
        var buff = state.getFileTable().lookUp((StringValue) value);
        if (buff == null)
            throw new MyException("File not opened.");
        try {
            buff.close();
            state.getFileTable().remove((StringValue) value);
        } catch (Exception e) {
            throw new MyException("Error closing the file.");
        }
        return null;
    }

    public String toString() {
        return "CloseRFile(" + expression.toString() + ")";
    }
}
