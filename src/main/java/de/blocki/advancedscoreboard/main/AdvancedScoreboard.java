package de.blocki.advancedscoreboard.main;

import de.blocki.advancedscoreboard.api.AdvancedScoreboardAPI;
import de.blocki.advancedscoreboard.fb.FBScoreboard;
import de.blocki.advancedscoreboard.fb.FastBoard;
import de.blocki.advancedscoreboard.listener.PlayerJoinLeave;
import de.blocki.advancedscoreboard.utils.ConfigManager;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.stream.Collectors;

public final class AdvancedScoreboard extends JavaPlugin {

    public static final Map<UUID, FastBoard> boards = new HashMap<>();
    public static Plugin instance;
    private static AdvancedScoreboardAPI api;
    public static FBScoreboard fbScoreboard;

    @Override
    public void onEnable() {
        instance = this;
        setDefaultConfig();
        api = new AdvancedScoreboardAPI();

        fbScoreboard = new FBScoreboard();
        fbScoreboard.setLines(ConfigManager.getList("scoreboard.lines"));
        fbScoreboard.setTitle(ConfigManager.getString("scoreboard.title"));
        fbScoreboard.setUpdateTime(Integer.parseInt(ConfigManager.get("scoreboard.update_time")));

        getServer().getPluginManager().registerEvents(new PlayerJoinLeave(), this);


        getServer().getScheduler().runTaskTimerAsynchronously(this, () -> {
            for (FastBoard board : boards.values()) {
                try {
                    updateBoard(board, Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }, 0, fbScoreboard.getUpdateTime() * 20L);


    }

    private void updateBoard(FastBoard board, boolean isPAPI){
        if(isPAPI){
            board.updateLines(fbScoreboard.getLines().stream().map(s -> ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(board.getPlayer(), String.valueOf(s)))).collect(Collectors.toList()));
        }else {
            board.updateLines(fbScoreboard.getLines().stream().map(s -> ChatColor.translateAlternateColorCodes('&', String.valueOf(s))).collect(Collectors.toList()));
        }
    }

    private void setDefaultConfig(){
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("&bProfile");
        list.add("&f%player_name%");
        list.add("");
        list.add("&bRank");
        list.add("&f%vault_prefix_color%%vault_rank%");
        list.add("");
        list.add("&bServer");
        list.add("&f%cn_current_service_name%");
        list.add("");
        list.add("&bCoins");
        list.add("&f%vault_eco_balance_formatted%");
        list.add("");
        list.add("&bWebsite");
        list.add("&fYourSITE.eu");
        ConfigManager.setDef("scoreboard.title", "&3&lYourSITE.eu");
        ConfigManager.setDef("scoreboard.lines", list);
        ConfigManager.setDef("scoreboard.update_time", 1);
    }

    public static AdvancedScoreboardAPI getAPI() {
        return (api != null) ? api : new AdvancedScoreboardAPI();
    }
}
