package net.wandoria.tablist.tablist;

import net.wandoria.tablist.shared.config.TabListConfig;
import net.wandoria.tablist.shared.logging.ILogger;
import net.wandoria.tablist.shared.logging.LogLevel;
import net.wandoria.tablist.shared.logging.loggers.BaseLogger;
import net.wandoria.tablist.shared.logging.output.ConsolePrinter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class WandoriaTabList {

    private static final ILogger logger = new BaseLogger("TabList", new ConsolePrinter());
    private TabListConfig config;
    private Scoreboard scoreboard;

    public WandoriaTabList(TabListConfig config) {
        this.config = config;
        scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        addAllTeams();
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

    private List<Team> addAllTeams() {
        List<Team> teams = new ArrayList<>();
        List<ViewableGroup> groups = config.getGroups();
        for(ViewableGroup group : groups) {
            System.out.println("Name: " + group.name());
            System.out.println("ShowInTab: " + group.showInTab());
            System.out.println("Prefix: " + group.prefix());
            System.out.println("Suffix: " + group.suffix());
            if(!group.showInTab()) continue;
            System.out.println("Name: " + group.name());
            teams.add(addNewTeam(group));
        }
        return teams;
    }

    private Team addNewTeam(ViewableGroup group) {
        Team team = scoreboard.getTeam(group.order() + group.name());
        if(team == null) team = scoreboard.registerNewTeam(group.order() + group.name());
        team.setPrefix(group.prefix());
        team.setSuffix(group.suffix());
        return team;
    }

    public Optional<Team> addPlayerToTeam(Player player) {
        Optional<ViewableGroup> groupOpt = getHighestGroup(getPlayerGroups(player));
        if(groupOpt.isEmpty()) return Optional.empty();
        ViewableGroup group = groupOpt.get();
        if(!group.showInTab()) return Optional.empty();
        Team team = scoreboard.getTeam(group.order() + group.name());
        if(team == null) {
            logger.log(LogLevel.ERROR, "Could not find a Team with the name " + group.order() + group.name(), new Exception());
            return Optional.empty();
        }
        team.addEntry(player.getName());
        updatePlayerScoreboards();
        return Optional.ofNullable(team);
    }

    private void updatePlayerScoreboards() {
        for(Player target : Bukkit.getOnlinePlayers()) {
            target.setScoreboard(scoreboard);
        }
    }

    private List<ViewableGroup> getPlayerGroups(Player player) {
        List<ViewableGroup> groups = config.getGroups();
        List<ViewableGroup> playerGroups = new ArrayList<>();
        for(ViewableGroup group : groups) {
            if(player.hasPermission("group." + group.name())) playerGroups.add(group);
        }
        return playerGroups;
    }

    private Optional<ViewableGroup> getHighestGroup(List<ViewableGroup> groups) {
        ViewableGroup highestGroup = null;
        for(ViewableGroup group : groups) {
            if(!group.showInTab()) continue;
            if(highestGroup == null) {
                highestGroup = group;
                continue;
            }
            if(highestGroup.order() < group.order()) highestGroup = group;
        }
        return Optional.ofNullable(highestGroup);
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
