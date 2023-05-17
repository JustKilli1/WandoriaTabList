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

public class WandoriaTabList {

    private static final ILogger logger = new BaseLogger("TabList", new ConsolePrinter());
    private TabListConfig config;
    private Scoreboard scoreboard;

    public WandoriaTabList(TabListConfig config) {
        this.config = config;
        scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        addAllTeams();
    }

    /**
     * Builds the Header of the TabList
     * @param player The Player where the Header gets added
     * @return The Header as String
     * */
    public String buildHeader(Player player) {
        String header = build(config.getValues("header"));
        player.setPlayerListHeader(header);
        return header;
    }

    /**
     * Builds the Footer of the TabList
     * @param player The Player where the Footer gets added
     * @return The Footer as String
     * */
    public String buildFooter(Player player) {
        String footer = build(config.getValues("footer"));
        player.setPlayerListFooter(footer);
        return footer;
    }

    /**
     * Loads all available Groups from the Config and adds them as Teams to the Scoreboard
     * @return All Teams that got added to the Scoreboard
     * */
    private List<Team> addAllTeams() {
        List<Team> teams = new ArrayList<>();
        List<ViewableGroup> groups = config.getGroups();
        for(ViewableGroup group : groups) {
            if(!group.showInTab()) continue;
            teams.add(addNewTeam(group));
        }
        return teams;
    }

    /**
     * Adds a new Team to the Scoreboard
     * @param group The {@link ViewableGroup} that gets added as a new Team.
     * @return The Team that got added
     * */
    private Team addNewTeam(ViewableGroup group) {
        Team team = scoreboard.getTeam(group.order() + group.name());
        if(team == null) team = scoreboard.registerNewTeam(group.order() + group.name());
        team.setPrefix(group.prefix());
        team.setSuffix(group.suffix());
        return team;
    }

    /**
     * Adds a new Player to a Scoreboard Team.
     * @param player The player which gets added to a Team
     * @return An Optional with the Team the Player got added to. An Empty Optional if the Player got added to no Team
     * */
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

    /**
     * Updates the Scoreboard of all Online Players
     * */
    private void updatePlayerScoreboards() {
        for(Player target : Bukkit.getOnlinePlayers()) {
            target.setScoreboard(scoreboard);
        }
    }

    /**
     * Gets all Permission Groups that a specified Player has and Converts them into a List of {@link ViewableGroup}.
     * @param player The player from which all Groups get selected
     * @return A List with all Groups of the given Player as {@link ViewableGroup}
     * */
    private List<ViewableGroup> getPlayerGroups(Player player) {
        List<ViewableGroup> groups = config.getGroups();
        List<ViewableGroup> playerGroups = new ArrayList<>();
        for(ViewableGroup group : groups) {
            if(player.hasPermission("group." + group.name())) playerGroups.add(group);
        }
        return playerGroups;
    }

    /**
     * Gets the highest Group from a List of {@link ViewableGroup}.
     * @param groups The List from which the highest group gets selected
     * @return An Optional which contains the Highest Group or an Empty Optional if no Group was present in the given List
     * */
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

    /**
     * Builds the Footer or Header of the Tablist
     * @param content Content of the Header/Footer
     * @return The Footer/Header as String
     * */
    private String build(List<String> content) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < content.size(); i++) {
            String current = ChatColor.translateAlternateColorCodes('&', content.get(i));
            result.append(current);
            if(i + 1 < content.size()) result.append("\n");
        }
        return result.toString();
    }
}
