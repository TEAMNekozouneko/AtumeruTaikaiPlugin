package com.nekozouneko.stream.atsumeplugin.game;

import com.nekozouneko.stream.atsumeplugin.AtsumePlugin;

import java.util.Map;
import java.util.UUID;

public class AtsumeGame {

    public static class Result {
        private final UUID id;
        private final String title;
        private final String type;

        public Result(UUID id, String title, String type) {
            this.id = id;
            this.title = title;
            this.type = type;
        }

        public Result(UUID id, String title, AtsumePlugin.Type type) {
            this.id = id;
            this.title = title;
            this.type = type.name();
        }
    }

    private String title;
    private AtsumePlugin.Type type;

    private Map<UUID, Long> scores;
    private final UUID id = UUID.randomUUID();

    public AtsumeGame(String title, String type, Map<UUID, Long> scores) {
        this.title = title;
        this.type = AtsumePlugin.Type.valueOf(type.toUpperCase());
        this.scores = scores;
    }

    public UUID getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public AtsumePlugin.Type getType() {
        return this.type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(AtsumePlugin.Type type) {
        this.type = type;
    }

    public void putScore(UUID id, long l) {
        scores.put(id, l);
    }

    public long getScore(UUID id) {
        try {
            return scores.get(id);
        } catch (Exception e) {
            return 0L;
        }
    }
}
