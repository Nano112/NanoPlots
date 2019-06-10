package nano.topred.nanoplots.commands.plotadd;

import nano.topred.nanoplots.plots.Plot;
import nano.topred.nanoplots.plots.Plots;
import nano.topred.nanoplots.plots.Rank;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlotBan implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (! (sender instanceof Player))
        {
            sender.sendMessage("You must be a player");
            return false;
        }

        Player player = (Player)sender;
        Plot plot = Plots.plotContainingPlayer(player);
        String playerName = args[1];
        Player player2 = Bukkit.getPlayer(playerName);
        if (plot==null){
            sender.sendMessage("You must be in a plot");
            return false;
        }

        if ( ! plot.getPlotMembers().isRank(player, Rank.OWNER)){
            sender.sendMessage("You must be the owner");
            return false;
        }

        if (args.length!=2)
        {
            sender.sendMessage("Usage : /plotadd [PlayerName]");
            return false;
        }

        if (player2 != null){
            sender.sendMessage("Player doesn't exist");
            return false;
        }



        return true;
    }

}
