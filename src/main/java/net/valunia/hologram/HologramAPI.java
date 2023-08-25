package net.valunia.hologram;

import net.valunia.hologram.hologram.HologramManager;
import lombok.Getter;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class HologramAPI extends JavaPlugin {

    @Getter
    private static HologramManager hologramManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        hologramManager = new HologramManager(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static HologramManager getHologramManager(Plugin plugin) {
        if(hologramManager == null) {
            hologramManager = new HologramManager(plugin);
        }
        return hologramManager;
    }
}
