package de.blocki.advancedscoreboard.api;

import de.blocki.advancedscoreboard.fb.FastBoard;
import de.blocki.advancedscoreboard.main.AdvancedScoreboard;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public class AdvancedScoreboardAPI {

    public void updateTitleForPlayer(Player player, String title){
        UUID uuid = player.getUniqueId();
        Map<UUID, FastBoard> boards = AdvancedScoreboard.boards;
        FastBoard fb = boards.get(uuid);
        fb.updateTitle(title);
        boards.put(uuid, fb);
    }

    public void removeScoreboard(Player player){
        FastBoard board = AdvancedScoreboard.boards.remove(player.getUniqueId());

        if (board != null) {
            board.delete();
        }
    }

    public void setScoreboard(Player player){
        //creates new FastBoard
        FastBoard board = new FastBoard(player);

        //set the config title
        board.updateTitle(AdvancedScoreboard.fbScoreboard.getTitle().replace("&", "§"));

        //sets the board in main cache
        AdvancedScoreboard.boards.put(player.getUniqueId(), board);
    }

}
