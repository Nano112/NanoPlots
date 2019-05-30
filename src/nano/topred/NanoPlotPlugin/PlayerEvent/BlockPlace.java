package nano.topred.NanoPlotPlugin.PlayerEvent;

import nano.topred.NanoPlotPlugin.Plots.Plot;
import nano.topred.NanoPlotPlugin.Plots.Plots;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {

    @SuppressWarnings("deprecation")
    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockPlace(BlockPlaceEvent event)
    {
        Player player = event.getPlayer();
        Plot plot = Plots.plotContainingLocation(event.getBlockPlaced().getLocation());
        if (plot == null){
            return;
        }
        if (! plot.canBuild(plot,player))
        {
            player.sendMessage("You cannot build here");
            event.setCancelled(true);
        }

    }
}
