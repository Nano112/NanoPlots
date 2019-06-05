package nano.topred.NanoPlots.Commands;

import nano.topred.NanoPlots.MyMath.Geometry.G2D.Point2D;
import nano.topred.NanoPlots.MyMath.Geometry.G2D.Polygon2D;
import nano.topred.NanoPlots.Plots.PlotGeometry;
import nano.topred.NanoPlots.Position;
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
            customPlots.put((Player) sender,new PlotGeometry(
                    new Polygon2D(new ArrayList<Point2D>()),
                    player.getLocation().getY()-2,
                    player.getLocation().getY(),
                    player.getWorld().getUID()));

        }
        if(args[0].equals("stop"))
        {
            sender.sendMessage("End of creation");
            customPlots.get(sender).unShowBorder((Player)sender);
            customPlots.get(sender).unShowBoundingBox((Player)sender);
            customPlots.get(sender).unShowSurface((Player)sender);
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
                customPlots.get(player).unShowSurface(player);
                customPlots.get(player).unShowBorder(player);
                customPlots.get(player).unShowBoundingBox(player);
                PlotGeometry cp = customPlots.get(player) ;
                cp.addControlePoint(new Position(player.getLocation()));
                customPlots.put(player,cp);
                customPlots.get(player).showSurface(player);
                customPlots.get(player).showBoundingBox(player);
                customPlots.get(player).showBorder(player);
                customPlots.get(player).showControlePoints(player);

            }
        }
    }


}
