package me.remainingtoast.namecolour;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class NameColourMain extends JavaPlugin {

    public static NameColourMain INSTANCE;

    private File playerDataFile;
    private YamlConfiguration playerDataYaml;

    @Override
    public void onEnable() {
        INSTANCE = this;
        initiateFiles();

        getServer().getPluginManager().registerEvents(new Listener(), this);
        getServer().getPluginCommand("namecolour").setExecutor(new NameColourCmd());
        getServer().getPluginCommand("namecolourcheck").setExecutor(new NameColourCheckCmd());
    }

    @Override
    public void onDisable() {
        savePlayerData();
    }

    public File getPlayerData() {
        return playerDataFile;
    }

    public YamlConfiguration getPlayerDataYaml() {
        return playerDataYaml;
    }

    public void savePlayerData() {
        try { playerDataYaml.save(getPlayerData()); }
        catch (IOException ignored) {}
    }

    private void initiateFiles() {
        playerDataFile = new File(getDataFolder(), "players.yml");
        if (!playerDataFile.exists()) {
            try { playerDataFile.createNewFile(); }
            catch (IOException ignored) {}
        }
        saveDefaultConfig();
        playerDataYaml = YamlConfiguration.loadConfiguration(playerDataFile);
    }

    public ConfigurationSection getPlayerSection(Player player) {
        return playerDataYaml.getConfigurationSection(player.getUniqueId().toString());
    }
}
