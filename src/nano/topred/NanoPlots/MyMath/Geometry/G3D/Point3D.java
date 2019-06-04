package nano.topred.NanoPlots.MyMath.Geometry.G3D;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Point3D
{
    private double x;
    private double y;
    private double z;

    public Point3D(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static ArrayList<Point3D> bresenham(Point3D point1, Point3D point2)
    {
        int x1 = (int)Math.floor(point1.getX());
        int y1 = (int)Math.floor(point1.getY());
        int z1 = (int)Math.floor(point1.getZ());
        int x2 = (int)Math.floor(point2.getX());
        int y2 = (int)Math.floor(point2.getY());
        int z2 = (int)Math.floor(point2.getZ());
        ArrayList<Point3D> line = new ArrayList<>();
        line.add(new Point3D(x1,y1,z1));
        int deltaX = Math.abs(x2-x1);
        int deltaY = Math.abs(y2-y1);
        int deltaZ = Math.abs(z2-z1);

        int xs = (x2 > x1)?1:-1;
        int ys = (y2 > y1)?1:-1;
        int zs = (z2 > z1)?1:-1;

        //axis X
        if(deltaX >= deltaY && deltaX >= deltaZ) {
            int p1 = 2 * deltaY - deltaX;
            int p2 = 2 * deltaZ - deltaX;
            while (x1 != x2) {
                x1 += xs;
                if (p1 >= 0) {
                    y1 += ys;
                    p1 -= 2 * deltaX;
                }
                if (p2 >= 0) {
                    z1 += zs;
                    p2 -= 2 * deltaX;
                }
                p1 += 2 * deltaY;
                p2 += 2 * deltaZ;
                line.add(new Point3D(x1, y1, z1));
            }
        }
        else if(deltaY >= deltaX && deltaY >= deltaZ) {
            int p1 = 2 * deltaX - deltaY;
            int p2 = 2 * deltaZ - deltaY;
            while (y1 != y2) {
                y1 += ys;
                if (p1 >= 0) {
                    x1 += xs;
                    p1 -= 2* deltaY;
                }
                if (p2 >= 0) {
                    z1 += zs;
                    p2 -= 2* deltaY;
                }
                p1 += 2 * deltaX;
                p2 += 2 * deltaZ;
                line.add(new Point3D(x1, y1, z1));
            }
        }
        else  {
            int p1 = 2 * deltaY - deltaZ;
            int p2 = 2 * deltaX - deltaZ;
            while (z1 != z2) {
                z1 += zs;
                if (p1 >= 0) {
                    y1 += ys;
                    p1 -= 2 * deltaZ;
                }
                if (p2 >= 0) {
                    x1 += xs;
                    p2 -= 2 * deltaZ;
                }
                p1 += 2 * deltaY;
                p2 += 2 * deltaX;
                line.add(new Point3D(x1, y1, z1));
            }
        }
        return line;
    }

    public String toString()
    {
        String string = ("X: " + this.x) + ("Y: " + this.y) + ("Z: " + this.z);
        return string;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
