package Repository;

import Model.PrgState;
import Utils.MyException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MyRepo implements IRepo{
    private List<PrgState> repository;
    private String logFilePath;
    public MyRepo(PrgState prg, String file) {
        this.logFilePath = file;
        try {
            PrintWriter tmp = new PrintWriter(file);
            tmp.write("");
            tmp.close();
        } catch (FileNotFoundException ignored) {}
        repository = new ArrayList<>();
        repository.add(prg);
    }
    @Override
    public PrgState getPrgState() {
        return repository.getFirst();
    }

    @Override
    public void add(PrgState currState) {
        repository.add(currState);
    }

    @Override
    public void logPrgStateExec(PrgState p) throws MyException {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, true)));
            logFile.println(p.toString());
            logFile.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}