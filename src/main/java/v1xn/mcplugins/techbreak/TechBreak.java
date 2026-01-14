package v1xn.mcplugins.techbreak;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import v1xn.mcplugins.techbreak.Listeners.TBBLOCK;
import v1xn.mcplugins.techbreak.commands.TabCompleter;
import v1xn.mcplugins.techbreak.commands.tb;

public final class TechBreak extends JavaPlugin {
    public FileConfiguration config = this.getConfig();

    public boolean isTB = getConfig().getBoolean("isTB", false);

    public final String TB = "[§x§0§0§C§B§F§F§lT§x§F§F§A§F§E§1§lB§r] ";


    @Override
    public void onEnable() {
        saveDefaultConfig();

        getCommand("techbreak").setExecutor(new tb(this));
        getCommand("techbreak").setTabCompleter(new TabCompleter());
        getServer().getPluginManager().registerEvents(new TBBLOCK(this), this);
    }

    @Override
    public void onDisable() {

    }
}
