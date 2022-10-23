package me.playbosswar.toolbarapi;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class Toolbar {
    private final String name;
    private final Map<Integer, ClickableItem> items = new HashMap<>();

    public Toolbar(String name) {
        this.name = name;
    }

    public void addItem(int slot, ClickableItem item) {
        items.put(slot, item);
    }

    @Nullable
    public ClickableItem getItem(int slot) {
        return items.get(slot);
    }

    public String getName() {
        return name;
    }

    public void load(Player p, boolean clearInventory) {
        if (clearInventory) {
            p.getInventory().clear();
        }

        items.forEach((slot, item) -> {
            p.getInventory().setItem(slot, item.getItem());
        });
    }

    public void load(Player p) {
        load(p, true);
    }

    public boolean handleClick(PlayerInteractEvent e, int slot, Player p) {
        ClickableItem item = getItem(slot);

        if (item == null) {
            return false;
        }

        item.getConsumer().accept(e);
        return true;
    }
}
