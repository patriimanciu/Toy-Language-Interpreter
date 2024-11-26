package View;

import Controller.Controller;
import Model.Exp.ValueExp;
import Model.Exp.VariableExpr;
import Model.PrgState;
import Model.Stmt.*;
import Model.Types.Int;
import Model.Types.StringType;
import Model.Values.StringValue;
import Repository.IRepo;
import Repository.MyRepo;
import Utils.*;
import Utils.State.MyExeStack;
import Utils.State.MyHeap;

public class View {
    public View() {}
    public void runTest() {
        // string varf; varf="test.in"; openRFile(varf); int varc; readFile(varf,varc); print(varc);
        // readFile(varf,varc); print(varc); closeRFile(varf)
        IStmt testStm = new CompStmt(new VariableDeclStmt("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("/Users/patriimanciu/home/uni/map/A3_toyLanguage/src/View/test.in"))),
                        new CompStmt(new OpenRFile(new VariableExpr("varf")),
                                new CompStmt(new VariableDeclStmt("varc", new Int()),
                                        new CompStmt(new ReadFile(new VariableExpr("varf"), "varc"),
                                                new CompStmt(new PrintStmt(new VariableExpr("varc")), new CompStmt(new ReadFile(new VariableExpr("varf"), "varc"),
                                                        new CompStmt(new PrintStmt(new VariableExpr("varc")),
                                                                new CloseRFile(new VariableExpr("varf"))))))))));
        PrgState prg1 = new PrgState(new MyExeStack(), new MyDic<>(), new MyDic<>(), new MyHeap<>(), new MyList<>(), testStm);
        IRepo repo = new MyRepo(prg1, "logTest.txt");
        Controller controller = new Controller(repo);
        try {
            controller.allStep();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}
