package nano.topred.nanoplots;

import nano.topred.nanoplots.mymath.geometry.graphics3D.Point3D;
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
    private Point3D pos;
    private UUID world;

    public Position(Location l){
        pos = new Point3D(l.getX(),l.getY(),l.getZ());
        world=l.getWorld().getUID();
    }


    public Position(Point3D point3D, UUID world){
        this.pos = point3D;
        this.world=world;
    }

    public Position(Point3D point3D, World world){
        this.pos = point3D;
        this.world=world.getUID();
    }

    public String toString()
    {
        return this.pos.toString()+"  World: "+Bukkit.getWorld(this.world).getName();
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
        return new Location(this.getWorld(),this.pos.getX(),this.pos.getY(),this.pos.getZ());
    }
    public double getX()
    {
        return this.pos.getX();
    }

    public double getY()
    {
        return this.pos.getY();
    }

    public double getZ()
    {
        return this.pos.getZ();
    }

    public World getWorld()
    {
        return Bukkit.getWorld(world);
    }

    public void setX(double x)
    {
        this.pos.setX(x);
    }

    public void setY(double y)
    {
        this.pos.setY(y);
    }

    public void setZ(double z)
    {
        this.pos.setZ(z);
    }

    public void setWorld(World world)
    {
        this.world = world.getUID();
    }
}
