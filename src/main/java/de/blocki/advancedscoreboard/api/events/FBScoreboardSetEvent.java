package de.blocki.advancedscoreboard.api.events;

import de.blocki.advancedscoreboard.fb.FastBoard;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class FBScoreboardSetEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private boolean cancelled;

    private final Player player;

    private final FastBoard board;

    public FBScoreboardSetEvent(Player bukkitPlayer, FastBoard fastBoard) {
        player = bukkitPlayer;
        board = fastBoard;
    }

    public Player getPlayer() {
        return player;
    }

    public FastBoard getBoard() {
        return board;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
