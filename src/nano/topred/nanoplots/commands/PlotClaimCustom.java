package nano.topred.nanoplots.commands;

import nano.topred.nanoplots.mymath.geometry.graphics2D.Point2D;
import nano.topred.nanoplots.mymath.geometry.graphics2D.Polygon2D;
import nano.topred.nanoplots.plots.PlotGeometry;
import nano.topred.nanoplots.Position;
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

public class PlotClaimCustom implements CommandExecutor, Listener {


    private static HashMap<Player, PlotGeometry> customPlots = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(!(sender instanceof Player))
        {
            sender.sendMessage("You must be a player");
            return false;
        }
        Player player = (Player)sender;
        if (args.length==0)
        {
            sender.sendMessage("You must use arguments");
            return false;
        }

        if(args[0].equals("add"))
        {
            sender.sendMessage("Started creation");
            this.customPlots.put((Player) sender,new PlotGeometry(
                    new Polygon2D(new ArrayList<Point2D>()),
                    player.getLocation().getY()-2,
                    player.getLocation().getY(),
                    player.getWorld().getUID()));

        }
        if(args[0].equals("stop"))
        {
            sender.sendMessage("End of creation");
            this.customPlots.get(sender).unShowBorder((Player)sender);
            this.customPlots.get(sender).unShowBoundingBox((Player)sender);
            this.customPlots.get(sender).unShowSurface((Player)sender);
            this.customPlots.remove(sender);


        }
        return true;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerToggleSneakEvent(PlayerToggleSneakEvent event)
    {

        Player player = event.getPlayer();
        if(player.isSneaking())
        {
            if(this.customPlots.containsKey(player))
            {
                this.customPlots.get(player).unShowSurface(player);
                this.customPlots.get(player).unShowBorder(player);
                this.customPlots.get(player).unShowBoundingBox(player);
                PlotGeometry cp = this.customPlots.get(player) ;
                cp.addControlePoint(new Position(player.getLocation()));
                this.customPlots.put(player,cp);
                this.customPlots.get(player).showSurface(player);
                this.customPlots.get(player).showBoundingBox(player);
                this.customPlots.get(player).showBorder(player);
                this.customPlots.get(player).showControlePoints(player);


            }
        }
    }


}
