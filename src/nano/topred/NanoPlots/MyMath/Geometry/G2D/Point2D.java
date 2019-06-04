package nano.topred.NanoPlots.MyMath.Geometry.G2D;

import nano.topred.NanoPlots.MyMath.Geometry.G3D.Point3D;
import nano.topred.NanoPlots.Position;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import static nano.topred.NanoPlots.MyMath.Mean.mean;

public class Point2D {
    private double x;
    private double y;

    public Point2D(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public static Point2D midPoint(Point2D a, Point2D b)
    {
        return new Point2D(mean(a.getX(),b.getX()),mean(a.getY(),b.getY()));
    }

    public Position toWorldPosition(double y, UUID worldId)
    {
        return new Position(new Point3D(this.getX(),y,this.getY()),worldId);
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }


}
