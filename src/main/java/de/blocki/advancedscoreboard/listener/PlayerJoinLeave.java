package de.blocki.advancedscoreboard.listener;

import de.blocki.advancedscoreboard.api.events.FBScoreboardRemoveEvent;
import de.blocki.advancedscoreboard.api.events.FBScoreboardSetEvent;
import de.blocki.advancedscoreboard.fb.FastBoard;
import de.blocki.advancedscoreboard.main.AdvancedScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinLeave implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        //gets Player from event
        Player player = event.getPlayer();

        //creates new FastBoard
        FastBoard board = new FastBoard(player);

        //call the FBScoreboardSetEvent
        FBScoreboardSetEvent e = new FBScoreboardSetEvent(player, board);
        Bukkit.getServer().getPluginManager().callEvent(e);

        if(!e.isCancelled()) {

            //set the config title
            board.updateTitle(AdvancedScoreboard.fbScoreboard.getTitle().replace("&", "§"));

            //sets the board in main cache
            AdvancedScoreboard.boards.put(player.getUniqueId(), board);

        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        //gets Player from event
        Player player = event.getPlayer();

        //gets FB from list
        FastBoard board = AdvancedScoreboard.boards.remove(player.getUniqueId());

        //call the FBScoreboardRemoveEvent
        FBScoreboardRemoveEvent e = new FBScoreboardRemoveEvent(player, board);
        Bukkit.getServer().getPluginManager().callEvent(e);

        //if the board is not null and the event not cancelled, it is getting deleted
        if (board != null && !e.isCancelled()) {
            board.delete();
        }
    }

}
