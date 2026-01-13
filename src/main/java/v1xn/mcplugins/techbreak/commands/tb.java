package v1xn.mcplugins.techbreak.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import v1xn.mcplugins.techbreak.TechBreak;

import java.util.List;
import java.util.UUID;

public class tb implements CommandExecutor {
    private final TechBreak plugin;

    public tb(TechBreak plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (args.length == 0) {
            sender.sendMessage(plugin.TB + "§cUsage: /tb <add|on|off|reload>");
            return true;
        }

        String sub = args[0].toLowerCase();

        if (args.length > 2) {
            sender.sendMessage(plugin.TB + "§cUsage: /tb add | on | off | reload | rl");
            return true;
        }

        // ===RELOAD===
        if (sub.equals("reload") || sub.equals("rl")) {
            if (args.length == 1) {
                if (!sender.hasPermission("v1x.tb.reload")) {
                    sender.sendMessage(plugin.TB + "§cYou do not have permission to use this command.");
                    return true;
                } else {
                    plugin.reloadConfig();
                    sender.sendMessage(plugin.TB + "§aConfig reloaded!");
                    return true;
                }
            } else {
                sender.sendMessage(plugin.TB + "§cUsage: /tb reload | rl");
                return true;
            }
        }

        // ===TOGGLE===
        if (sub.equals("on") || sub.equals("off")) {
            if (!sender.hasPermission("v1x.tb.toggle")) {
                sender.sendMessage(plugin.TB + "§cYou do not have permission to use this command.");
                return true;
            }
            if (args.length != 1) {
                sender.sendMessage(plugin.TB + "§cUsage: /tb on | off");
                return true;
            }

            plugin.isTB = sub.equals("on");
            if (plugin.config.getBoolean("save-tb-state")) {
                plugin.config.set("isTB", plugin.isTB);
                plugin.saveConfig();
            }

            sender.sendMessage(plugin.TB + "§aTechBreak mode is now " + (plugin.isTB ? "ON" : "OFF"));

            if (plugin.isTB) {
                List<String> allowed = plugin.config.getStringList("passed-uuids");
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (!allowed.contains(p.getUniqueId().toString())) {
                        p.kick(Component.text(plugin.config.getString("tb-message", "§cServer under maintenance.")));
                    }
                }
            }
            return true;
        }

        // ===ADD===
        if (sub.equals("add")) {
            if (args.length != 2) {
                sender.sendMessage(plugin.TB + "§cUsage: /tb add <player>");
                return true;
            }
            if (!sender.hasPermission("v1x.tb.add")) {
                sender.sendMessage(plugin.TB + "§cYou do not have permission to use this command.");
                return true;
            } else {
                String playerName = args[1];
                Player target = Bukkit.getPlayerExact(playerName);
                if (target != null) {
                    UUID targetUUID = target.getUniqueId();
                    List<String> UUIDs = plugin.config.getStringList("passed-uuids");
                    if (UUIDs.contains(targetUUID.toString())) {
                        sender.sendMessage(plugin.TB + "§cPlayer §e" + playerName + " §calready in whitelist!");
                        return true;
                    }
                    UUIDs.add(targetUUID.toString());
                    plugin.config.set("passed-uuids", UUIDs);
                    plugin.saveConfig();
                    sender.sendMessage(plugin.TB + "§aPlayer §e" + playerName + "§a added to whitelist.");
                    return true;
                } else {
                    sender.sendMessage(plugin.TB + "§cPlayer not online!");
                    return true;
                }
            }
        }
        sender.sendMessage(plugin.TB + "§cUnknown subcommand. Use: add, on, off, reload");
        return true;
    }
}