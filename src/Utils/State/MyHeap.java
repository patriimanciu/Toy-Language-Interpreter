package Utils.State;
import Model.Types.Int;
import Utils.MyException;

import java.util.HashMap;
import java.util.Map;

public class MyHeap<V> implements IHeap<V> {
    public static int nextAddr = 0;
    protected Map<Integer, V> map;
    public MyHeap() {
        map = new HashMap<Integer, V>();
    }

    private int nextFreeAddr(){
        nextAddr++;
        return nextAddr;
    }

    @Override
    public int put(V v) throws MyException {
        map.put(nextFreeAddr(), v);
        return nextAddr;
    }

    @Override
    public Map<Integer, V> getContent() {
        return map;
    }

    @Override
    public V lookUp(int a) {
        return this.map.get(a);
    }

    @Override
    public void update(int addr, V value) {
        this.map.put(addr, value);
    }

    @Override
    public void setContent(Map<Integer, V> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return map.toString();
    }
}