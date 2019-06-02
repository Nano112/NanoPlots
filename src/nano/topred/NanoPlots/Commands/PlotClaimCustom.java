package nano.topred.NanoPlots.Commands;

import nano.topred.NanoPlots.Plots.CustomPlot;
import nano.topred.NanoPlots.Position;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlotClaimCustom implements CommandExecutor, Listener {


    private static HashMap<Player, CustomPlot> customPlots = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(!(sender instanceof Player))
        {
            sender.sendMessage("You must be a player");
            return false;
        }
        if (args.length==0)
        {
            sender.sendMessage("You must use arguments");
            return false;
        }

        if(args[0].equals("add"))
        {
            sender.sendMessage("Started creation");
            customPlots.put((Player) sender,new CustomPlot());

        }
        if(args[0].equals("stop"))
        {
            sender.sendMessage("End of creation");
            customPlots.get(sender).unShowBorder((Player)sender);
            customPlots.remove(sender);


        }
        return true;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerToggleSneakEvent(PlayerToggleSneakEvent event)
    {

        Player player = event.getPlayer();
        if(player.isSneaking())
        {
            if(customPlots.containsKey(player))
            {
                customPlots.get(player).unShowBorder(player);
                CustomPlot cp = customPlots.get(player) ;
                cp.addPosition(new Position(player.getLocation().add(0,-1,0)));
                customPlots.put(player,cp);

                customPlots.get(player).showBorder(player);
                customPlots.get(player).showControlePoints(player);
            }
        }
    }


}
