package nano.topred.NanoPlots.Plots;


import nano.topred.NanoPlots.PlotPlayer;
import nano.topred.NanoPlots.PlotsData;
import nano.topred.NanoPlots.Position;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("ALL")
public class Plot
{
    //testK
    private int id;

    private PlotPlayer creator;

    private Timestamp creationTime;

    private int width;//longeur du plot en X

    private int height;//longueur du plot en Z

    private Position corner;

    private PlotMembers plotMembers;


    public Plot(PlotPlayer o, int ID, Location l, int w, int h)
    {
        creator = o;
        id = ID;
        PlotMember pm = new PlotMember(o, Rank.OWNER);
        plotMembers = new PlotMembers(pm);
        corner = new Position(l); //Coin Nord West donc plus petit X et plus petit Z
        width = w;
        height = h;
        creationTime = new Timestamp(System.currentTimeMillis());
        createWalls();
    }

    public Plot(Player o, int ID, Location l, int w, int h)
    {
        creator = PlotsData.getPlotPlayer(o);
        id = ID;
        PlotMember pm = new PlotMember(PlotsData.getPlotPlayer(o), Rank.OWNER);
        plotMembers = new PlotMembers(pm);
        corner = new Position(l); //Coin Nord West donc plus petit X et plus petit Z
        width = w;
        height = h;
        creationTime = new Timestamp(System.currentTimeMillis());
        createWalls();
    }

    public void printPlayers(CommandSender s)
    {
        for (PlotMember member : plotMembers.members)
        {
            s.sendMessage("     " + member.getRank() + ": " + member.getPlayer().getPlayer().getDisplayName());
        }

    }

    public void plotPrintInfo(CommandSender sender)
    {
        sender.sendMessage("----------------------------");
        sender.sendMessage("ID: "+ this.getId());
        sender.sendMessage("Owner: " + this.creator.getPlayer().getDisplayName().toString());
        sender.sendMessage("Creation Time: " + this.creationTime.toString());
        sender.sendMessage("Position: World:" + this.corner.getWorld().getName() + "  X: " + this.corner.getX() + "  Y: " + this.corner.getY() + "  Z: " + this.corner.getZ());
        sender.sendMessage("Players: ");
        this.printPlayers(sender);
    }

    public void createWalls()
    {
        for (int x = (int)corner.getX(); x < corner.getX() + width; ++x)
        {
            for (int z = (int)corner.getZ(); z < corner.getZ() + height; ++z)
            {

                Location l = new Location(corner.getWorld(), x, corner.getY(), z);
                World world = l.getWorld();
                world.getHighestBlockAt(x, z);
                creator.getPlayer().sendBlockChange(l, Material.DIAMOND_BLOCK, (byte) 0);
            }
        }
    }

    public boolean isInPlot(Location l)
    {
        if (l.getX() >= corner.getX() && l.getX() < corner.getX() + width)
        {
            if (l.getZ() >= corner.getZ() && l.getZ() < corner.getZ() + height)
            {
                return true;
            }
        }
        return false;
    }

    public boolean canBuild(Plot plot, PlotPlayer p)
    {
        ArrayList<PlotMember> list = plot.plotMembers.members;
        PlotMember plotmember = list.stream().filter(o -> p.equals(o.getPlayer())).findFirst().orElse(null);
        if (plotmember == null)
        {
            return false;
        }
        if (plotmember.getRank().ordinal() > Rank.RANDOM.ordinal())
        {
            return true;
        }
        return false;
    }

    public boolean canBuild(Plot plot, Player p)
    {
        ArrayList<PlotMember> list = plot.plotMembers.members;
        PlotMember plotmember = list.stream().filter(o -> PlotsData.getPlotPlayer(p).equals(o.getPlayer())).findFirst().orElse(null);
        if (plotmember == null)
        {
            return false;
        }
        if (plotmember.getRank().ordinal() > Rank.RANDOM.ordinal())
        {
            return true;
        }
        return false;
    }

    public List<Player> getOwners()
    {
        List<Player> owners = new ArrayList<>();
        for (PlotMember m:this.plotMembers.members)
        {
            if(m.getRank().equals(Rank.OWNER))
            {
                owners.add(m.getPlayer().getPlayer());
            }
        }
        return owners;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public PlotPlayer getCreator()
    {
        return creator;
    }

    public void setCreator(PlotPlayer creator)
    {
        this.creator = creator;
    }

    public Timestamp getCreationTime()
    {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime)
    {
        this.creationTime = creationTime;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public Position getCorner()
    {
        return corner;
    }

    public void setCorner(Position corner)
    {
        this.corner = corner;
    }

    public PlotMembers getPlotMembers()
    {
        return plotMembers;
    }

    public void setPlotMembers(PlotMembers plotMembers)
    {
        this.plotMembers = plotMembers;
    }
}
