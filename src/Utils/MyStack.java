package Utils;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    private Stack<T> stack;
    public MyStack() {
        stack = new Stack<>();
    }
    @Override
    public void push(T t) {
        stack.push(t);
    }

    @Override
    public T pop() throws MyException {
        if (stack.isEmpty())
            throw new MyException("Nothing to pop. The stack is empty.");
        return stack.pop();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public List<T> toListS() {
        return (List<T>) Arrays.asList(stack.toArray());
    }

    public String toString() {
        return stack.toString();
    }

    public MyStack<T> deepCopy() {
        MyStack<T> newStack = new MyStack<T>();
        for(T elem : stack) {
            newStack.push(elem);
        }
        return newStack;
    }
}
