package Utils.State;

import Model.Types.Int;
import Model.Values.Value;
import Utils.MyException;

import java.util.Map;

public interface IHeap<V> {
    int put(V v) throws MyException;
    void setContent(Map<Integer, V> map);
    Map<Integer, V> getContent();
    V lookUp(int a);
    void update(int addr, V value);
}