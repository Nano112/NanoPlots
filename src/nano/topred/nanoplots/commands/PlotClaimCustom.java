package nano.topred.nanoplots.commands;

import nano.topred.nanoplots.mymath.geometry.geometry2D.Point2D;
import nano.topred.nanoplots.mymath.geometry.geometry2D.Polygon2D;
import nano.topred.nanoplots.plots.Plot;
import nano.topred.nanoplots.plots.PlotGeometry;
import nano.topred.nanoplots.mymath.Position;
import nano.topred.nanoplots.plots.Plots;
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
            if(customPlots.containsKey(player))
            {
                player.sendMessage("Creation already started");
                return false;
            }
            sender.sendMessage("Started creation");
            customPlots.put((Player) sender,
                        new PlotGeometry(
                            new Polygon2D(
                                    new ArrayList<Point2D>()),
                                ((Player) sender).getLocation().getY()-2,
                                ((Player) sender).getLocation().getY(),
                                player.getWorld().getUID()));
            return true;
        }

        if(args[0].equals("confirm"))
        {
            PlotGeometry pG = customPlots.get(player);
            if(pG == null)
            {
                sender.sendMessage("No creation");
                return false;
            }
            sender.sendMessage("End of creation");
            pG.unShowBorder(player);
            pG.unShowBoundingBox(player);
            pG.unShowSurface(player);
            pG.setYMin(0);
            pG.setYMax(256);
            Plot p = new Plot(player,pG);
            Plots.add(p);
            customPlots.remove(sender);
            return true;
        }

        if(args[0].equals("stop"))
        {
            PlotGeometry pG = customPlots.get(sender);
            if(pG == null)
            {
                sender.sendMessage("No creation");
                return false;
            }
            sender.sendMessage("End of creation");
            pG.unShowBorder((Player)sender);
            pG.unShowBoundingBox((Player)sender);
            pG.unShowSurface((Player)sender);
            customPlots.remove(sender);
            return true;
        }
        return false;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerToggleSneakEvent(PlayerToggleSneakEvent event)
    {
        //Something cheaky happens, plot is at top of the world


        Player player = event.getPlayer();
        if(player.isSneaking())
        {
            if(customPlots.containsKey(player))
            {
                player.sendMessage("new control point added");
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
