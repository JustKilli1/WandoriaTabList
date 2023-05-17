package net.wandoria.tablist.shared.config;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.group.Group;
import net.wandoria.tablist.shared.Utils;
import net.wandoria.tablist.tablist.ViewableGroup;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TabListConfig extends ConfigManager{
    private static final String groupRootPath = "groups.";
    private LuckPerms luckPerms;

    public TabListConfig(Plugin plugin, LuckPerms luckPerms) {
        super(plugin, "tab_list.yml");
        this.luckPerms = luckPerms;
        createDefaultConfig();
    }

    /**
     * Create the Default Configuration
     * */
    private void createDefaultConfig() {
        List<String> header = List.of("line1", "line2");
        List<String> footer = List.of("line1", "line2");
        addDefault("header", header);
        addDefault("footer", footer);

        Set<Group> permGroups = luckPerms.getGroupManager().getLoadedGroups();
        for(Group permGroup : permGroups) {
            String groupPath = groupRootPath + permGroup.getName();
            addDefault(new ConfigValue(groupPath + ".name", permGroup.getName()));
            addDefault(new ConfigValue(groupPath + ".order", ""));
            addDefault(new ConfigValue(groupPath + ".show_in_tab", "false"));
            addDefault(new ConfigValue(groupPath + ".prefix", ""));
            addDefault(new ConfigValue(groupPath + ".suffix", ""));
        }
    }

    /**
     * Loads all Groups from the Config File.
     * @return All Groups from the Config File as a List of {@link ViewableGroup}
     * */
    public List<ViewableGroup> getGroups() {
        List<ViewableGroup> result = new ArrayList<>();
        Set<Group> permGroups = luckPerms.getGroupManager().getLoadedGroups();
        for(Group permGroup : permGroups) {
            String groupPath = groupRootPath + permGroup.getName();
            String name = getValue(groupPath + ".name");
            int order = Utils.getInteger(getValue(groupPath + ".order")).orElse(0);
            boolean showInTab = Utils.getBoolean(getValue(groupPath + ".show_in_tab")).orElse(false);
            String prefix = getValue(groupPath + ".prefix");
            String suffix = getValue(groupPath + ".suffix");
            result.add(new ViewableGroup(name, order, showInTab, prefix, suffix));
        }
        return result;
    }

}
