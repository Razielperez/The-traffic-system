/**
 * raziel perez 316134956
 */
package Gui;

import components.*;

import javax.swing.*;
import java.awt.*;

public class myPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private Driving driving;
    public myPanel() {super();}

    /**
     *
     * @param driving
     */
    public void setDriving(Driving driving) { this.driving = driving; }

    /**
     * Color the component according to the data in a driving variable
     * @param g
     */
    public  void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(driving==null){ return;}

        for (Road x : driving.getMap().getRoads()) {
            if(x.getEnabled()){
                int x1 = (int) x.getStartJunction().getX();
                int y1 = (int) x.getStartJunction().getY();
                int x2 = (int) x.getEndJunction().getX();
                int y2 = (int) x.getEndJunction().getY();


                g.setColor(Color.black);
                g.drawLine(x1, y1, x2, y2);
            }
        }
        for(Junction j: driving.getMap().getJunctions())
        {
            int x=(int)j.getX();
            int y=(int)j.getY();

            if(j instanceof LightedJunction){
                if (((LightedJunction) j).getLights().getTrafficLightsOn()) {
                    Road green = ((LightedJunction) j).getLights().getRoads().get(((LightedJunction) j).getLights().getGreenLightIndex());
                    drawArrow(g, (int) green.getStartJunction().getX(), (int) green.getStartJunction().getY(),
                            (int) green.getEndJunction().getX(), (int) green.getEndJunction().getY(), 20, 5);
                    g.setColor(Color.RED);
                }
                else
                    g.setColor(Color.GREEN);
            }
            else
                g.setColor(Color.BLACK);
            g.fillOval(x-10,y-10,20,20);

        }

        for (Vehicle v:driving.getVehicles()){
            g.setColor(v.getColor_car());
            drawCar(g,v);

        }




    }

    /**
     * Draws the vehicle according to its location and its component
     * @param g
     * @param v
     */
    public void drawCar(Graphics g,Vehicle v)
    {
        int x1 = (int) v.getLastRoad().getStartJunction().getX();
        int y1 = (int) v.getLastRoad().getStartJunction().getY();
        int x2 = (int) v.getLastRoad().getEndJunction().getX();
        int y2 = (int) v.getLastRoad().getEndJunction().getY();
        int k;
        if(v.getCurrentRoutePart() instanceof Road) {
            k = v.getTimeOnCurrentPart();
            int l = (int) v.getLastRoad().calcEstimatedTime(v) - k;
            int xc = ((x1 * l) + (x2 * k)) / (k + l);
            int yc = ((y1 * l) + (y2 * k)) / (k + l);
            drawRotetedVehicle(g, xc, yc, x2, y2, 10, 4);
        }
        else {
            int[] xpoints = {(int) x2- 5, (int) x2+5,  (int) x2+5, (int) x2-5};
            int[] ypoints = {(int) y2+2, (int) y2+2, (int) y2-2, (int) y2-2};
            g.fillPolygon(xpoints, ypoints, 4);
            g.setColor(Color.BLACK);
            g.fillOval((int) x2-5-2,(int) y2,4,4);
            g.fillOval((int) x2+5-2,(int) y2,4,4);
            g.fillOval((int) x2-5-2,(int) y2-2-2,4,4);
            g.fillOval((int) x2+5-2,(int) y2-2-2,4,4);

        }







    }

    private void drawRotetedVehicle(Graphics g, int x1, int y1, int x2, int y2, int d, int h){

        int dx = x2 - x1, dy = y2 - y1, delta = 10;
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = delta, xn = xm, ym = h, yn = -h, x;
        double xm1 = delta + d, xn1 = xm1, ym1 = h, yn1 = -h, xx;
        double sin = dy / D, cos = dx / D;
        x = xm*cos - ym*sin + x1;
        xx = xm1*cos - ym1*sin + x1;
        ym = xm*sin + ym*cos + y1;
        ym1 = xm1*sin + ym1*cos + y1;
        xm = x;
        xm1 = xx;
        x = xn*cos - yn*sin + x1;
        xx = xn1*cos - yn1*sin + x1;
        yn = xn*sin + yn*cos + y1;
        yn1 = xn1*sin + yn1*cos + y1;
        xn = x;
        xn1 = xx;
        int[] xpoints = {(int) xm1, (int) xn1,  (int) xn, (int) xm};
        int[] ypoints = {(int) ym1, (int) yn1, (int) yn, (int) ym};
        g.fillPolygon(xpoints, ypoints, 4);
        g.setColor(Color.BLACK);
        g.fillOval((int) xm1-2,(int) ym1-2,4,4);
        g.fillOval((int) xn1-2,(int) yn1-2,4,4);
        g.fillOval((int) xm-2,(int) ym-2,4,4);
        g.fillOval((int) xn-2,(int) yn-2,4,4);

    }

    /**
     * Draws a green triangle on an open road at the junction with a traffic light
     * @param g
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param d
     * @param h
     */
    private void drawArrow(Graphics g, int x1, int y1, int x2, int y2, int d, int h)
    {
        int dx = x2 - x1, dy = y2- y1;
        double D = Math.sqrt(dx*dx +dy*dy);
        double xm = D - d, xn = xm,ym = h,yn = -h,x;
        double sin = dy / D, cos = dx / D;
        x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;
        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;
        int[] xpoints = {x2,(int)xm,(int)xn};
        int[] ypoints = {y2,(int)ym,(int)yn};
        g.setColor(Color.GREEN);
        g.fillPolygon(xpoints,ypoints,3);

    }


}
