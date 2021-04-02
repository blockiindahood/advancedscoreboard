package de.blocki.advancedscoreboard.main;

import de.blocki.advancedscoreboard.fb.FastBoard;
import de.blocki.advancedscoreboard.listener.PlayerJoinLeave;
import de.blocki.advancedscoreboard.utils.ConfigManager;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.stream.Collectors;

public final class Main extends JavaPlugin {

    public static final Map<UUID, FastBoard> boards = new HashMap<>();

    public static Plugin instance;

    private static int update_time;

    @Override
    public void onEnable() {
        instance = this;

        setDefaultConfig();
        update_time = Integer.parseInt(ConfigManager.get("Scoreboard.Update"));

        getServer().getPluginManager().registerEvents(new PlayerJoinLeave(), this);
        getServer().getScheduler().runTaskTimer(this, () -> {
            for (FastBoard board : boards.values()) {
                if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                    updateBoard(board, true);
                }else {
                    updateBoard(board, false);
                }
            }
        }, 0, update_time * 20L);

    }

    private void updateBoard(FastBoard board, boolean isPAPI){
        if(isPAPI){
            board.updateLinesColection(
                    ConfigManager.getList("Scoreboard.lines").stream().map(s -> ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(board.getPlayer(), String.valueOf(s)))).collect(Collectors.toList())
            );
        }else {
            board.updateLinesColection(
                    ConfigManager.getList("Scoreboard.lines").stream().map(s -> ChatColor.translateAlternateColorCodes('&', String.valueOf(s))).collect(Collectors.toList())
            );
        }
    }

    private void setDefaultConfig(){
        List<String> list = new ArrayList<>();
        list.add("&1");
        list.add("&bProfile");
        list.add("&f%player_name%");
        list.add("&2");
        list.add("&bRank");
        list.add("&f%vault_prefix_color%%vault_rank%");
        list.add("&3");
        list.add("&bServer");
        list.add("&f%cn_current_service_name%");
        list.add("&4");
        list.add("&bCoins");
        list.add("&f%vault_eco_balance_formatted%");
        list.add("&5");
        list.add("&bWebsite");
        list.add("&fYourSITE.eu");
        ConfigManager.setDef("Scoreboard.Name", "&3&lYourSITE.eu");
        ConfigManager.setDef("Scoreboard.lines", list);
        ConfigManager.setDef("Scoreboard.Update", 1);
    }
}
