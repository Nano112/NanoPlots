package nano.topred.nanoplots.commands;

import nano.topred.nanoplots.Main;
import nano.topred.nanoplots.plots.PlotPlayer;
import nano.topred.nanoplots.plots.Plot;
import nano.topred.nanoplots.plots.Plots;
import nano.topred.nanoplots.PlotsData;
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
        ArrayList<Long> plotPlayerPlots = plotPlayer.getPlotIDs();
        Plot plot;
        if (plotPlayerPlots.size()==0)
        {
            sender.sendMessage("You don't have any plots");
            return false;
        }
        if(args.length==0)
        {
             plot = Plots.getPlotFromID(plotPlayerPlots.get(0));
        }
        else
        {
            if (!Main.isNumeric(args[0]))
            {
                sender.sendMessage("You must use a number");
                return false;
            }
            int plotNumber = Integer.parseInt(args[0]);
            if (! (plotNumber < plotPlayerPlots.size())|| plotNumber<0) {
                sender.sendMessage("This plot does not exist");
                return false;
            }
            plot = Plots.getPlotFromID(plotPlayerPlots.get(plotNumber));
        }

        if (plot == null)
        {
            sender.sendMessage("Plot doesn't exist");
            return false;
        }

        player.teleport(plot.getPosition().getLocation());
        player.sendMessage("You have been teleported to plot n°"+plot.getId());
        return true;
    }
}
