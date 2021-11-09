package Inspection;

import components.Vehicle;
import java.io.IOException;
import java.util.Date;
import java.util.Observer;
import java.util.Observable;

public class BigBrother implements Observer {
    private static volatile BigBrother bigBrother=null;
    private static int reportId=1;
    private Moked moked;
    private BigBrother()  { moked=new Moked("C:\\Users\\razie\\IdeaProjects\\HOMEWORK4\\reports.txt");}
    public static BigBrother getInstance()  {
        if(bigBrother==null)
            synchronized (BigBrother.class)
            {
                if (bigBrother == null)
                    bigBrother = new BigBrother();
            }


        return bigBrother;
    }

    public static int getReportId() {
        return reportId;
    }

    public Moked getMoked() {
        return moked;
    }


    public static void setReportId(int reportId) {
        BigBrother.reportId = reportId;
    }

    @Override
    public void update(Observable observable, Object o) {
        Date now=new Date();
        Vehicle v=(Vehicle)observable;
        double speed_car=v.getVehicleType().getAverageSpeed()*10*(v.getRandomDouble(1,1.5));

        double speed_Road=v.getLastRoad().getMaxSpeed();
        if (speed_car>speed_Road)
        {
            String str="Number car: "+v.getId()+" Number report: "+(reportId++) +" Date: "+ now;
            System.out.println("Number car: "+v.getId()+"get report"+now);
            try {
                moked.Report(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
