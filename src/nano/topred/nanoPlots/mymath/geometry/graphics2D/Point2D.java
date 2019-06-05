package nano.topred.nanoPlots.mymath.geometry.graphics2D;

import nano.topred.nanoPlots.mymath.geometry.graphics3D.Point3D;
import nano.topred.nanoPlots.Position;

import java.util.ArrayList;
import java.util.UUID;

import static nano.topred.nanoPlots.mymath.Mean.mean;

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

    public static ArrayList<Point2D> bresenham(Point2D pos0, Point2D pos1)
    {

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
