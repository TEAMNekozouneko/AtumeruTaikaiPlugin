package com.nekozouneko.stream.atsumeplugin.command;

import com.nekozouneko.stream.atsumeplugin.AtsumePlugin;
import com.nekozouneko.stream.atsumeplugin.AtsumeStatus;
import com.nekozouneko.stream.atsumeplugin.game.AtsumeGame;
import com.nekozouneko.stream.atsumeplugin.game.TaikaiManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StartCommand implements CommandExecutor, TabCompleter {

    private final AtsumePlugin plugin = AtsumePlugin.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        AtsumePlugin.setStatus(AtsumeStatus.STARTED);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return Collections.singletonList("<title>");
    }
}
