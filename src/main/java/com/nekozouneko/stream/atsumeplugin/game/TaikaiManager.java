package com.nekozouneko.stream.atsumeplugin.game;

import com.nekozouneko.stream.atsumeplugin.AtsumePlugin;
import com.nekozouneko.stream.atsumeplugin.AtsumeStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TaikaiManager {

    private static final Map<UUID, AtsumeGame> games;

    static {
        games = new HashMap<>();

    }

    private TaikaiManager() {}

    public static UUID startGame(AtsumePlugin plugin, String title) {
        if (games.size() != 0) {
            return null;
        }

        AtsumeGame game = new AtsumeGame(title, null, new HashMap<>());
        game.setType(AtsumePlugin.Type.valueOf(plugin.getConfig().getString("on", "MINE")));

        games.put(game.getId(), game);
        return game.getId();
    }

    public static AtsumeGame getGame(UUID id) {
        return games.get(id);
    }

    public static Map<UUID, AtsumeGame> getGames() {
        return TaikaiManager.games;
    }

}
