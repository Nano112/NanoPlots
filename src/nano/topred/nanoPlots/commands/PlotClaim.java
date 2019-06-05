//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package nano.topred.nanoPlots.commands;


import nano.topred.nanoPlots.PlotPlayer;
import nano.topred.nanoPlots.plots.Plots;
import nano.topred.nanoPlots.PlotsData;
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
                int id = Plots.incrPlotNumber();
                plotPlayer.getPlotIDs().add(id);
                //TODO
            } else
            {
                plotPlayer.getPlayer().sendMessage("You are in a plot, you must claim oustide of a plot");
                return false;
            }
        }
        return true;
    }
}
