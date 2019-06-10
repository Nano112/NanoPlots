package nano.topred.nanoplots;

import nano.topred.nanoplots.plots.PlotPlayer;
import nano.topred.nanoplots.plots.Plots;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PlotsData
{
    private static Plots plots;
    private static ArrayList<PlotPlayer> plotPlayers;

    public PlotsData()
    {
        plots = new Plots(new ArrayList<>());
        plotPlayers = new ArrayList<PlotPlayer>();
    }

    public static PlotPlayer getPlotPlayer(Player player)
    {
        for (PlotPlayer p : plotPlayers)
        {
            if (p.getPlayer().equals(player))
            {
                return p;
            }
        }
        PlotPlayer newPlotPlayer = new PlotPlayer(player);
        plotPlayers.add(newPlotPlayer);
        return newPlotPlayer;
    }

    public static Plots getPlots()
    {
        return plots;
    }

    public static void setPlots(Plots plots)
    {
        PlotsData.plots = plots;
    }

    public static ArrayList<PlotPlayer> getPlotPlayers()
    {
        return plotPlayers;
    }

    public static void setPlotPlayers(ArrayList<PlotPlayer> plotPlayers)
    {
        PlotsData.plotPlayers = plotPlayers;
    }
}
