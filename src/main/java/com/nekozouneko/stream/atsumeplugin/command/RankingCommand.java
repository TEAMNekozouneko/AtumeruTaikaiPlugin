package com.nekozouneko.stream.atsumeplugin.command;

import com.nekozouneko.stream.atsumeplugin.AtsumePlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;

public class RankingCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        final Map<UUID, Integer> scores = AtsumePlugin.score;
        final List<String> scoreArr = new ArrayList<>();
        for (Player p : Bukkit.getOnlinePlayers()) {
            int s = 0;
            try {
                s = scores.get(p.getUniqueId());
            } catch (Exception ignored) {}

            scoreArr.add(s + ":" + p.getName());
        }

        Collections.sort(scoreArr);
        int r = 1;

        for (String sa : scoreArr) {
            String[] s = sa.split(":");
            Bukkit.broadcastMessage("§7[§a" + r + "位§7] §e" + s[1] + " (" + s[0] + "個)");

            r++;
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return new ArrayList<>();
    }
}
