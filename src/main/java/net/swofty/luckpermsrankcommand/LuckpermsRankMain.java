package net.swofty.luckpermsrankcommand;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Listener;
import org.bukkit.command.CommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.configuration.file.FileConfiguration;
import java.io.File;
import org.bukkit.plugin.java.JavaPlugin;

public final class LuckpermsRankMain extends JavaPlugin
{
    private static LuckpermsRankMain instance;
    public static LuckpermsRankMain plugin;
    private File configFile;
    private FileConfiguration config;
    PluginManager pm;

    public LuckpermsRankMain() {
        this.pm = Bukkit.getPluginManager();
    }

    public void onEnable() {
        LuckpermsRankMain.plugin = this;
        this.registerCommands();
        this.createConfigFile();
    }

    public void registerCommands() {
        this.getCommand("rank").setExecutor((CommandExecutor)new RankCommand(this));
    }

    public static LuckpermsRankMain getInstance() {
        return LuckpermsRankMain.instance;
    }

    public static LuckpermsRankMain getPlugin() {
        return (LuckpermsRankMain)getPlugin((Class)LuckpermsRankMain.class);
    }

    private void createConfigFile() {
        this.configFile = new File(this.getDataFolder(), "config.yml");
        if (!this.configFile.exists()) {
            Log.info("********************************************************");
            Log.warn("File config.yml was not found, creating a new one!");
            Log.info("*********************************************************");
            this.configFile.getParentFile().mkdirs();
            this.saveResource("config.yml", false);
            Log.info("********************************************************");
            Log.success("File config.yml was successfully created!");
            Log.info("*********************************************************");
        }
        this.config = (FileConfiguration)new YamlConfiguration();
    }
}