package v1xn.mcplugins.techbreak.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import v1xn.mcplugins.techbreak.TechBreak;

import java.util.List;
import java.util.UUID;

public class TBBLOCK implements Listener {
    private final TechBreak plugin;

    public TBBLOCK(TechBreak plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerLoginEvent event) {
        if (!plugin.isTB) return;

        UUID playerUUID = event.getPlayer().getUniqueId();
        List<String> allowedUUIDs = plugin.config.getStringList("passed-uuids");

        if (!allowedUUIDs.contains(playerUUID.toString())) {
            event.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, plugin.config.getString("tb-message", "§cSorry. The server is currently being serviced."));
        }
    }
}
