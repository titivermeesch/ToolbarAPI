package me.playbosswar.toolbarapi;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ToolbarListener implements Listener {
    private final ToolbarManager toolbarManager;

    public ToolbarListener(ToolbarManager toolbarManager) {
        this.toolbarManager = toolbarManager;
    }

    @EventHandler
    public void onToolbarClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Toolbar activeToolbar = toolbarManager.getPlayerToolbar(p);

        if (activeToolbar == null) {
            return;
        }

        boolean handled = activeToolbar.handleClick(e, p.getInventory().getHeldItemSlot(), p);
        e.setCancelled(handled);
    }
}
