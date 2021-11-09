package state;

import javax.swing.*;

public class NotAllReportsOk implements State {
    @Override
    public void exit() {
        JOptionPane.showMessageDialog(new JMenu(),"Unable to exit\n" + "Not all reports were paid");
    }
}
