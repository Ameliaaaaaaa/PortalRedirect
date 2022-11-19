package me.amelia.portalredirect;

import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {}

    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent event) {
        PlayerTeleportEvent.TeleportCause portalType = event.getCause();

        Player player = event.getPlayer();

        World currentWorld = event.getFrom().getWorld();

        ConsoleCommandSender console = getServer().getConsoleSender();

        if (portalType == PlayerTeleportEvent.TeleportCause.NETHER_PORTAL) {
            if (currentWorld.getName().equals("world")) getServer().dispatchCommand(console, String.format("warp nether %s", player.getName()));
            if (currentWorld.getName().equals("world_nether")) getServer().dispatchCommand(console, String.format("spawn %s", player.getName()));
        }

        if (portalType == PlayerTeleportEvent.TeleportCause.END_PORTAL) {
            if (currentWorld.getName().equals("world")) getServer().dispatchCommand(console, String.format("warp end %s", player.getName()));
            if (currentWorld.getName().equals("world_the_end")) getServer().dispatchCommand(console, String.format("spawn %s", player.getName()));
        }

        event.setCancelled(true);
    }
}