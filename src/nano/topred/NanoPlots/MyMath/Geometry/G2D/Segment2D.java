package nano.topred.NanoPlots.MyMath.Geometry.G2D;

import nano.topred.NanoPlots.MyMath.Geometry.G3D.Point3D;
import nano.topred.NanoPlots.Position;

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



    public ArrayList<Position> toWorldPositions(double y, UUID worldID)
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
        int x1 = (int)Math.floor(pos0.getX());
        int x2 = (int)Math.floor(pos1.getX());
        int y1 = (int)Math.floor(pos0.getY());
        int y2 = (int)Math.floor(pos1.getY());
        ArrayList<Point2D> edge = new ArrayList<>();
        int d = 0;

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int dx2 = 2 * dx; // slope scaling factors to
        int dy2 = 2 * dy; // avoid floating point

        int ix = x1 < x2 ? 1 : -1; // increment direction
        int iy = y1 < y2 ? 1 : -1;

        int x = x1;
        int y = y1;

        if (dx >= dy) {
            while (true) {
                edge.add(new Point2D(x,y));
                if (x == x2)
                    break;
                x += ix;
                d += dy2;
                if (d > dx) {
                    y += iy;
                    d -= dx2;
                }
            }
        } else {
            while (true) {
                edge.add(new Point2D(x,y));
                if (y == y2)
                    break;
                y += iy;
                d += dx2;
                if (d > dy) {
                    x += ix;
                    d -= dy2;
                }
            }
        }
        return edge;
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
