package nano.topred.NanoPlots.Commands;

import nano.topred.NanoPlots.Plots.Plot;
import nano.topred.NanoPlots.Plots.Plots;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlotDelete implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (! (sender instanceof Player))
        {
            sender.sendMessage("You must be a player");
            return false;
        }
        if (args.length!=1)
        {
          Player player = (Player)sender;
          Plot plot = Plots.plotContainingPlayer(player);
          if(plot.getOwners().contains(player))
          {
              Plots.removePlotFromID(plot.getId());
              return true;
          }
        }
        return false;
    }
    //A fair
}
