package com.nekozouneko.stream.atsumeplugin;

import com.nekozouneko.stream.atsumeplugin.command.EndCommand;
import com.nekozouneko.stream.atsumeplugin.command.RankingCommand;
import com.nekozouneko.stream.atsumeplugin.command.StartCommand;
import com.nekozouneko.stream.atsumeplugin.game.AtsumeGame;
import com.nekozouneko.stream.atsumeplugin.listener.onDestroyedBlock;
import com.nekozouneko.stream.atsumeplugin.task.updateScoreboard;
import fr.minuskube.netherboard.Netherboard;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public final class AtsumePlugin extends JavaPlugin {

    private static AtsumePlugin instance = null;
    private final static Netherboard nb = Netherboard.instance();
    public static final AtsumePlugin.Type type = Type.MINE;
    public static final Map<UUID, Integer> score = new HashMap<>();
    private BukkitRunnable us;
    private static AtsumeGame mainGame;
    private static AtsumeStatus status = AtsumeStatus.STOPPED;

    public static AtsumePlugin getInstance() {return instance;}
    public static Netherboard getNb() {return AtsumePlugin.nb;}
    public static AtsumeGame getMainGame() {return AtsumePlugin.mainGame;}
    public static AtsumeStatus getStatus() {return AtsumePlugin.status;}
    public static void setStatus(AtsumeStatus stat) {AtsumePlugin.status = stat;}

    public enum Type {
        CRAFT,
        MINE,
        KILL,
        PLACE
    }

    @Override
    public void onEnable() {
        AtsumePlugin.instance = this;
        us = new updateScoreboard();
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);

        getCommand("start").setExecutor(new StartCommand());
        getCommand("ranking").setExecutor(new RankingCommand());
        getCommand("end").setExecutor(new EndCommand());
        getServer().getPluginManager().registerEvents(new onDestroyedBlock(), this);
        us.runTaskTimer(this, 5, 5);
    }

    @Override
    public void onDisable() {
        us = null;
        for (Player p : Bukkit.getOnlinePlayers()) {
            try {
                nb.deleteBoard(p);
            } catch (Exception ignored) {}
        }
    }

    public static void putScore(UUID uuid, int score) {
        AtsumePlugin.score.put(uuid, score);
    }

    public static int getScore(UUID id) {
        try {
            return AtsumePlugin.score.get(id);
        } catch (Exception e) {
            return 0;
        }
    }

}
