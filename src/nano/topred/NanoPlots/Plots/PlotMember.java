package nano.topred.NanoPlots.Plots;

import nano.topred.NanoPlots.PlotPlayer;

import java.sql.Timestamp;

@SuppressWarnings("ALL")
public class PlotMember
{
    private Timestamp joinTime;
    private PlotPlayer player;
    private Rank rank;


    public PlotMember(PlotPlayer p, Rank r)
    {
        player = p;
        rank = r;
        joinTime = new Timestamp(System.currentTimeMillis());
    }

    public boolean promotePlotMember()
    {
        if (rank.ordinal() >= Rank.BANNED.ordinal() && rank.ordinal() < rank.OWNER.ordinal())
        {
            rank = rank.next();
            return true;
        }
        return false;
    }

    public boolean demotePlotMember()
    {
        if (rank.ordinal() > Rank.BANNED.ordinal() && rank.ordinal() <= Rank.OWNER.ordinal())
        {
            rank = rank.previous();
            return true;
        }
        return false;
    }

    public Timestamp getJoinTime()
    {
        return joinTime;
    }

    public void setJoinTime(Timestamp joinTime)
    {
        this.joinTime = joinTime;
    }

    public PlotPlayer getPlayer()
    {
        return player;
    }

    public void setPlayer(PlotPlayer player)
    {
        this.player = player;
    }

    public Rank getRank()
    {
        return rank;
    }

    public void setRank(Rank rank)
    {
        this.rank = rank;
    }
}
