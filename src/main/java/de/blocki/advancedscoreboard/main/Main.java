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
        // Plugin startup logic
        instance = this;

        setDefaultConfig();
        update_time = Integer.parseInt(ConfigManager.get("Scoreboard.Update"));

        getServer().getPluginManager().registerEvents(new PlayerJoinLeave(), this);
        getServer().getScheduler().runTaskTimer(this, () -> {
            for (FastBoard board : boards.values()) {
                if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI").isEnabled()) {
                    updateBoardPAPI(board);
                }else {
                    updateBoard(board);
                }
            }
        }, 0, update_time * 20L);

    }

    private void updateBoardPAPI(FastBoard board) {
        board.updateLinesColection(
                ConfigManager.getList("Scoreboard.lines").stream().map(s -> ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(board.getPlayer(), String.valueOf(s)))).collect(Collectors.toList())
        );
    }

    private void updateBoard(FastBoard board) {
        board.updateLinesColection(
                ConfigManager.getList("Scoreboard.lines").stream().map(s -> ChatColor.translateAlternateColorCodes('&', String.valueOf(s))).collect(Collectors.toList())
        );
    }

    private void setDefaultConfig(){
        List<String> list = new ArrayList<>();
        list.add("&1");
        list.add("&bProfil");
        list.add("&f%Name%");
        list.add("&2");
        list.add("&bRang");
        list.add("&f%Rang%");
        list.add("&3");
        list.add("&bServer");
        list.add("&f%cn_current_service_name%");
        list.add("&4");
        list.add("&bCoins");
        list.add("&f%Coins%");
        list.add("&5");
        list.add("&bWebseite");
        list.add("&fRiseMC.eu");
        if (ConfigManager.get("Scoreboard.Name") == null){ ConfigManager.set("Scoreboard.Name", "&3&lRiseMC.eu"); }
        if (ConfigManager.getList("Scoreboard.lines") == null) { ConfigManager.set("Scoreboard.lines", list); }
        if (ConfigManager.get("Scoreboard.Update") == null){ ConfigManager.set("Scoreboard.Update", 1); }
    }
}
