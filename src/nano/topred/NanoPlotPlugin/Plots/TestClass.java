package nano.topred.NanoPlotPlugin.Plots;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TestClass
{
    private int a ;
    private String s ;
    private Location location;
    public TestClass(Location l)
    {
        a=12;
        s="hellow";
        location=l;
    }
    public void setA(int a)
    {
        this.a = a;
    }

    public void setS(String s)
    {
        this.s = s;
    }

    public int getA()
    {
        return a;
    }

    public String getS()
    {
        return s;
    }

    public void setLocation(Location location)
    {
        this.location = location;
    }

    public Location getLocation()
    {
        return location;
    }
}
