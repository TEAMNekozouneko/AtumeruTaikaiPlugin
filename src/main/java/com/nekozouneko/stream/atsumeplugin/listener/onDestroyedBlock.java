package com.nekozouneko.stream.atsumeplugin.listener;

import com.nekozouneko.stream.atsumeplugin.AtsumePlugin;
import com.nekozouneko.stream.atsumeplugin.AtsumeStatus;
import com.nekozouneko.stream.atsumeplugin.game.AtsumeGame;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class onDestroyedBlock implements Listener {

    private final AtsumePlugin plugin = AtsumePlugin.getInstance();

    @EventHandler ( ignoreCancelled = true )
    public void onDestroy(BlockBreakEvent e) {
        final Player breaker = e.getPlayer();
        if (AtsumePlugin.getStatus() != AtsumeStatus.STARTED || breaker == null) {return;}


        if (AtsumePlugin.type == AtsumePlugin.Type.MINE && e.getBlock().getType() == Material.PUMPKIN) {
            AtsumePlugin.score.put(breaker.getUniqueId(), AtsumePlugin.getScore(breaker.getUniqueId())+1);
            e.setDropItems(false);
        }
    }

}
