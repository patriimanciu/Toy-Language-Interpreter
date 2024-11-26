package Utils;

import java.util.List;

public interface MyIStack<T> {
    void push(T t);
    T pop() throws MyException;
    boolean isEmpty();
    public List<T> toListS();
}