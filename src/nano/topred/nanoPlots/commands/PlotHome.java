package nano.topred.nanoPlots.commands;

import nano.topred.nanoPlots.Main;
import nano.topred.nanoPlots.PlotPlayer;
import nano.topred.nanoPlots.plots.Plot;
import nano.topred.nanoPlots.plots.Plots;
import nano.topred.nanoPlots.PlotsData;
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
        player.teleport(plot.getPosition().getLocation());
        player.sendMessage("You have been teleported");
        return true;
    }
}
