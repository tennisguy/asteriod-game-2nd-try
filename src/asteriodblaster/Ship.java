package asteriodblaster;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

public class Ship
{
    public Ship(int scaleFactor)
    {
        for (int i = 0; i < shipXpoints.length; i++)
        {
            shipXpoints[i] /= scaleFactor;
            shipYpoints[i] /= scaleFactor;
        }
    }
    // Ship defines the ship and the exhaust ports
    int[] shipXpoints =
    {
        100, 200, 220, 200, 100, 0, -100, -200, -220, -200, -100,
        -40, -40, -10, -10, 10, 10, 40, 40
    };
    int[] shipYpoints =
    {
        -20, -20, 0, 20, 20, 100, 20, 20, 0, -20, -20,
        -80, -120, -120, -80, -80, -120, -120, -80
    };
    int[] exhaust1Xpoints =
    {
        -40, -25, -10
    };
    int[] exhaust1Ypoints =
    {
        -120, -180, -120
    };
    int[] exhaust2Xpoints =
    {
        10, 25, 40
    };
    int[] exhaust2Ypoints =
    {
        -120, -180, -120
    };
    int deltaX;
    int deltaY;
    int rotateAmount = 180;
    int shipHeading = 0;
    int shipCourse = 0;
    private AffineTransform motherShipAT;
    int shipDeltaX = 0;
    int shipDeltaY = 0;
    int shipX = 900;
    int shipY = 500;
    double shipSpeed = 1;
    Area shipArea;
    Polygon shipShape = new Polygon(shipXpoints, shipYpoints,
            shipXpoints.length);
    Polygon exhaust1Shape = new Polygon(exhaust1Xpoints,
            exhaust1Ypoints, exhaust1Xpoints.length);
    Polygon exhaust2Shape = new Polygon(exhaust2Xpoints,
            exhaust2Ypoints, exhaust2Xpoints.length);

    public void paintSelf(Graphics2D g2)
    {
        shipArea = new Area(shipShape);
        deltaX = (int) (Math.sin(Math.toRadians(shipCourse)) * shipSpeed);
        deltaY = (int) (Math.cos(Math.toRadians(shipCourse)) * -shipSpeed);
        //System.out.println("shipData shipCourse = " + shipCourse);
        // trick:  we move the screen, not the ship!
        shipX = shipX + deltaX;
        shipY = shipY + deltaY;

        g2.translate(shipX, shipY);

        //g2.rotate(Math.toRadians(rotateAmount))e
        //rotateAmount = rotateAmount + 1;

        // draw ship
        g2.setStroke(new BasicStroke(.02f));
        g2.setColor(Color.BLUE);
        g2.setColor(Color.GREEN);
        g2.draw(shipArea);

        //g2.fillPolygon(shipShape);


        // draw exhaust1 and exhaust2
        g2.setStroke(new BasicStroke(.02f));
        g2.setColor(Color.RED);
        // draw exhaust1
        g2.draw(exhaust1Shape);
        g2.fillPolygon(exhaust1Shape);

        // draw exhaust2
        g2.draw(exhaust2Shape);
        g2.fillPolygon(exhaust2Shape);

        g2.scale(.25, .25);

    }

    public void setShipCourse(int shipCourseLuke)
    {
        shipCourse = shipCourseLuke;
        System.out.println("set shipCourse = " + shipCourse);

    }

    public void setShipSpeed(double shipSpeedLuke)
    {
        shipSpeed = shipSpeedLuke;
        System.out.println("shipSpeed = " + shipSpeed);
    }
}