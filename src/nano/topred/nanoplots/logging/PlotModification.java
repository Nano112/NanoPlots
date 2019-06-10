package nano.topred.nanoplots.logging;

import nano.topred.nanoplots.plots.PlotPlayer;

import java.sql.Timestamp;

public class PlotModification {

    private String type;
    private PlotPlayer actor;
    private Timestamp time;

    public PlotModification(String t, PlotPlayer a)
    {
        this.type = t;
        this.actor = a;
        this.time = new Timestamp(System.currentTimeMillis());

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PlotPlayer getActor() {
        return actor;
    }

    public void setActor(PlotPlayer actor) {
        this.actor = actor;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
