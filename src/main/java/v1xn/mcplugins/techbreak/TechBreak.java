package v1xn.mcplugins.techbreak;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import v1xn.mcplugins.techbreak.Listeners.TBBLOCK;
import v1xn.mcplugins.techbreak.commands.tb;

public final class TechBreak extends JavaPlugin {
    public FileConfiguration config = this.getConfig();

    public boolean isTB = getConfig().getBoolean("isTB", false);

    public final String TB = "§dT§bB ";


    @Override
    public void onEnable() {
        saveDefaultConfig();

        getCommand("techbreak").setExecutor(new tb(this));

        getServer().getPluginManager().registerEvents(new TBBLOCK(this), this);
    }

    @Override
    public void onDisable() {

    }
}
