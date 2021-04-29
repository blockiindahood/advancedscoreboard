package de.blocki.advancedscoreboard.listener;

import de.blocki.advancedscoreboard.fb.FastBoard;
import de.blocki.advancedscoreboard.main.Main;
import de.blocki.advancedscoreboard.utils.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinLeave implements Listener {

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        Player player = event.getPlayer();

        FastBoard board = new FastBoard(player);

        board.updateTitle(Main.fbScoreboard.getTitle().replace("&", "§"));

        Main.boards.put(player.getUniqueId(), board);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        FastBoard board = Main.boards.remove(player.getUniqueId());

        if (board != null) {
            board.delete();
        }
    }

}
