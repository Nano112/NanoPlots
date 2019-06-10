package nano.topred.nanoplots.commands;

import nano.topred.nanoplots.plots.Plot;
import nano.topred.nanoplots.plots.Plots;
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
          if(plot==null)
          {
              sender.sendMessage("You are not in a plot");
              return false;
          }
          if(plot.getOwners().contains(player))
          {
              Plots.removePlotFromID(plot.getId());
              sender.sendMessage("Plot, id:"+ plot.getId() +" was deleted");
              return true;
          }
          else
          {
              sender.sendMessage("You are not a owner");
              return false;
          }
        }
        return false;
    }
    //A fair
}
