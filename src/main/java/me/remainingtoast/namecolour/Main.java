package me.remainingtoast.namecolour;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.remainingtoast.namecolour.commands.AddNameColour;
import me.remainingtoast.namecolour.commands.NameColour;
import me.remainingtoast.namecolour.commands.NameColourCheck;
import me.remainingtoast.namecolour.commands.ToggleNameColour;
import me.remainingtoast.namecolour.util.Checker;
import me.remainingtoast.namecolour.util.PlayerWrapper;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.UUID;

public final class Main extends JavaPlugin {

    public static BukkitScheduler SCHEDULER
            = Bukkit.getScheduler();

    public static Gson GSON
            = new GsonBuilder().setPrettyPrinting().excludeFieldsWithModifiers(Modifier.FINAL).create();

    public static HashMap<String, PlayerWrapper> DATA
            = new HashMap<>();

    public static Main INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;

        getServer().getPluginCommand("namecolour").setExecutor(new NameColour());
        getServer().getPluginCommand("namecolourcheck").setExecutor(new NameColourCheck());
        getServer().getPluginCommand("addnamecolour").setExecutor(new AddNameColour());
        getServer().getPluginCommand("togglenamecolour").setExecutor(new ToggleNameColour());
        getServer().getPluginManager().registerEvents(new Checker(), this);

        SCHEDULER.scheduleSyncRepeatingTask(this, new Checker(), 0L, 72000L);
    }

}
