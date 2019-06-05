package nano.topred.nanoPlots.commands;

import nano.topred.nanoPlots.plots.Plot;
import nano.topred.nanoPlots.PlotsData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PlotList implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("There are "+ PlotsData.getPlots().getPlots().size() + " plots ");
        if(! PlotsData.getPlots().getPlots().isEmpty()) {
            for (Plot p : PlotsData.getPlots().getPlots()) {
                p.plotPrintInfo(sender);
            }
            sender.sendMessage("----------------------------");
        }
        return true;
    }
}
