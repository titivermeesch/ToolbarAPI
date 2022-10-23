package me.playbosswar.toolbarapi;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class ClickableItem {
    private ItemStack item;
    private final Consumer<PlayerInteractEvent> consumer;

    public ClickableItem(ItemStack item, Consumer<PlayerInteractEvent> consumer) {
        this.item = item;
        this.consumer = consumer;
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public Consumer<PlayerInteractEvent> getConsumer() {
        return consumer;
    }
}
