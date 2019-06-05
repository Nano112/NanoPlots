package nano.topred.nanoPlots.mymath.geometry.graphics2D;

import nano.topred.nanoPlots.Position;

import java.util.ArrayList;
import java.util.UUID;

public class Rectangle2D {
    //c0----c1
    //--------
    //--------
    //--------
    //c3----c2
    private Point2D corner0;
    private Point2D corner1;
    private Point2D corner2;
    private Point2D corner3;
    private Segment2D edge0;
    private Segment2D edge1;
    private Segment2D edge2;
    private Segment2D edge3;
    private Point2D center;
    private double minX;
    private double maxX;
    private double minY;
    private double maxY;
    private double width;
    private double height;
    private double surface;
    private double perimetre;

    public Rectangle2D(Point2D p0, Point2D p1, Point2D p2, Point2D p3)
    {
        this.corner0 = p0;
        this.corner1 = p1;
        this.corner2 = p2;
        this.corner3 = p3;

        recalc();
    }

    public Rectangle2D(Point2D p, double width, double height)
    {
        this.corner0 = p;
        this.width = width;
        this.height = height;
        this.corner1 = new Point2D(p.getX() + width,p.getY());
        this.corner2 = new Point2D(p.getX() + width,p.getY() + height);
        this.corner3 = new Point2D(p.getX(),p.getY() + height);
        recalc();
    }

    public void recalc()
    {
        this.center = Point2D.midPoint(this.corner0, this.corner2);
        this.surface = this.height * this.width;
        this.perimetre = this.height * 2 + this.width * 2;
        this.minX = this.corner0.getX();
        this.minY = this.corner0.getY();
        this.maxX = this.corner2.getX();
        this.maxY = this.corner2.getY();
        this.width = this.corner1.getX() - this.corner0.getX();
        this.height = this.corner3.getY() - this.corner0.getY();
        calcEdges();
    }



    public boolean isInside(Point2D p)
    {
        double x = p.getX();
        double y = p.getY();
        if(x >= this.minX && x <= this.maxX && y >= this.minY && y <= this.maxY)
            return true;
        return false;
    }

    public void calcEdges() {
        this.edge0 = new Segment2D(this.corner0,this.corner1);
        this.edge1 = new Segment2D(this.corner1,this.corner2);
        this.edge2 = new Segment2D(this.corner2,this.corner3);
        this.edge3 = new Segment2D(this.corner3,this.corner0);
    }

    public ArrayList<Position> toPositions(double y, UUID worldId)
    {

        ArrayList<Position> positions = new ArrayList<>();
        if(edge0== null)
            return positions;
        positions.addAll(this.edge0.toPositions(y,worldId));
        positions.addAll(this.edge1.toPositions(y,worldId));
        positions.addAll(this.edge2.toPositions(y,worldId));
        positions.addAll(this.edge3.toPositions(y,worldId));
        return positions;
    }


    public Point2D getCorner0() {
        return corner0;
    }

    public void setCorner0(Point2D corner0) {
        this.corner0 = corner0;
        recalc();
    }

    public Point2D getCorner1() {
        return corner1;
    }

    public void setCorner1(Point2D corner1) {
        this.corner1 = corner1;
        recalc();
    }

    public Point2D getCorner2() {
        return corner2;
    }

    public void setCorner2(Point2D corner2) {
        this.corner2 = corner2;
        recalc();
    }

    public Point2D getCorner3() {
        return corner3;
    }

    public void setCorner3(Point2D corner3) {
        this.corner3 = corner3;
        recalc();
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
        recalc();
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
        recalc();
    }

    public Point2D getCenter() {
        return center;
    }


    public double getSurface() {
        return surface;
    }


    public double getPerimetre() {
        return perimetre;
    }

    public double getMinX() {
        return minX;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMaxY() {
        return maxY;
    }

    public Segment2D getEdge0() {
        return edge0;
    }

    public Segment2D getEdge1() {
        return edge1;
    }

    public Segment2D getEdge2() {
        return edge2;
    }

    public Segment2D getEdge3() {
        return edge3;
    }


}
