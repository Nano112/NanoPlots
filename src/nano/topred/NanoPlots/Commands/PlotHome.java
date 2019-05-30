package nano.topred.NanoPlots.Commands;

import nano.topred.NanoPlots.Main;
import nano.topred.NanoPlots.PlotPlayer;
import nano.topred.NanoPlots.Plots.Plot;
import nano.topred.NanoPlots.Plots.Plots;
import nano.topred.NanoPlots.PlotsData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PlotHome implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player))
        {
            sender.sendMessage("You must be a player");
            return false;
        }
        Player player = (Player) sender;
        PlotPlayer plotPlayer = PlotsData.getPlotPlayer(player);
        if (!Main.isNumeric(args[0]))
        {
            sender.sendMessage("You must use a number");
            return false;
        }

        int plotNumber = Integer.parseInt(args[0]);
        ArrayList<Integer> plotPlayerPlots = plotPlayer.getPlotIDs();

        if (plotPlayerPlots.size() <= plotNumber)
        {
            sender.sendMessage("Plot doesn't exist");
            return true;
        }

        Plot plot = Plots.getPlotFromID(plotPlayerPlots.get(plotNumber));
        player.teleport(plot.getCorner().getLocation());
        player.sendMessage("You have been teleported");
        return true;
    }
}
