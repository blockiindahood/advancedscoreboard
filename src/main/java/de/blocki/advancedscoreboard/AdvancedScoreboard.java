package de.blocki.advancedscoreboard;

import de.blocki.advancedscoreboard.api.AdvancedScoreboardAPI;
import de.blocki.advancedscoreboard.commands.ASCommand;
import de.blocki.advancedscoreboard.fb.FBScoreboard;
import de.blocki.advancedscoreboard.fb.FastBoard;
import de.blocki.advancedscoreboard.listener.PlayerJoinLeave;
import de.blocki.advancedscoreboard.utils.ConfigManager;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public final class AdvancedScoreboard extends JavaPlugin {

    public static final Map<UUID, FastBoard> boards = new HashMap<>();
    public static FBScoreboard fbScoreboard;
    private static Plugin plugin;
    private static AdvancedScoreboardAPI api;
    private static ConfigManager config;

    private static String pluginPrefix;

    @Override
    public void onEnable() {
        plugin = this;
        setDefaultConfig();
        api = new AdvancedScoreboardAPI();

        //create new temp scorebaord
        fbScoreboard = new FBScoreboard()
                .setLines(config.getList("scoreboard.lines"))
                .setTitle(config.getString("scoreboard.title", true))
                .setUpdateTime(Integer.parseInt(config.get("scoreboard.update_time")));

        //set plugin prefix
        pluginPrefix = config.getString("messages.prefix", true) + " ";

        //register plugins
        getServer().getPluginManager().registerEvents(new PlayerJoinLeave(), this);

        //register commands
        getCommand("as").setExecutor(new ASCommand());


        //set scorevoard for each player online
        getServer().getOnlinePlayers().forEach(player -> {
            //creates new FastBoard
            FastBoard board = new FastBoard(player);

            //set the config title
            board.updateTitle(fbScoreboard.getTitle());

            //sets the board in main cache
            AdvancedScoreboard.boards.put(player.getUniqueId(), board);
        });

        //update scoreboard for each player online
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

    public static void loadNewScoreboardFromConfig(){
        config = new ConfigManager("config.yml");
        fbScoreboard = new FBScoreboard()
                .setLines(config.getList("scoreboard.lines"))
                .setTitle(config.getString("scoreboard.title", true))
                .setUpdateTime(Integer.parseInt(config.get("scoreboard.update_time")));
    }

    private void setDefaultConfig(){
        config = new ConfigManager("config.yml");
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("&bProfile");
        list.add("&f%player_name%");
        list.add("");
        list.add("&bRank");
        list.add("&f%vault_prefix_color%%vault_rank%");
        list.add("");
        list.add("&bServer");
        list.add("&fServer1");
        list.add("");
        list.add("&bCoins");
        list.add("&f%vault_eco_balance_formatted%");
        list.add("");
        list.add("&bWebsite");
        list.add("&fYourSITE.eu");
        config.setDef("scoreboard.title", "&3&lYourSITE.eu");
        config.setDef("scoreboard.lines", list);
        config.setDef("scoreboard.update_time", 1);

        config.setDef("messages.prefix", "&7[&6AS&7]");
        config.setDef("messages.no_perm", "&7You &cdon't &7have permission to execute this command!");
        config.setDef("messages.reload.success", "&7Scoreboard reloaded &5successfully &7for all online players.");
    }

    private void updateBoard(FastBoard board, boolean isPAPI){
        board.updateTitle(fbScoreboard.getTitle());
        if(isPAPI){
            board.updateLines(fbScoreboard.getLines().stream().map(s -> ChatColor.translateAlternateColorCodes('&', PlaceholderAPI.setPlaceholders(board.getPlayer(), String.valueOf(s)))).collect(Collectors.toList()));
        }else {
            board.updateLines(fbScoreboard.getLines().stream().map(s -> ChatColor.translateAlternateColorCodes('&', String.valueOf(s))).collect(Collectors.toList()));
        }
    }

    public static AdvancedScoreboardAPI getAPI() {
        return (api != null) ? api : new AdvancedScoreboardAPI();
    }

    public static ConfigManager getPluginConfig() {
        return config;
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public static String getPluginPrefix() {
        return pluginPrefix;
    }
}
