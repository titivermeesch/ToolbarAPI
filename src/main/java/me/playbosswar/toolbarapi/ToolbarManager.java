package me.playbosswar.toolbarapi;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nullable;
import javax.naming.NameAlreadyBoundException;
import java.util.*;

public class ToolbarManager {
    private final List<Toolbar> toolbars = new ArrayList<>();
    private final Map<Player, Toolbar> activeToolbars = new HashMap<>();

    public ToolbarManager(Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(new ToolbarListener(this), plugin);
    }

    public void addToolbar(Toolbar toolbar) throws NameAlreadyBoundException {
        String toolbarName = toolbar.getName();
        if (getToolbar(toolbarName) != null) {
            throw new NameAlreadyBoundException("A toolbar is already registered with the name " + toolbarName);
        }

        toolbars.add(toolbar);
    }

    public void setToolbar(Player p, String toolbarName) {
        Toolbar toolbar = getToolbar(toolbarName);

        if (toolbar == null) {
            Bukkit.getLogger().warning("Tried to access toolbar that doesn't exist: " + toolbarName);
            return;
        }

        activeToolbars.put(p, toolbar);
        toolbar.load(p);
    }

    @Nullable
    public Toolbar getToolbar(String name) {
        Optional<Toolbar> toolbar = toolbars.stream().filter(t -> t.getName().equals(name)).findFirst();

        if (toolbar.isEmpty()) {
            return null;
        }

        return toolbar.get();
    }

    @Nullable
    public Toolbar getPlayerToolbar(Player p) {
        return activeToolbars.get(p);
    }
}
