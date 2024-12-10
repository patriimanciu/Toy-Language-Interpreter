package Model.Stmt;

import Model.Exp.Exp;
import Model.PrgState;
import Model.Types.Int;
import Model.Types.StringType;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Utils.MyException;

public class ReadFile implements IStmt{
    private Exp exp;
    private String varName;
    private Int i;
    private StringType str;

    public ReadFile(Exp exp, String fileName) {
        this.exp = exp;
        this.varName = fileName;
        i = new Int();
        str = new StringType();
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
       var value = exp.eval(state.getSymTable(), state.getMyHeapTable());
       if (!state.getSymTable().contains(varName) || !state.getSymTable().lookUp(varName).getType().equals(i)) {
           throw new MyException("The var is not defines or it doesn't match the type.");
       }
       var buffer = state.getFileTable().lookUp((StringValue) value);
       try {
           String line = buffer.readLine();
           if (line == null) {
               state.getSymTable().update(varName, new Int().defaultValue());
           } else {
               state.getSymTable().update(varName, new IntValue(Integer.parseInt(line)));
           }
       } catch (Exception e) {
           throw new MyException("Error reading from file.");
       }
       return null;
    }

    public String toString() {
        return "ReadFile(" + exp.toString() + ", " + varName + ")";
    }
}
