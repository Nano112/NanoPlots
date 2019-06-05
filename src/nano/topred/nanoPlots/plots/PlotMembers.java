package nano.topred.nanoPlots.plots;

import nano.topred.nanoPlots.PlotPlayer;
import nano.topred.nanoPlots.PlotsData;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PlotMembers {

    public PlotMember creator;
    public ArrayList<PlotMember> members ;

    public PlotMembers(PlotMember plotMember)
    {
        creator = plotMember;
        members = new ArrayList<>();
        members.add(plotMember);

    }



    public Rank getRank(Player p){
       return this.members.stream().filter(o -> p.equals(o.getPlayer())).findFirst().orElse(null).getRank();
    }

    public void setRank(Player p,Rank r){
         this.members.stream().filter(o -> p.equals(o.getPlayer())).findFirst().orElse(null).setRank(r);
    }

    public boolean isRank(Player p, Rank r){
        Rank playerRank = getRank(p);
        return playerRank.equals(r);
    }
    public boolean containsPlayer(PlotPlayer p) {
        return this.members.indexOf(this.members.stream().filter(o -> p.equals(o.getPlayer())).findFirst().orElse(null)) != -1;
    }
    public boolean containsPlayer(Player p) {
        return this.members.indexOf(this.members.stream().filter(o -> PlotsData.getPlotPlayer(p).equals(o.getPlayer().getPlayer())).findFirst().orElse(null)) != -1;
    }



    public int getPlayerPosition(PlotPlayer p){
        return this.members.indexOf(this.members.stream().filter(o -> p.equals(o.getPlayer())).findFirst().orElse(null));
    }

    public int getPlayerPosition(Player p){
        return this.members.indexOf(this.members.stream().filter(o -> PlotsData.getPlotPlayer(p).equals(o.getPlayer().getPlayer())).findFirst().orElse(null));
    }


    public int countRank(Rank r) {
        return this.members.indexOf(this.members.stream().filter(o -> r == o.getRank()).count());
    }

    public boolean addPlayer(PlotPlayer p, Rank r) {
        PlotMember pm = new PlotMember(p, r);
        if (containsPlayer(p)) {
            return false; //Player was already in the plot
        }
        this.members.add(pm);
        return true; //Player was successfully added
    }

    public boolean addPlayer(Player p, Rank r) {
        PlotMember pm = new PlotMember(PlotsData.getPlotPlayer(p), r);
        if (containsPlayer(PlotsData.getPlotPlayer(p))) {
            return false; //Player was already in the plot
        }
        this.members.add(pm);
        return true; //Player was successfully added
    }

    public int removePlayer(Player p) {
        int position = getPlayerPosition(p);

        if (p.equals(creator)) {
            return -2; //Can't remove creator
        }

        if (position != -1) {
            this.members.remove(position);
            return 0; //Player was successfully removed
        }
        return -1; //Player was already removed
    }

    public PlotMember getCreator()
    {
        return creator;
    }

    public ArrayList<PlotMember> getMembers()
    {
        return members;
    }

    public void setCreator(PlotMember creator)
    {
        this.creator = creator;
    }

    public void setMembers(ArrayList<PlotMember> members)
    {
        this.members = members;
    }
}
