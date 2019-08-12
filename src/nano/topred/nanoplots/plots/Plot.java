package nano.topred.nanoplots.plots;


import nano.topred.nanoplots.PlotsData;
import nano.topred.nanoplots.logging.PlotModificationLog;
import nano.topred.nanoplots.mymath.Position;
import nano.topred.nanoplots.mymath.geometry.geometry2D.Point2D;
import org.bukkit.Bukkit;
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
    private long id;

    private PlotModificationLog log;

    private PlotGeometry plotGeometry;

    private PlotMembers plotMembers;

    private Position position;

    public Plot(Player p, PlotGeometry plotGeometry)
    {
        PlotPlayer plotPlayer = PlotsData.getPlotPlayer(p);
        this.id = Plots.getNextPlotId();
        this.plotMembers = new PlotMembers(new PlotMember(plotPlayer, Rank.OWNER));
        this.plotGeometry = plotGeometry;
        this.plotGeometry.reCalcMeanPosition();
        this.position = this.plotGeometry.getMeanPosition();
        this.log = new PlotModificationLog(plotPlayer);
        plotPlayer.getPlotIDs().add(id);
        Bukkit.broadcastMessage("Plot created");
        showPlot(p);


    }


    public void plotPrintInfo(CommandSender sender)
    {
        sender.sendMessage("----------------------------");
        sender.sendMessage("ID: "+ this.getId());
        sender.sendMessage("Creation Time: " + this.log.getCreation().getTime());
        sender.sendMessage("Mean Position: World:" + this.position.getWorld().getName() + "  X: " + this.position.getX() + "  Y: " + this.position.getY() + "  Z: " + this.position.getZ());
        sender.sendMessage("Owners: " + this.plotMembers.getOwnersAsString());
        sender.sendMessage("Trusted: " + this.plotMembers.getTrustedAsString());
        sender.sendMessage("Players: " + this.plotMembers.getPlayersAsString());
    }

    public void showPlot(Player player)
    {
        Bukkit.broadcastMessage("Showing plot");
        ArrayList<Point2D> points = this.plotGeometry.getSurface();
        World w = this.position.getWorld();
        Bukkit.broadcastMessage(String.valueOf(points.size()));
        Position pos;
        for(Point2D p: points)
        {

            pos = Point2D.toPosition(p,player.getLocation().getBlockY(),w.getUID());
            pos.getLocation().getBlock().setBlockData(Material.ORANGE_WOOL.createBlockData());
        }
    }

    public void createWalls(Player player)
    {
        this.plotGeometry.showSurface(player);
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

    public long getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }


    public Timestamp getCreationTime()
    {
        return this.log.getCreation().getTime();
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
