package nano.topred.NanoPlots;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class Position
{
    Double x;
    Double y;
    Double z;
    UUID world;

    public Position(Location l){
        x=l.getX();
        y=l.getY();
        z=l.getZ();
        world=l.getWorld().getUID();
    }

    public Position(double X, double Y, double Z, UUID world){
        this.x=X;
        this.y=Y;
        this.z=Z;
        this.world=world;
    }

    public Position(double X, double Y, double Z, World world){
        this.x=X;
        this.y=Y;
        this.z=Z;
        this.world=world.getUID();
    }

    public String toString()
    {
        String string = new String();
        string = string + "X: "+this.x.toString() +" Y: "+this.y.toString()+" Z: "+this.z.toString()+"  World: "+Bukkit.getWorld(this.world).getName();
        return string;
    }

    public Block getBlock()
    {
        return this.getLocation().getBlock();
    }

    public BlockData getBlockData()
    {
        return this.getBlock().getBlockData();
    }

    public void toBlock(Player player, BlockData block)
    {
        player.sendBlockChange(this.getLocation(),block);
    }

    public static void positionsToBlocks(ArrayList<Position> positions, Player player, BlockData block)
    {
        for (Position p: positions)
        {
            player.sendBlockChange(p.getLocation(),block);
        }
    }

    public static void updatePositionBlocks(ArrayList<Position> positions, Player player)
    {
        for (Position p: positions)
        {
            player.sendBlockChange(p.getLocation(),p.getBlockData());
        }
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
