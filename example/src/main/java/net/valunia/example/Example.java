package net.valunia.example;

import net.valunia.hologram.HologramAPI;
import net.valunia.hologram.hologram.TextAnimation;
import net.valunia.hologram.hologram.TextHologram;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Example extends JavaPlugin implements Listener {

    private TextHologram hologram;

    @Override
    public void onEnable() {
        // Plugin startup logic

        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                if(!event.getMessage().startsWith("!")) return;
                onTestCommand(event.getMessage().toLowerCase().replace("!", ""), event.getPlayer());
            }
        });
    }


    private void onTestCommand(String command, Player player) {
        switch (command) {
            case "spawn" -> {
                if(hologram != null && hologram.getDisplay() != null) hologram.kill();
              hologram = spawnHologram(player.getLocation());
              player.sendMessage("spawned");
            }
            case "kill" -> {
                hologram.kill();
            }
            case "animate" -> {
                TextAnimation animation = new TextAnimation()
                        .addFrame(ChatColor.RED + "First frame")
                        .addFrame("Second frame")
                        .addFrame("Third frame\nSecond line")
                        .addFrame("Last frame");
                HologramAPI.getHologramManager().applyAnimation(this.hologram, animation);
            }
            case "stopanimation" -> {
                HologramAPI.getHologramManager().cancelAnimation(this.hologram);
            }
            case "killall" -> {
                HologramAPI.getHologramManager().removeAll("test_hologram");
            }
            case "bigger" -> {
                hologram.setSize(5, 5, 5);
                hologram.update();
            }
            case "smaller" -> {
                hologram.setSize(0.5F, 0.5F, 0.5F);
                hologram.update();
            }
            case "normal" -> {
                hologram.setSize(1.5F,1.5F,1.5F);
                hologram.update();
            }
        }
    }

    private TextHologram spawnHologram(Location location) {
        return new TextHologram("test_hologram")
                .setText("§6Valunia.net")
                .addLine("§8§m-----------")
                .addLine("§5Test")
                .setBillboard(Display.Billboard.VERTICAL)
                .setTextShadow(true)
                .setSize(2, 2, 2)
                .setTextOpacity((byte) 200)
                .setBackgroundColor(Color.fromARGB(0, 255, 236, 222))
                .spawn(location);
    }

}
