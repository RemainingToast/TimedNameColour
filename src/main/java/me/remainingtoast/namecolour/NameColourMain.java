package me.remainingtoast.namecolour;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Modifier;

public final class NameColourMain extends JavaPlugin {

    public static NameColourMain INSTANCE;
    public static Gson GSON = new GsonBuilder().setPrettyPrinting().excludeFieldsWithModifiers(Modifier.FINAL).create();

    @Override
    public void onEnable() {
        INSTANCE = this;
        getServer().getPluginManager().registerEvents(new Listener(), this);
        getServer().getPluginCommand("namecolour").setExecutor(new NameColourCmd());
        getServer().getPluginCommand("namecolourcheck").setExecutor(new NameColourCheckCmd());
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Checker(), 0L, 20L);
    }

    @Override
    public void onDisable() {

    }
}
