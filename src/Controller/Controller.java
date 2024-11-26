package Controller;

import Model.PrgState;
import Model.Stmt.IStmt;
import Model.Stmt.NopStmt;
import Model.Values.RefValue;
import Model.Values.Value;
import Repository.IRepo;
import Utils.MyException;
import Utils.MyIStack;
import Utils.State.MyExeStack;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {
    private IRepo repository;
    private boolean flag = true;
    public Controller(IRepo repository) {
        this.repository = repository;
    }
    private PrgState oneStep(PrgState state) throws MyException {
        MyExeStack stk = state.getExeStack();
        if(stk.isEmpty())
            throw new MyException("PrgState stack is empty.");
        IStmt curr = stk.pop();
        if (curr instanceof NopStmt)
            return state;
        return curr.execute(state);
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void allStep() throws MyException {
        PrgState state = repository.getPrgState();
        while (!state.getExeStack().isEmpty()) {
            oneStep(state);
            if (flag) {
                repository.logPrgStateExec(state);
                state.getMyHeapTable().setContent(safeGarbageCollector(
                        getAddrFromSymTable(state.getSymTable().getMap().values()),
                        state.getMyHeapTable().getContent()));
            }
        }
    }

    List<Integer> getAddrFromSymTable(Collection<Value> symTableValues){
        return symTableValues.stream()
                .filter(v-> v instanceof RefValue) //gets all the refValues
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddr();}) //each refValue gets replace by it s adress
                .collect(Collectors.toList());
    }

    Map<Integer, Value> safeGarbageCollector(List<Integer> symTableAddr, Map<Integer,Value> heap){
        List<Integer> heapAddr=heap.values().stream()
                .filter(value -> value instanceof RefValue)
                .map(value->{RefValue va=(RefValue) value;
                    return va.getAddr();})
                .toList();

        //Put all from heapAddr in SymTableAddr:
        heapAddr.forEach(v -> {if(!symTableAddr.contains(v)) symTableAddr.add(v);});

        return heap.entrySet().stream().filter(e-> symTableAddr.contains(e.getKey())).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
