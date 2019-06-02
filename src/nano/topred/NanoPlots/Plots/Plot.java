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

    private List<SubPlot> subPlots;

    private PlotMembers plotMembers;

    private Position position;

    private Position minPosition;

    private Position maxPosition;

    private int minY;
    private int maxY;

    public Plot(PlotPlayer o, int ID, List<SubPlot> subPlots, int minY, int maxY)
    {
        this.minY = minY;
        this.maxY = maxY;
        this.creator = o;
        this.id = ID;
        this.plotMembers = new PlotMembers(new PlotMember(o, Rank.OWNER));
        this.subPlots = subPlots;
        this.creationTime = new Timestamp(System.currentTimeMillis());
        this.position = getMeanPosition();
        this.minPosition = getMinPosition();
        this.maxPosition = getMaxPosition();
        createWalls();
    }


    public Position getMeanPosition()
    {
        double X = 0;
        double Y = 0;
        double Z = 0;
        int count = 0;
        Position p = null;
        for(SubPlot sp: this.subPlots)
        {
            count++;
            p = sp.getCorner();
            X += p.getX();
            Y += p.getY();
            Z += p.getZ();
        }
        return new Position(X/count,Y/count,Z/count,p.getWorld());
    }

    public Position getMaxPosition()
    {
        Position maxPos = this.subPlots.get(0).getMaxPosition();
        double maxX = maxPos.getX();
        double maxY= maxPos.getY();
        double maxZ = maxPos.getZ();
        for(int i=1; i<this.subPlots.size(); ++i)
        {

            maxPos = this.subPlots.get(i).getMaxPosition();
            if(maxX<maxPos.getX())
            {
                maxX = maxPos.getX();
            }
            if(maxY<maxPos.getY())
            {
                maxY = maxPos.getY();
            }
            if(maxZ<maxPos.getZ())
            {
                maxZ = maxPos.getZ();
            }
        }
        return new Position(maxX,maxY,maxZ,maxPos.getWorld());
    }

    public Position getMinPosition()
    {
        Position minPos = this.subPlots.get(0).getMinPosition();
        double minX = minPos.getX();
        double minY= minPos.getY();
        double minZ = minPos.getZ();
        for(int i=1; i<this.subPlots.size(); ++i)
        {

            minPos = this.subPlots.get(i).getMinPosition();
            if(minX<minPos.getX())
            {
                minX = minPos.getX();
            }
            if(minY<minPos.getY())
            {
                minY = minPos.getY();
            }
            if(minZ < minPos.getZ())
            {
                minZ = minPos.getZ();
            }
        }
        return new Position(minX,minY,minZ,minPos.getWorld());
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
        if(p.getY()>=this.minY && p.getY()<=this.maxY)
            for(SubPlot sp: subPlots)
                if(sp.isInSubPlot(p))
                    return true;
        return false;
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
