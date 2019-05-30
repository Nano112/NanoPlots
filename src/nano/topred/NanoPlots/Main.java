package nano.topred.NanoPlots;

import nano.topred.NanoPlots.Commands.*;
import nano.topred.NanoPlots.Commands.PlotAdd.PlotAddOwner;
import nano.topred.NanoPlots.Commands.PlotAdd.PlotAddPlayer;
import nano.topred.NanoPlots.Commands.PlotAdd.PlotAddTrusted;
import nano.topred.NanoPlots.PlayerEvent.BlockPlace;
import nano.topred.NanoPlots.PlayerEvent.PlayerInteract;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{

    private static PlotsData plotsData;

    @Override

    public void onEnable()
    {
        //Fired When server starts

        System.out.println("-------------------------------------");
        System.out.println("|        Plot Plugin loaded         |");
        System.out.println("-------------------------------------");
        plotsData = new PlotsData();
        registerCommands();
        registerEvents();
    }

    @Override

    public void onDisable()
    {
        //Fired when server stops and disables all plugins
        System.out.println("-------------------------------------");
        System.out.println("|       Plot Plugin unloaded        |");
        System.out.println("-------------------------------------");
    }

    public void registerCommands()
    {
        this.getCommand("test").setExecutor(new TestCommand());
        this.getCommand("plotClaim").setExecutor(new PlotClaim());
        this.getCommand("plotList").setExecutor(new PlotList());
        this.getCommand("plotInfo").setExecutor(new PlotInfo());
        this.getCommand("plotAddPlayer").setExecutor(new PlotAddPlayer());
        this.getCommand("plotAddTrusted").setExecutor(new PlotAddTrusted());
        this.getCommand("plotAddOwner").setExecutor(new PlotAddOwner());
        this.getCommand("plotHome").setExecutor(new PlotHome());
        this.getCommand("plotDelete").setExecutor(new PlotDelete());
        this.getCommand("json").setExecutor(new PlotsJSON());
    }

    public void registerEvents()
    {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new BlockPlace(), this);
        pm.registerEvents(new PlayerInteract(), this);

    }

    public static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public PlotsData getPlotsData()
    {
        return plotsData;
    }
}
