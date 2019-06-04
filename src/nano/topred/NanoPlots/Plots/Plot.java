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

    private PlotGeometry plotGeometry;

    private PlotMembers plotMembers;

    private Position position;

    private Position minPosition;

    private Position maxPosition;

    private int minY;
    private int maxY;

    public Plot(PlotPlayer o, int ID, PlotGeometry plotGeometry, int minY, int maxY)
    {
        this.minY = minY;
        this.maxY = maxY;
        this.creator = o;
        this.id = ID;
        this.plotMembers = new PlotMembers(new PlotMember(o, Rank.OWNER));
        this.plotGeometry = plotGeometry;
        this.creationTime = new Timestamp(System.currentTimeMillis());
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
        sender.sendMessage("Mean Position: World:" + this.position.getWorld().getName() + "  X: " + this.position.getX() + "  Y: " + this.position.getY() + "  Z: " + this.position.getZ());
        sender.sendMessage("Players: ");
        this.printPlayers(sender);
    }

    public void createWalls()
    {
        for (int x = (int)minPosition.getX(); x < maxPosition.getX(); ++x)
        {
            for (int z = (int)minPosition.getZ(); z < maxPosition.getZ(); ++z)
            {

                Location l = new Location(position.getWorld(), x, position.getY(), z);
                World world = l.getWorld();
                world.getHighestBlockAt(x, z);
                creator.getPlayer().sendBlockChange(l, Material.DIAMOND_BLOCK, (byte) 0);
            }
        }
    }

    public boolean isInPlot(Position p)
    {
        return this.plotGeometry.isInside(p);
    }

    public boolean isInPlot(Location l)
    {
        return isInPlot(new Position(l));
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

    public Position getPosition()
    {
        return position;
    }

    public void setPosition(Position corner)
    {
        this.position = corner;
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
