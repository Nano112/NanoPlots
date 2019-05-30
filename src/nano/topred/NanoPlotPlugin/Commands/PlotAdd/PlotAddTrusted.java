package nano.topred.NanoPlotPlugin.Commands.PlotAdd;

import nano.topred.NanoPlotPlugin.Plots.Plot;
import nano.topred.NanoPlotPlugin.Plots.Plots;
import nano.topred.NanoPlotPlugin.Plots.Rank;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlotAddTrusted implements CommandExecutor {
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

        if ( ! plot.getCreator().equals(player)){
            sender.sendMessage("You must be the owner");
            return true;
        }



        if (player2 != null){
            sender.sendMessage("Player doesn't exist");
            return true;
        }


        if (plot.getPlotMembers().containsPlayer(player2))
        {
            sender.sendMessage(player2.getDisplayName() +" already exists in the plot.");
            if (plot.getPlotMembers().getRank(player).equals(Rank.TRUSTED)){
                sender.sendMessage(player2.getDisplayName() + " is already a trusted on the plot.");
            }
            else{
                plot.getPlotMembers().setRank(player2,Rank.TRUSTED);
                sender.sendMessage(player2.getDisplayName()+ " changed to trusted on the plot");
            }
        }
        else{
            plot.getPlotMembers().addPlayer(player2,Rank.TRUSTED);
            sender.sendMessage(player2.getDisplayName()+ " is now trusted on the plot");
        }

        return true;
    }

}
