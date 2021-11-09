package state;

import Gui.CenterScreen;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

public class AllReportsOk implements State{
    @Override
    public void exit() {
        CenterScreen.clearFlie("C:\\Users\\razie\\IdeaProjects\\HOMEWORK4\\reports.txt");
        System.exit(0);

    }
}
