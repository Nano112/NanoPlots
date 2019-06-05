package nano.topred.nanoplots;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class PlotPlayer {
    private ArrayList<Integer> plotIDs;
    private UUID playerUUID;
    private double credit;

    public PlotPlayer(Player p){
        this.playerUUID = p.getUniqueId();
        this.plotIDs = new ArrayList<>();
        this.credit =  100;
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

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }
}
