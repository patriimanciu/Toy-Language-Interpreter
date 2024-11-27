package Model.Stmt;

import Model.Exp.Exp;
import Model.PrgState;
import Model.Values.RefValue;
import Model.Values.Value;
import Utils.MyException;

public class NewStmt implements IStmt {
    String name;
    Exp exp;

    public NewStmt(String name, Exp expression) {
        this.name = name;
        this.exp = expression;
    }

    @Override
    public PrgState execute(PrgState prg) throws MyException {
        Value v = prg.getSymTable().lookUp(name);
        if (v == null)
            throw new MyException("Variable not found");
        if (v instanceof RefValue refVal) {
            Value value= exp.eval(prg.getSymTable(), prg.getMyHeapTable());
            if (value.getType().equals(refVal.getLocationType())) {
                int address= prg.getMyHeapTable().put(value);
                prg.getSymTable().put(name, new RefValue(address, value.getType()));
                return prg;
            }
            else
                throw new MyException("Value does not match the inner type");
        }
        else
            throw new MyException("Value is not a RefValue");
    }

    @Override
    public String toString() {
        return "new("+ name+","+exp+")";
    }

}