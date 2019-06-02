package nano.topred.NanoPlots.MyMath;

public class Rounding {

    public double signedFloor(double a)
    {
        if(a>0)
            return Math.floor(a);
        return Math.ceil(a);
    }

    public double signedCeil(double a)
    {
        if(a>0)
            return Math.ceil(a);
        return Math.floor(a);
    }

}
