/**
 * raziel perez 316134956
 */
package Gui;

import Inspection.BigBrother;
import components.Driving;
import components.Vehicle;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ButtonCreateRoadSystem implements ActionListener {
    private CenterScreen gui;
    public ButtonCreateRoadSystem(CenterScreen gui){this.gui=gui;}
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        CenterScreen.isStop=true;
        JDialog frame=new JDialog();
        frame.setSize(600,300);

        GridLayout myGridLayout = new GridLayout(1,2);
        JPanel lower=new JPanel();
        lower.setLayout(myGridLayout);
        JButton ok=new JButton("OK");
        JButton cancel=new JButton("cancel");
        lower.add(ok);
        lower.add(cancel);
        JSlider junction=new JSlider(JSlider.HORIZONTAL,3,20,10);

        junction.setMajorTickSpacing(1);
        junction.setMinorTickSpacing(1);
        junction.setPaintTicks(true);
        junction.setPaintLabels(true);




        JSlider vehicle=new JSlider(JSlider.HORIZONTAL,0,50,20);
        vehicle.setMajorTickSpacing(5);
        vehicle.setMinorTickSpacing(0);
        vehicle.setPaintTicks(true);
        vehicle.setPaintLabels(true);

        JLabel num_Junction=new JLabel("number of Junction:");
        num_Junction.setHorizontalAlignment(JLabel.CENTER);
        JLabel num_Vehicle=new JLabel("number of Vehicle:");
        num_Vehicle.setHorizontalAlignment(JLabel.CENTER);
        JPanel upper=new JPanel();
        GridLayout GridLayout2 = new GridLayout(4,1);
        upper.setLayout(GridLayout2);
        upper.add(num_Junction,BorderLayout.CENTER);
        upper.add(junction);
        upper.add(num_Vehicle,BorderLayout.CENTER);
        upper.add(vehicle);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();


            }
        });
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                BigBrother.setReportId(1);
                CenterScreen.clearFlie("C:\\Users\\razie\\IdeaProjects\\HOMEWORK4\\reports.txt");


                gui.setDriving(new Driving(junction.getValue(),vehicle.getValue()));
                Vehicle.setObjectsCount(1);
                gui.DrawMap();
                frame.dispose();


            }
        });

        frame.add(lower,BorderLayout.SOUTH);
        frame.add(upper,BorderLayout.NORTH);
        frame.setVisible(true);

    }
}
