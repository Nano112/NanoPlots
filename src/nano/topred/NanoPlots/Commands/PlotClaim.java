//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package nano.topred.NanoPlots.Commands;


import nano.topred.NanoPlots.PlotPlayer;
import nano.topred.NanoPlots.Plots.Plot;
import nano.topred.NanoPlots.Plots.Plots;
import nano.topred.NanoPlots.PlotsData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlotClaim implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player))
        {
            sender.sendMessage("sender must be a player");
            return false;
        } else
        {
            Player player = (Player) sender;
            PlotPlayer plotPlayer = PlotsData.getPlotPlayer(player);
            if (!Plots.isInPlots(plotPlayer.getPlayer().getLocation()) && !Plots.isInPlots(plotPlayer.getPlayer().getLocation().add(10.0D, 0.0D, 10.0D)))
            {
                int id = Plots.getPlotNumber();
                plotPlayer.getPlotIDs().add(id);
                Plot plot = new Plot(plotPlayer,id, plotPlayer.getPlayer().getLocation(), 10, 10);
                Plots.getPlots().add(plot);
                return true;
            } else
            {
                plotPlayer.getPlayer().sendMessage("You are in a plot, you must claim oustide of a plot");
                return false;
            }
        }
    }
}
