/**
 * raziel perez 316134956
 */
package Gui;

import Inspection.BigBrother;
import builder.CityBuilder;
import builder.CountryBuilder;
import builder.MapBuilder;
import builder.MapEngineer;
import components.*;
import prototype.Workshop;
import utilities.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class CenterScreen implements Utilities {
    Driving driving;
    JFrame frame = new JFrame("Roads system");
    JScrollPane sp;
    myPanel mainPanel;
    private boolean isTable=false;
    public static boolean isStop=false;
    JDialog dialogReport;

    /**
     * A Constructor  builds the main frame by building buttons and a top menu
     */
    public CenterScreen() {
        clearFlie("C:\\Users\\razie\\IdeaProjects\\HOMEWORK4\\reports.txt");
        frame.setSize(1000, 1000);

        //****************************menu panel***********************

        mainPanel=new myPanel();
        mainPanel.setSize(810,610);
        mainPanel.setBackground(Color.WHITE);

        //****************************JPanel lower***********************


        JPanel lower = new JPanel();
        GridLayout myGridLayout = new GridLayout(1, 5);
        lower.setLayout(myGridLayout);

        //**************************** JButton info***********************

        JButton info = new JButton("Info");
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(!isTable) {
                    drawTable();
                }
                else {
                    sp.setVisible(false);
                    isTable=false;

                }

            }
        });

        //****************************JButton start ***********************

        JButton start = new JButton("Start");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(driving==null){return;}
                isStop=false;
                driving.setMainPanel(mainPanel);
                (new Thread(driving)).start();

            }
        });

        //****************************JButton stop***********************

        JButton stop = new JButton("Stop");
        stop.addActionListener(new ActionListener() {
            @Override
            public  void actionPerformed(ActionEvent actionEvent) {
                isStop=true;
            }
        });

        //****************************JButton create_road_system***********************

        JButton create_road_system = new JButton("Create road system");
        create_road_system.addActionListener(new ButtonCreateRoadSystem(this));

        //****************************JButton resume***********************

        JButton resume = new JButton("Resume");
        resume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                isStop=false;
                driving.resumeAll();

            }
        });

        //****************************add Item***********************


        lower.add(create_road_system);
        lower.add(start);
        lower.add(stop);
        lower.add(resume);
        lower.add(info);
        frame.add(lower, BorderLayout.SOUTH);
        jmenuBar();


        frame.add(mainPanel,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }
    public static void clearFlie(String path){
        try { new FileWriter(path).close();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void showReports()
    {
        dialogReport=new JDialog();
        dialogReport.setTitle("REPORTS");
        JPanel panelReport=new JPanel();
        dialogReport.setSize(800,800);
        panelReport.setSize(800,800);
        panelReport.setBackground(Color.WHITE);
        ArrayList<JLabel> labels=new ArrayList<>();
        ArrayList<String> lines=BigBrother.getInstance().getMoked().getReports();
        Scanner scanner= null;
        String isApproved;
        try {
            scanner = new Scanner(new File(BigBrother.getInstance().getMoked().getPath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scanner.hasNextLine())
        {
            String reportTemp=scanner.nextLine();
            isApproved=(lines.contains(reportTemp))? "Not approved":"Approved";
            JLabel x=new JLabel(reportTemp+" - "+isApproved+"\n");
            labels.add(x);
        }

        for (JLabel label:labels) {
            panelReport.add(label);
        }
        dialogReport.add(panelReport);
        dialogReport.setVisible(true);
    }





    /**
     * Builds the top menu
     */
    public void jmenuBar()
    {
        JMenuBar jMenuBar=new JMenuBar();
        //****************************file***********************
        JMenu file=new JMenu("File");
        JMenuItem exit=new JMenuItem("exit");
        file.add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(driving==null){System.exit(0);}
                driving.getState().exit();
            }
        });

        //****************************background***********************
        JMenu background =new JMenu("Background");
        JMenuItem blue1=new JMenuItem("Blue");
        background.add(blue1);
        blue1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainPanel.setBackground(Color.BLUE);
                mainPanel.repaint();


            }
        });
        JMenuItem none=new JMenuItem("None");
        background.add(none);
        none.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                mainPanel.setBackground(Color.WHITE);
                mainPanel.repaint();
                //mainPanel.paintComponent(mainPanel.getGraphics());
            }
        });

        //****************************vehicleColor***********************
        JMenu vehicleColor=new JMenu("Vehicle color");
        JMenuItem magenta=new JMenuItem("Magenta");
        JMenuItem blue=new JMenuItem("Blue");
        JMenuItem orange=new JMenuItem("Orange");
        JMenuItem random=new JMenuItem("Random");


        magenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for(Vehicle v:driving.getVehicles())
                    v.setColor_car(Color.magenta);
                mainPanel.repaint();

            }
        });

        orange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for(Vehicle v:driving.getVehicles())
                    v.setColor_car(Color.orange);
                mainPanel.repaint();

            }
        });
        blue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               for(Vehicle v:driving.getVehicles())
                    v.setColor_car(Color.BLUE);
                mainPanel.repaint();
            }
        });
        random.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Color[] colors={Color.YELLOW,Color.green,Color.pink,Color.RED,Color.cyan,Color.LIGHT_GRAY};
                for(Vehicle v:driving.getVehicles())
                    v.setColor_car(colors[driving.getRandomInt(0,colors.length)]);
                mainPanel.repaint();

            }
        });

        vehicleColor.add(blue);
        vehicleColor.add(magenta);
        vehicleColor.add(orange);
        vehicleColor.add(random);
        //****************************help***********************
        JMenu help=new JMenu("Help");
        JMenuItem help1=new JMenuItem("help");
        help.add(help1);
        help1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(help,"Home Work 3\n" + "GUI @ Threads");
            }
        });
        //**************************map a Build*************************
        JMenu mapBuild =new JMenu("map a Build");
        JMenuItem mapCity=new JMenuItem("City");
        JMenuItem mapCountry=new JMenuItem("Country");
        mapBuild.add(mapCity);
        mapBuild.add(mapCountry);
        mapCity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                isStop=true;
                JDialog frame=new JDialog();
                frame.setSize(600,300);

                GridLayout myGridLayout = new GridLayout(1,2);
                JPanel lower=new JPanel();
                lower.setLayout(myGridLayout);
                JButton ok=new JButton("OK");
                JButton cancel=new JButton("cancel");
                lower.add(ok);
                lower.add(cancel);

                JLabel num_Vehicle=new JLabel("number of Vehicle:");
                num_Vehicle.setHorizontalAlignment(JLabel.CENTER);

                JSlider vehicles=new JSlider(JSlider.HORIZONTAL,0,50,10);
                vehicles.setMajorTickSpacing(10);
                vehicles.setMinorTickSpacing(1);
                vehicles.setPaintTicks(true);
                vehicles.setPaintLabels(true);


                JPanel upper=new JPanel();
                GridLayout GridLayout2 = new GridLayout(2,1);
                upper.setLayout(GridLayout2);
                upper.add(num_Vehicle,BorderLayout.CENTER);
                upper.add(vehicles);
                cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent)
                    {
                        frame.dispose();
                    }
                });
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        BigBrother.setReportId(1);
                        clearFlie("C:\\Users\\razie\\IdeaProjects\\HOMEWORK4\\reports.txt");

                        driving=new Driving();
                        MapBuilder mapBuilder=new CityBuilder(vehicles.getValue());
                        MapEngineer mapEngineer=new MapEngineer(mapBuilder);
                        mapEngineer.constructMap();
                        driving.setMap(mapEngineer.getMap());
                        DrawMap();
                        frame.dispose();
                    }
                });

                frame.add(lower,BorderLayout.SOUTH);
                frame.add(upper,BorderLayout.NORTH);
                frame.setVisible(true);

            }

        });
        mapCountry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                isStop=true;
                JDialog frame=new JDialog();
                frame.setSize(600,300);

                GridLayout myGridLayout = new GridLayout(1,2);
                JPanel lower=new JPanel();
                lower.setLayout(myGridLayout);
                JButton ok=new JButton("OK");
                JButton cancel=new JButton("cancel");
                lower.add(ok);
                lower.add(cancel);

                JLabel num_Vehicle=new JLabel("number of Vehicle:");
                num_Vehicle.setHorizontalAlignment(JLabel.CENTER);

                JSlider vehicles=new JSlider(JSlider.HORIZONTAL,0,50,10);
                vehicles.setMajorTickSpacing(10);
                vehicles.setMinorTickSpacing(1);
                vehicles.setPaintTicks(true);
                vehicles.setPaintLabels(true);


                JPanel upper=new JPanel();
                GridLayout GridLayout2 = new GridLayout(2,1);
                upper.setLayout(GridLayout2);
                upper.add(num_Vehicle,BorderLayout.CENTER);
                upper.add(vehicles);
                cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent)
                    {
                        frame.dispose();
                    }
                });
                ok.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        BigBrother.setReportId(1);
                        clearFlie("C:\\Users\\razie\\IdeaProjects\\HOMEWORK4\\reports.txt");
                        driving=new Driving();
                        MapBuilder mapBuilder=new CountryBuilder(vehicles.getValue());
                        MapEngineer mapEngineer=new MapEngineer(mapBuilder);
                        mapEngineer.constructMap();
                        driving.setMap(mapEngineer.getMap());
                        DrawMap();
                        frame.dispose();
                    }
                });

                frame.add(lower,BorderLayout.SOUTH);
                frame.add(upper,BorderLayout.NORTH);
                frame.setVisible(true);

            }
        });

        //***************************Clone a car***********************
        JMenu cloneCar =new JMenu("Clone a car");
        JMenuItem choiceId=new JMenuItem("choice Id");
        cloneCar.add(choiceId);
        choiceId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(driving==null){
                    JOptionPane.showMessageDialog(cloneCar,"Unable to copy vehicle\n" + "There are no vehicles in the system");
                    return;
                }
                JDialog frame=new JDialog();
                frame.setSize(600,300);

                GridLayout myGridLayout = new GridLayout(1,2);
                JPanel lower=new JPanel();
                lower.setLayout(myGridLayout);
                JButton ok=new JButton("OK");
                JButton cancel=new JButton("cancel");
                lower.add(ok);
                lower.add(cancel);

                JLabel choiceIdVeh=new JLabel("Choosing a vehicle number to copy:");
                choiceIdVeh.setHorizontalAlignment(JLabel.CENTER);

                JSlider vehicleId=new JSlider(JSlider.HORIZONTAL,1,driving.getVehicles().size(),1);
                vehicleId.setMajorTickSpacing(1);
                vehicleId.setMinorTickSpacing(1);
                vehicleId.setPaintTicks(true);
                vehicleId.setPaintLabels(true);


                JPanel upper=new JPanel();
                GridLayout GridLayout2 = new GridLayout(2,1);
                upper.setLayout(GridLayout2);
                upper.add(choiceIdVeh,BorderLayout.CENTER);
                upper.add(vehicleId);
                cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent)
                    {
                        frame.dispose();
                    }
                });
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        Vehicle newVeh=driving.getVehicles().get(vehicleId.getValue()-1);
                        Workshop workshop=new Workshop();
                        Road temp;
                        while(true) {
                            temp = driving.getMap().getRoads().get(getRandomInt(0, driving.getMap().getRoads().size()));//random road from the map
                            if (temp.getEnabled())
                                break;
                        }
                        newVeh=workshop.makeCar(newVeh,temp);
                        driving.getVehicles().add(newVeh);
                        driving.getAllTimedElements().add(newVeh);
                        new  Thread(newVeh).start();
                        mainPanel.repaint();
                        frame.dispose();


                    }
                });

                frame.add(lower,BorderLayout.SOUTH);
                frame.add(upper,BorderLayout.NORTH);
                frame.setVisible(true);









            }
        });


        //****************************Report***********************
        JMenu reports=new JMenu("Reports");
        JMenuItem getReports= new JMenuItem ("Reports");
        reports.add(getReports);
        getReports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                    showReports();
            }
        });




        //****************************Add item***********************
        jMenuBar.add(file);
        jMenuBar.add(background);
        jMenuBar.add(vehicleColor);
        jMenuBar.add(mapBuild);
        jMenuBar.add(cloneCar);
        jMenuBar.add(reports);
        jMenuBar.add(help);

        frame.add(jMenuBar,BorderLayout.NORTH);

    }

    /**
     *
     * @param driving
     */
    public void setDriving(Driving driving) { this.driving = driving; }

    public  void DrawMap( ){
        mainPanel.setDriving(driving);
        mainPanel.repaint();
       // mainPanel.paintComponent(mainPanel.getGraphics());
    }

    /**
     * Builds the data table
     */
    public void drawTable(){
        if(driving==null){return;}
        String[] colNames = {"Vehicle #", "Type", "Location", "Time on loc", "speed"};
        int num_Vehicle=driving.getVehicles().size();
        String data[][]= new String[num_Vehicle][];
        for(int i=0;i<num_Vehicle;i++){
            Vehicle v=driving.getVehicles().get(i);
            String location,speed;
            if(v.getCurrentRoutePart() instanceof Junction) {
                location = "Junction "+((Junction) v.getCurrentRoutePart()).getJunctionName();
                speed="0";
            }
            else {
                location = "Road " + v.getLastRoad().getStartJunction().getJunctionName() + "-" + v.getLastRoad().getEndJunction().getJunctionName();
                speed=""+v.getVehicleType().getAverageSpeed();
            }
            data[i]=new String[]{"" + v.getId(), "" + v.getVehicleType(), location, "" + v.getTimeOnCurrentPart(), speed};

        }
        JTable table=new JTable(data,colNames);
        table.setBounds(0,580,300,400);
        //table.setPreferredScrollableViewportSize(new Dimension(50,500));
        table.setFillsViewportHeight(true);
        sp = new JScrollPane(table);
        sp.setBounds(0,0,400,100);
        mainPanel.add(sp);
        isTable=true;
        sp.repaint();


    }
}
