package com.nekozouneko.stream.atsumeplugin.task;

import com.nekozouneko.stream.atsumeplugin.AtsumePlugin;
import com.nekozouneko.stream.atsumeplugin.AtsumeStatus;
import com.nekozouneko.stream.atsumeplugin.AtsumeUtil;
import com.nekozouneko.stream.atsumeplugin.game.AtsumeGame;
import fr.minuskube.netherboard.Netherboard;
import fr.minuskube.netherboard.bukkit.BPlayerBoard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class updateScoreboard extends BukkitRunnable {
    private final AtsumePlugin plugin = AtsumePlugin.getInstance();
    Netherboard nb = AtsumePlugin.getNb();

    @Override
    public void run() {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            if (AtsumePlugin.getStatus() == AtsumeStatus.STARTED) {
                BPlayerBoard bpb;
                if (nb.getBoard(player) != null) {
                    bpb = nb.getBoard(player);
                } else {
                    bpb = nb.createBoard(player, "カボチャ集める大会");
                }

                bpb.clear();
                for (Player pl : plugin.getServer().getOnlinePlayers()) {
                    try {
                        bpb.set(pl.getName(), AtsumePlugin.score.get(pl.getUniqueId()));
                    } catch (NullPointerException e) {
                        bpb.set(pl.getName(), 0);
                    }
                }
            } else if (AtsumePlugin.getStatus() == AtsumeStatus.STOPPED){
                if (nb.getBoard(player) != null) {
                    nb.removeBoard(player);
                }
            }
        }
    }
}
