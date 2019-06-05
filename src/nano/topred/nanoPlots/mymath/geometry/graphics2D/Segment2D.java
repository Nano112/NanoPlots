package nano.topred.nanoPlots.mymath.geometry.graphics2D;

import nano.topred.nanoPlots.mymath.geometry.graphics3D.Point3D;
import nano.topred.nanoPlots.Position;

import java.util.ArrayList;
import java.util.UUID;

public class Segment2D {
    private Point2D p1;
    private Point2D p2;
    private ArrayList<Point2D> points;
    private double slope;
    private double yIntercept;
    private double length;

    public Segment2D(Point2D p1, Point2D p2)
    {
        this.p1 = p1;
        this.p2 = p2;
        this.slope = calcSlope();
        this.yIntercept = calcYIntercept();
        this.length = calcLenght();
        this.points = bresenham(this);
    }

    public double calcLenght()
    {
        double deltaX = this.p2.getX()-this.p1.getX();
        double deltaY = this.p2.getY()-this.p1.getY();
        return(Math.sqrt(deltaX*deltaX+deltaY*deltaY));
    }

    public double calcSlope()
    {
        double deltaX = this.p2.getX()-this.p1.getX();
        double deltaY = this.p2.getY()-this.p1.getY();
        return deltaY/deltaX;
    }

    public double calcYIntercept()
    {
        double Y = this.p1.getY();
        double X = this.p1.getX();
        return Y - this.slope*X;
    }

    public static int orientation(Point2D p, Point2D q, Point2D r) {
        double val = (q.getY() - p.getY()) * (r.getX() - q.getX())
                - (q.getX() - p.getX()) * (r.getY() - q.getY());

        if (val == 0.0)
            return 0; // colinear
        return (val > 0) ? 1 : 2; // clock or counterclock wise
    }

    public static boolean intersect(Segment2D s1, Segment2D s2) {
        Point2D p1 = s1.getP1();
        Point2D p2 = s1.getP2();
        Point2D q1 = s2.getP1();
        Point2D q2 = s2.getP2();
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        if (o1 != o2 && o3 != o4)
            return true;

        return false;
    }



    public ArrayList<Position> toPositions(double y, UUID worldID)
    {
        ArrayList<Position> positions = new ArrayList<>();
        for (Point2D p2D: this.points)
            positions.add(new Position(new Point3D(p2D.getX(), y, p2D.getY()),worldID));
        return positions;
    }

    public static ArrayList<Point2D> bresenham(Segment2D segment2D)
    {
        Point2D pos0 = segment2D.p1;
        Point2D pos1 = segment2D.p2;
        return Point2D.bresenham(pos0,pos1);
    }

    public Line2D getLine()
    {
        return new Line2D(this.slope,this.yIntercept);
    }

    public Point2D getP1() {
        return p1;
    }

    public void setP1(Point2D p1) {
        this.p1 = p1;
    }

    public Point2D getP2() {
        return p2;
    }

    public void setP2(Point2D p2) {
        this.p2 = p2;
    }

    public double getSlope()
    {
        return this.slope;
    }

    public void setSlope(double s)
    {
        this.slope = s;
    }

    public double getyIntercept() {
        return yIntercept;
    }

    public void setyIntercept(double yIntercept) {
        this.yIntercept = yIntercept;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public ArrayList<Point2D> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point2D> points) {
        this.points = points;
    }
}
