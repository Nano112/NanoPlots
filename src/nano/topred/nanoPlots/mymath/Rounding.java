package nano.topred.nanoPlots.mymath;

public class Rounding {

    public static double signedFloor(double a)
    {
        if(a>0)
            return Math.floor(a);
        return Math.ceil(a);
    }

    public static double signedCeil(double a)
    {
        if(a>0)
            return Math.ceil(a);
        return Math.floor(a);
    }

}
