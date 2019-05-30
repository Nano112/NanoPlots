package nano.topred.NanoPlots;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class PlotPlayer {
    private ArrayList<Integer> plotIDs;
    private UUID playerUUID;

    public PlotPlayer(Player p){
        playerUUID = p.getUniqueId();
        plotIDs=new ArrayList<>();
    }
    public Player getPlayer(){
       return Bukkit.getPlayer(playerUUID);
    }
    public void setPlotIDs(ArrayList<Integer> plotIDs) {
        this.plotIDs = plotIDs;
    }

    public ArrayList<Integer> getPlotIDs() {
        return plotIDs;
    }

    public void setPlayerUUID(UUID playerUUID)
    {
        this.playerUUID = playerUUID;
    }

    public UUID getPlayerUUID()
    {
        return playerUUID;
    }
}
