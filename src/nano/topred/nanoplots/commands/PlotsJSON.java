package nano.topred.nanoplots.commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nano.topred.nanoplots.plots.Plots;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PlotsJSON implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {

        Player player = (Player) sender;
        sender.sendMessage("Working Directory = " + System.getProperty("user.dir"));
        Gson gson =new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(Plots.getPlots());
        try (PrintWriter out = new PrintWriter("filename.json")) {
            out.println(json);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        sender.sendMessage(json);
        return true;
    }

}