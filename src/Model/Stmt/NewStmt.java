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
        Value value= exp.eval(prg.getSymTable(), prg.getMyHeapTable());
        int address= prg.getMyHeapTable().put(value);
        prg.getSymTable().put(name, new RefValue(address, value.getType()));
        return prg;
    }

    @Override
    public String toString() {
        return "new("+ name+","+exp+")";
    }

}