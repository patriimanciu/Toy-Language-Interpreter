package Model;

import Model.Stmt.CompStmt;
import Model.Stmt.IStmt;
import Model.Stmt.NopStmt;
import Model.Values.StringValue;
import Model.Values.Value;
import Utils.*;
import Utils.State.IHeap;
import Utils.State.MyExeStack;
import Utils.State.MyHeap;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class PrgState {
    private MyExeStack exeStack;
    public MyExeStack getExeStack() {
        return exeStack;
    }
    public void setExeStack(MyExeStack exeStack) {
        this.exeStack = exeStack;
    }

    private MyIDic<String, Value> symTable;
    public MyIDic<String, Value> getSymTable() {
        return (MyIDic<String, Value>) symTable;
    }
    public void setSymTable(MyIDic<String, Value> symTable) {
        this.symTable = symTable;
    }

    private MyList<Value> out;
    public MyList<Value> getOut() {
        return out;
    }
    public void setOut(MyList<Value> out) {
        this.out = out;
    }


    public IHeap<Value> myHeapTable;
    public IHeap<Value> getMyHeapTable() {
        return myHeapTable;
    }
    public void setMyHeapTable(IHeap<Value> myHeapTable) {
        this.myHeapTable = myHeapTable;
    }

    IStmt originalProgram;
    public IStmt getOriginalProgram() {
        return originalProgram;
    }

    private MyIDic<StringValue, BufferedReader> fileTable;
    public PrgState(MyExeStack stk, MyIDic<String, Value> symtbl, MyIDic<StringValue, BufferedReader> filetbl, MyHeap<Value> heapTable, MyList<Value> ot, IStmt prg) {
        exeStack = stk;
        symTable = symtbl;
        fileTable = filetbl;
        myHeapTable = heapTable;
        out = ot;
        originalProgram = prg;
        stk.push(prg);
    }

    public String toString() {
        return "----------------------------------------------- \n ExeStack: " + distinctStatamentsString() +
                "\n SymTable: " + symTable.toString() +
                "\n Out: " + out.toString() +
                "\n FileTable: " + getFileTableList() +
                "\n Heap: " + getMyHeapTable() + "\n----------------------------------------------- \n";
    }

    public MyIDic<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public String getFileTableList(){
        // return a string with the list of files from the file table, each on a new line
        StringBuilder result = new StringBuilder();
        for (StringValue key : fileTable.getKeys()) {
            result.append(key.toString()).append("\n");
        }
        return result.toString();
    }

    public String getSymTableList(){
        // return a string with the list of variables from the symbol table, each on a new line
        StringBuilder result = new StringBuilder();
        for (String key : symTable.getKeys()) {
            result.append(key).append(symTable.lookUp(key)).append("\n");
        }
        return result.toString();
    }

    public String getOutList(){
        // return a string with the list of values from the output list, each on a new line
        StringBuilder result = new StringBuilder();
        for (Value value : out.toList()) {
            result.append(value.toString()).append("\n");
        }
        return result.toString();
    }

    private Node<IStmt> toTree(IStmt stmt) {
        Node node;
        if (stmt instanceof CompStmt){
            CompStmt comptStmt = (CompStmt) stmt;
            node = new Node<>(new NopStmt());
            node.setLeft(new Node<>(comptStmt.getFirstStmt()));
            node.setRight(toTree( comptStmt.getSecondStmt()));
        }
        else {
            node = new Node<>(stmt);
        }
        return node;

    }
    public List<IStmt> distinctStataments() {
        MyTree<IStmt> tree =  new MyTree<IStmt>();
        List<IStmt> inOrderList=new LinkedList<IStmt>();
        if(!getExeStack().toList().isEmpty()) {
            IStmt stmt = getExeStack().toList().getFirst();
            tree.setRoot(toTree(stmt));
            tree.inorderTraversal(inOrderList, tree.getRoot());
        }
        return inOrderList;
    }

    public String distinctStatamentsString() {
        List<IStmt> inOrderList = distinctStataments();
        StringBuilder str = new StringBuilder();
        for (IStmt stmt : inOrderList) {
            if(!Objects.equals(stmt.toString(),";")) {
                if (!Objects.equals(stmt.toString(), "")) {
                    str.append(stmt.toString());
                    str.append("\n");
                }
            }
        }
        return str.toString();
    }
}