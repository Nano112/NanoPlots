package nano.topred.nanoplots.plots;

import nano.topred.nanoplots.mymath.geometry.graphics3D.Point3D;
import nano.topred.nanoplots.Position;
import org.bukkit.Location;

public class SubPlot {

    private int width;//longeur du plot en X

    private int height;//longueur du plot en Z

    private Position corner;

    public SubPlot(int width, int height, Location corner)
    {
        this.width = width;
        this.height = height;
        this.corner = new Position(corner);
    }

    public SubPlot(int width, int height, Position corner)
    {
        this.width = width;
        this.height = height;
        this.corner = corner;
    }

    public Position getMaxPosition()
    {
        double Xmax = this.corner.getX()+width;
        double Zmax = this.corner.getZ()+height;
        return new Position(new Point3D(Xmax, this.corner.getY(), Zmax), this.corner.getWorld());
    }

    public Position getMinPosition()
    {
        double Xmin = this.corner.getX();
        double Zmin = this.corner.getZ();
        return new Position(new Point3D(Xmin, this.corner.getY(), Zmin), this.corner.getWorld());
    }

    public boolean isInSubPlot(Position p)
    {
        if (p.getX() >= corner.getX() && p.getX() <= corner.getX() + width)
        {
            return p.getZ() >= corner.getZ() && p.getZ() <= corner.getZ() + height;
        }
        return false;
    }

    public Position getCorner() {
        return corner;
    }

    public void setCorner(Position corner) {
        this.corner = corner;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
