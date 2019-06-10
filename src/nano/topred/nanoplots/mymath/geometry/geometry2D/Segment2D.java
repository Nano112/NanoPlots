package nano.topred.nanoplots.mymath.geometry.geometry2D;

import nano.topred.nanoplots.mymath.geometry.geometry3D.Point3D;
import nano.topred.nanoplots.mymath.Position;

import java.util.ArrayList;
import java.util.UUID;

import static nano.topred.nanoplots.mymath.geometry.geometry2D.Point2D.determinant;

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
        this.points = toPoint2D();
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



    public static boolean doBoundingBoxesIntersect(Segment2D a, Segment2D b) {
        Rectangle2D ra = new Rectangle2D(a);
        Rectangle2D rb = new Rectangle2D(b);
        return rb.isInside(ra);
    }

    public static final double EPSILON = 0.000001;

    public static boolean isPointOnLine(Segment2D a, Point2D b) {
        // Move the image, so that a.first is on (0|0)
        Segment2D aTmp = new Segment2D(new Point2D(0, 0), new Point2D(
                a.p2.getX() - a.p1.getX(), a.p2.getY() - a.p1.getY()));
        Point2D bTmp = new Point2D(b.getX() - a.p1.getX(), b.getY() - a.p1.getY());
        double r = determinant(aTmp.p2, bTmp);
        return Math.abs(r) < EPSILON;
    }

    public static boolean isPointRightOfLine(Segment2D a, Point2D b) {
        // Move the image, so that a.first is on (0|0)
        Segment2D aTmp = new Segment2D(new Point2D(0, 0), new Point2D(
                a.p2.getX() - a.p1.getX(), a.p2.getY() - a.p1.getY()));
        Point2D bTmp = new Point2D(b.getX() - a.p1.getX(), b.getY() - a.p1.getY());
        return determinant(aTmp.p2, bTmp) < 0;
    }




    public static boolean lineSegmentTouchesOrCrossesLine(Segment2D a,
                                                   Segment2D b) {
        return isPointOnLine(a, b.p1)
                || isPointOnLine(a, b.p2)
                || (isPointRightOfLine(a, b.p1) ^
                isPointRightOfLine(a, b.p2));
    }

    public static boolean intersect(Segment2D a, Segment2D b) {
        return doBoundingBoxesIntersect(a, b)
                && lineSegmentTouchesOrCrossesLine(a, b)
                && lineSegmentTouchesOrCrossesLine(b, a);
    }


    public static Segment2D switcharoo(Segment2D a)
    {
        return new Segment2D(a.p2, a.p1);
    }

    public String toString()
    {
        return "p1:"+this.p1.toString()+"  p2:"+this.p2.toString();
    }

    public ArrayList<Point2D> toPoint2D()
    {
        ArrayList<Point2D> points = new ArrayList<>();
        if (this.length>100000)
        {
            System.out.println("Segment to big to calculate");
            return points;
        }
        points = this.bresenham(this);
        return points;
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
