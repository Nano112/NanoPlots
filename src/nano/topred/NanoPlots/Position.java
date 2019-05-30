package nano.topred.NanoPlots;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.UUID;

public class Position
{
    double x;
    double y;
    double z;
    UUID world;

    public Position(Location l){
        x=l.getX();
        y=l.getY();
        z=l.getZ();
        world=l.getWorld().getUID();
    }
    public Location getLocation()
    {
        return new Location(this.getWorld(),this.x,this.y,this.z);
    }
    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getZ()
    {
        return z;
    }

    public World getWorld()
    {
        return Bukkit.getWorld(world);
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public void setZ(double z)
    {
        this.z = z;
    }

    public void setWorld(World world)
    {
        this.world = world.getUID();
    }
}
