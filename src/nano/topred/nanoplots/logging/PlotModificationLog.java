package nano.topred.nanoplots.logging;

import nano.topred.nanoplots.plots.PlotPlayer;

import java.sql.Timestamp;
import java.util.ArrayList;

public class PlotModificationLog
{
    private ArrayList<PlotModification> log = new ArrayList<>();
    public PlotModificationLog(PlotPlayer creator)
    {
       log.add(new PlotModification("Plot Creation", creator));
    }

    public PlotModification getCreation()
    {
        return this.log.get(0);
    }

    public void add(PlotModification plotModification)
    {
        this.log.add(plotModification);
    }
}
