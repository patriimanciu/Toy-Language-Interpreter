import Model.Exp.*;
import Model.PrgState;
import Model.Stmt.*;
import Model.Types.Bool;
import Model.Types.Int;
import Model.Types.RefType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Repository.IRepo;
import Repository.MyRepo;
import Utils.MyDic;
import Utils.MyList;
import Utils.State.MyExeStack;
import Controller.Controller;
import Utils.State.MyHeap;
import View.Command.ExitCommand;
import View.Command.RunCommand;
import View.TextMenu;
import View.View;

public class Interpreter {
    public static void main(String[] args) {
        View view = new View();
        view.runTest();

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));

        try {
            IStmt p1 = new CompStmt(new VariableDeclStmt("v", new Int()),
                    new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
                            new PrintStmt(new VariableExpr("v"))));
            PrgState prg1 = new PrgState(new MyExeStack(), new MyDic<>(), new MyDic<>(), new MyHeap<>(), new MyList<>(), p1);
            IRepo repo1 = new MyRepo(prg1, "log1.txt");
            Controller ctrl1 = new Controller(repo1);
            menu.addCommand(new RunCommand("1", p1.toString(), ctrl1));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try {
            IStmt p2 = new CompStmt( new VariableDeclStmt("a",new Int()),
                    new CompStmt(new VariableDeclStmt("b",new Int()),
                            new CompStmt(new AssignStmt("a",
                                    new ArithExp('+',new ValueExp(new IntValue(2)),
                                            new ArithExp('*',new ValueExp(new IntValue(3)),
                                                    new ValueExp(new IntValue(5))))),
                                    new CompStmt(new AssignStmt("b",
                                            new ArithExp('+',new VariableExpr("a"), new ValueExp(new IntValue(1)))),
                                            new PrintStmt(new VariableExpr("b"))))));
            PrgState prg2 = new PrgState(new MyExeStack(), new MyDic<>(), new MyDic<>(), new MyHeap<>(), new MyList<>(), p2);
            IRepo repo2 = new MyRepo(prg2, "log2.txt");
            Controller ctrl2 = new Controller(repo2);
            menu.addCommand(new RunCommand("2", p2.toString(), ctrl2));
        }
        catch (Exception e) {
            e.printStackTrace();
        }




        try {
            IStmt p3 = new CompStmt(new VariableDeclStmt("a",new Bool()),
                    new CompStmt(new VariableDeclStmt("v", new Int()),
                            new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                    new CompStmt(new IfStmt(new VariableExpr("a"),
                                            new AssignStmt("v",new ValueExp(new IntValue(2))),
                                            new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                            new PrintStmt(new VariableExpr("v"))))));

            PrgState prg3 = new PrgState(new MyExeStack(), new MyDic<>(), new MyDic<>(), new MyHeap<>(), new MyList<>(), p3);
            IRepo repo3 = new MyRepo(prg3, "log3.txt");
            Controller ctrl3 = new Controller(repo3);
            menu.addCommand(new RunCommand("3", p3.toString(), ctrl3));
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        try {
//        Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)
            IStmt p4 = new CompStmt(
                    new VariableDeclStmt("v", new RefType(new Int())),
                    new CompStmt(new NewStmt("v", new ConstExpr(new IntValue(20))),
                            new CompStmt(new VariableDeclStmt("a", new RefType(new RefType(new Int()))),
                                    new CompStmt(new NewStmt("a", new VariableExpr("v")),
                                            new CompStmt(new PrintStmt(new VariableExpr("v")),
                                                    new PrintStmt(new VariableExpr("a")))))));

            PrgState prg4 = new PrgState(new MyExeStack(), new MyDic<>(), new MyDic<>(), new MyHeap<>(), new MyList<>(), p4);
            IRepo repo4 = new MyRepo(prg4, "log4.txt");
            Controller ctrl4 = new Controller(repo4);
            menu.addCommand(new RunCommand("4", p4.toString(), ctrl4));
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        try {
            // Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
            // Ref int v; new(v,20); Ref Ref int a; new(a,v); print(rH(v)); print(rH(rH(a))+5)
            IStmt p5 = new CompStmt(new VariableDeclStmt("v", new RefType(new Int())),
                    new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                            new CompStmt(new VariableDeclStmt("a", new RefType(new RefType(new Int()))),
                                    new CompStmt(new NewStmt("a", new VariableExpr("v")),
                                            new CompStmt( new NewStmt("v", new ValueExp(new IntValue(30))),
                                                    new CompStmt(new PrintStmt(new rH(new VariableExpr("v"))),
                                                            new PrintStmt(new rH(new rH(new VariableExpr("a"))))))))));

            PrgState prg5 = new PrgState(new MyExeStack(), new MyDic<>(), new MyDic<>(), new MyHeap<>(), new MyList<>(), p5);
            IRepo repo5 = new MyRepo(prg5, "log5.txt");
            Controller ctrl5 = new Controller(repo5);
            menu.addCommand(new RunCommand("5", p5.toString(), ctrl5));
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        try {
            // Ref int v; new(v,20); print(rH(v)); wH(v,30); print(rH(v)+5);
//            IStmt p6 = new CompStmt(new VariableDeclStmt("v", new RefType(new Int())),
//                    new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
//                            new CompStmt(new PrintStmt(new rH(new VariableExpr("v"))),
//                                    new CompStmt(new WriteHeapStmt("v", new ValueExp(new IntValue(30))),
//                                            new PrintStmt(new ArithExp('+', new rH(new VariableExpr("v")), new ValueExp(new IntValue(5))))))));
            IStmt p6 = new CompStmt(new VariableDeclStmt("v", new RefType(new Int())), new CompStmt(new NewStmt("v",
                    new ValueExp(new IntValue(20))),new CompStmt( new PrintStmt(new rH(new VariableExpr("v"))),
                    new CompStmt(new WriteHeapStmt("v", new ValueExp(new IntValue(30))), new PrintStmt(new ArithExp(
                            '+',new rH(new VariableExpr("v")),new ValueExp(new IntValue(5))))))));

            PrgState prg6 = new PrgState(new MyExeStack(), new MyDic<>(), new MyDic<>(), new MyHeap<>(), new MyList<>(), p6);
            IRepo repo6 = new MyRepo(prg6, "log6.txt");
            Controller ctrl6 = new Controller(repo6);
            menu.addCommand(new RunCommand("6", p6.toString(), ctrl6));
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        try {
            // int v; v=4; (while (v>0) print(v);v=v-1);print(v)
            IStmt p7=  new CompStmt(
                    new VariableDeclStmt("v", new Int()), // int v;
                    new CompStmt(
                            new AssignStmt("v", new ValueExp(new IntValue(4))), // v = 4;
                            new CompStmt(
                                    new WhileStmt(
                                            new RelationalExp(">", new VariableExpr("v"), new ValueExp(new IntValue(0))), // while (v > 0)
                                            new CompStmt(
                                                    new PrintStmt(new VariableExpr("v")), // print(v);
                                                    new AssignStmt("v", new ArithExp('-', new VariableExpr("v"), new ValueExp(new IntValue(1)))) // v = v - 1;
                                            )
                                    ),
                                    new PrintStmt(new VariableExpr("v")) // print(v);
                            )
                    )
            );
            PrgState prg7 = new PrgState(new MyExeStack(), new MyDic<>(), new MyDic<>(), new MyHeap<>(), new MyList<>(), p7);
            IRepo repo7 = new MyRepo(prg7, "log7.txt");
            Controller ctrl7 = new Controller(repo7);
            menu.addCommand(new RunCommand("7", p7.toString(), ctrl7));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        menu.show();
    }
}
