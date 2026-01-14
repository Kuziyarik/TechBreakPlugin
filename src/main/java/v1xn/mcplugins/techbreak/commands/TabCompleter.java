package v1xn.mcplugins.techbreak.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import v1xn.mcplugins.techbreak.TechBreak;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TabCompleter implements org.bukkit.command.TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args){
        List<String> completions = new ArrayList<>();

        if (args.length == 1){
            completions.addAll(Arrays.asList("on","off","add","remove","reload","rl"));
        } else if ("remove".equals(args[0])){
            TechBreak plugin = (TechBreak) Bukkit.getServer().getPluginManager().getPlugin("TechBreak");
            if(plugin != null){
                List<String> allowedUUIDs = plugin.config.getStringList("passed-uuids");
                for (String uuidStr : allowedUUIDs){
                    try {
                        UUID uuid = UUID.fromString(uuidStr);
                        OfflinePlayer p = Bukkit.getOfflinePlayer(uuid);
                        if(p.hasPlayedBefore()){
                            completions.add(p.getName());
                        }
                    }catch (IllegalArgumentException ignored){
                    }
                }
            }
        }else if (args.length == 2 && "add".equals(args[0])){
            for (Player p : Bukkit.getOnlinePlayers()){
                completions.add(p.getName());
            }
        }

        return completions.stream().filter(s -> s.toLowerCase().startsWith(args[args.length - 1].toLowerCase())).toList();
    }
}
