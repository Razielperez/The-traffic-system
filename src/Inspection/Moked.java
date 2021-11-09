package Inspection;

import components.Vehicle;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import  java.util.concurrent.locks.ReentrantReadWriteLock;
public class Moked {
    private ReentrantReadWriteLock lock=new ReentrantReadWriteLock();

    private PrintWriter bw;
    private String path;
    private ArrayList<String> reports;
    public Moked(String text)   {path=text; reports=new ArrayList<>(); }

    public  void  Report(String report) throws IOException {

        bw=new PrintWriter(new FileWriter(path,true));
        lock.writeLock().lock();
        bw.println(report);
        reports.add(report);
        bw.close();
        lock.writeLock().unlock();
        bw.close();


    }

    public String getPath() { return path; }

    public ArrayList<String> getReports() {
        return reports;
    }


    public   void ReportApproval(Vehicle vehicle){

        lock.readLock().lock();
        Scanner scanner= null;
        try {
            scanner = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scanner.hasNextLine()) {

            String reportTemp=scanner.nextLine();
            String[] line=reportTemp.split(" ");
            if(line[2].equalsIgnoreCase(vehicle.getId()+"")&&reports.contains(reportTemp)){
                reports.remove(reportTemp);
                System.out.println("Report number "+line[5]+" payed by driver number "+vehicle.getId());
                break;
            }


        }
        lock.readLock().unlock();

    }


}
