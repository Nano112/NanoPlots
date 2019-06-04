package nano.topred.NanoPlots.MyMath.Geometry.G2D;

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
        this.width = this.corner1.getX() - this.corner0.getX();
        this.height = this.corner3.getY() - this.corner0.getY();
        this.center = Point2D.midPoint(this.corner0, this.corner2);
        this.surface = this.height * this.width;
        this.perimetre = this.height * 2 + this.width * 2;
        this.minX = this.corner0.getX();
        this.minY = this.corner0.getY();
        this.maxX = this.corner2.getX();
        this.maxY = this.corner2.getY();
    }

    public Rectangle2D(Point2D p, double width, double height)
    {
        this.corner0 = p;
        this.width = width;
        this.height = height;
        this.corner1 = new Point2D(p.getX() + width,p.getY());
        this.corner2 = new Point2D(p.getX() + width,p.getY() + height);
        this.corner3 = new Point2D(p.getX(),p.getY() + height);
        this.center = Point2D.midPoint(this.corner0, this.corner2);
        this.surface = this.height * this.width;
        this.perimetre = this.height * 2 + this.width * 2;
        this.minX = this.corner0.getX();
        this.minY = this.corner0.getY();
        this.maxX = this.corner2.getX();
        this.maxY = this.corner2.getY();
    }

    public boolean isInside(Point2D p)
    {
        double x = p.getX();
        double y = p.getY();
        if(x >= this.minX && x <= this.maxX && y >= this.minY && y <= this.maxY)
            return true;
        return false;
    }

    public Point2D getCorner0() {
        return corner0;
    }

    public void setCorner0(Point2D corner0) {
        this.corner0 = corner0;
    }

    public Point2D getCorner1() {
        return corner1;
    }

    public void setCorner1(Point2D corner1) {
        this.corner1 = corner1;
    }

    public Point2D getCorner2() {
        return corner2;
    }

    public void setCorner2(Point2D corner2) {
        this.corner2 = corner2;
    }

    public Point2D getCorner3() {
        return corner3;
    }

    public void setCorner3(Point2D corner3) {
        this.corner3 = corner3;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Point2D getCenter() {
        return center;
    }

    public void setCenter(Point2D center) {
        this.center = center;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public double getPerimetre() {
        return perimetre;
    }

    public void setPerimetre(double perimetre) {
        this.perimetre = perimetre;
    }

    public double getMinX() {
        return minX;
    }

    public void setMinX(double minX) {
        this.minX = minX;
    }

    public double getMaxX() {
        return maxX;
    }

    public void setMaxX(double maxX) {
        this.maxX = maxX;
    }

    public double getMinY() {
        return minY;
    }

    public void setMinY(double minY) {
        this.minY = minY;
    }

    public double getMaxY() {
        return maxY;
    }

    public void setMaxY(double maxY) {
        this.maxY = maxY;
    }
}
