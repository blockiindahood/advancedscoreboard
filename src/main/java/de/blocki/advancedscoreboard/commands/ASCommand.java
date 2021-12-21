package de.blocki.advancedscoreboard.commands;

import de.blocki.advancedscoreboard.AdvancedScoreboard;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ASCommand implements CommandExecutor, TabCompleter {
    private static final String prefix = AdvancedScoreboard.getPluginPrefix();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player pSender = (Player) sender;

            if(args[0].equalsIgnoreCase("reload")){
                if(pSender.hasPermission("as.reload") | pSender.hasPermission("advancedscoreboard.reload")){
                    AdvancedScoreboard.loadNewScoreboardFromConfig();
                    pSender.sendMessage(prefix + AdvancedScoreboard.getPluginConfig().getString("messages.reload.success", true));
                }else {
                    pSender.sendMessage(prefix + AdvancedScoreboard.getPluginConfig().getString("messages.no_perm", true));
                }
            }

            return true;
        }
        return false;
    }


    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        ArrayList<String> returnVal = new ArrayList<>();

        if(args.length == 1){
            returnVal.add("reload");
        }

        return returnVal;
    }
}
