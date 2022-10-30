package com.nekozouneko.stream.atsumeplugin;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.Map;

public class AtsumeConfig {

    private String ON;
    private List<String> TARGET;

    private boolean LEADER_ENABLED;
    private int LEADER_MIN;
    private String LEADER_MESSAGE;
    private List<String> LEADER_EXCLUDE;

    private boolean ACTIONBAR_ENABLED;
    private String ACTIONBAR_MESSAGE;

    private boolean SCOREBOARD_ENABLED;
    private List<String> SCOREBOARD_SHOW;

    public AtsumeConfig(
            String on, List<String> target,
            boolean leaderEnabled, int leaderMin, String leaderMessage, List<String> leaderExclude,
            boolean actionbarEnabled, String actionbarMessage,
            boolean scoreboardEnabled, List<String> scoreboardShow
    )
    {
        this.ON = on;
        this.TARGET = target;

        this.LEADER_ENABLED  = leaderEnabled;
        this.LEADER_MIN = leaderMin;
        this.LEADER_MESSAGE = leaderMessage;
        this.LEADER_EXCLUDE = leaderExclude;

        this.ACTIONBAR_ENABLED = actionbarEnabled;
        this.ACTIONBAR_MESSAGE = actionbarMessage;

        this.SCOREBOARD_ENABLED = scoreboardEnabled;
        this.SCOREBOARD_SHOW = scoreboardShow;
    }

    public AtsumeConfig(FileConfiguration conf) {
        this.ON = conf.getString("on", "Mine");
        this.TARGET = AtsumeUtil.toUpperList(conf.getStringList("target"));

        this.LEADER_ENABLED  = conf.getBoolean("leader.enabled", false);
        this.LEADER_MIN = conf.getInt("leader.min", 3);
        this.LEADER_MESSAGE = conf.getString("message", "&c%player%&rが&c%score%&r%unit%で&c%unit%リーダー&rとなりました。");
        this.LEADER_EXCLUDE = conf.getStringList("leader.exclude");

        this.ACTIONBAR_ENABLED = conf.getBoolean("actionbar.enabled", false);
        this.ACTIONBAR_MESSAGE = conf.getString("actionbar.message", "現在のあなたのスコア: %score% %unit%");

        this.SCOREBOARD_ENABLED = conf.getBoolean("scoreboard.enabled", false);
        this.SCOREBOARD_SHOW = conf.getStringList("scoreboard.show");
    }

    /**
     * @todo はよ作れ
      * @param map
     */
    public void mapping(Map<String, Object> map) {
        this.ON = (String) map.get("on");
    }

    public void commit(FileConfiguration conf) {
        conf.set("on", this.ON);
        conf.set("target", this.TARGET);
        conf.set("leader.enabled", this.LEADER_ENABLED);
        conf.set("leader.min", this.LEADER_MIN);
        conf.set("leader.message", this.LEADER_MESSAGE);
        conf.set("leader.exclude", this.LEADER_EXCLUDE);
        conf.set("actionbar.enabled", this.ACTIONBAR_ENABLED);
        conf.set("actionbar.message", this.ACTIONBAR_MESSAGE);
        conf.set("scoreboard.enabled", this.SCOREBOARD_ENABLED);
        conf.set("scoreboard.show", this.SCOREBOARD_SHOW);
    }

}
