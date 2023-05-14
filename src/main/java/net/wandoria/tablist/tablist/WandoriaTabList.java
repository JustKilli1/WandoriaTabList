package net.wandoria.tablist.tablist;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentBuilder;
import net.kyori.adventure.text.format.TextColor;
import net.wandoria.tablist.shared.config.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.List;

public class WandoriaTabList {

    private ConfigManager config;
    private Scoreboard scoreboard;

    public WandoriaTabList(ConfigManager config) {
        this.config = config;
        scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
    }

    public String buildHeader(Player player) {
        List<String> header = config.getValues("header");
        player.setPlayerListHeader(build(header));
        return "";
    }

    public String buildFooter(Player player) {
        List<String> footer = config.getValues("footer");
        player.setPlayerListFooter(build(footer));
        return "";
    }

    public Team addNewTeam(String name, String prefix, String suffix, String order) {
        return null;
    }

    private String build(List<String> str) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < str.size(); i++) {
            String current = ChatColor.translateAlternateColorCodes('&', str.get(i));
            result.append(current);
            if(i + 1 < str.size()) result.append("\n");
        }
        return result.toString();
    }
}
