package nano.topred.nanoplots.commands.plotadd;

import nano.topred.nanoplots.plots.Plot;
import nano.topred.nanoplots.plots.Plots;
import nano.topred.nanoplots.plots.Rank;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlotAddPlayer implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (! (sender instanceof Player))
        {
            sender.sendMessage("You must be a player");
            return false;
        }
        if (args.length!=1)
        {
            return false;
        }

        Player player = (Player)sender;
        Plot plot = Plots.plotContainingPlayer(player);
        String playerName = args[0];
        Player player2 = Bukkit.getPlayer(playerName);

        if (plot==null){
            sender.sendMessage("You must be in a plot");
            return true;
        }

        if ( ! plot.getPlotMembers().isRank(player,Rank.OWNER)){
            sender.sendMessage("You must be the owner");
            return true;
        }


        if (player2 == null){
            sender.sendMessage("Player doesn't exist");
            return true;
        }

        if (plot.getPlotMembers().containsPlayer(player2))
        {
            sender.sendMessage("|");
            sender.sendMessage(player2.getDisplayName() +" already exists in the plot.");
            if (plot.getPlotMembers().getRank(player).equals(Rank.PLAYER)){
                sender.sendMessage(player2.getDisplayName() + " is already a player on the plot.");
            }
            else{
                plot.getPlotMembers().setRank(player2,Rank.PLAYER);
                sender.sendMessage(player2.getDisplayName()+ " changed to player on the plot");
            }
        }
        else{
            plot.getPlotMembers().addPlayer(player2,Rank.PLAYER);
            sender.sendMessage(player2.getDisplayName()+ " is now player on the plot");
        }

        return true;
    }

}
