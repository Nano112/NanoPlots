package nano.topred.nanoPlots.mymath.geometry.graphics2D;

public class Line2D {
    private double slope;
    private double yIntercept;

    public Line2D(double m, double b)
    {
        this.slope=m;
        this.yIntercept=b;
    }
    public double getSlope() {
        return slope;
    }

    public void setSlope(double slope) {
        this.slope = slope;
    }

    public double getyIntercept() {
        return yIntercept;
    }

    public void setyIntercept(double yIntercept) {
        this.yIntercept = yIntercept;
    }
}
