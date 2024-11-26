package Model.Exp;

import Model.Types.Type;
import Model.Values.Value;
import Utils.MyException;
import Utils.MyIDic;
import Utils.State.IHeap;

public interface Exp {
    Value eval(MyIDic<String, Value> tbl, IHeap<Value> heap) throws MyException;
    Type typecheck(MyIDic<String, Value> typeTbl) throws MyException;
}
