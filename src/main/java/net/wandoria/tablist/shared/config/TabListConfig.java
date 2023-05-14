package net.wandoria.tablist.shared.config;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.group.Group;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TabListConfig extends ConfigManager{
    private LuckPerms luckPerms;

    public TabListConfig(Plugin plugin, LuckPerms luckPerms) {
        super(plugin, "groups.yml");
        this.luckPerms = luckPerms;
        createDefaultConfig();
    }

    private void createDefaultConfig() {
        List<String> header = List.of("line1", "line2");
        List<String> footer = List.of("line1", "line2");
        addDefault("header", header);
        addDefault("footer", footer);

        Set<Group> permGroups = luckPerms.getGroupManager().getLoadedGroups();
        for(Group permGroup : permGroups) {
            String groupPath = "groups." + permGroup.getName();
            addDefault(new ConfigValue(groupPath + ".show_in_tab", "false"));
            addDefault(new ConfigValue(groupPath + ".prefix", ""));
            addDefault(new ConfigValue(groupPath + ".suffix", ""));
        }
    }

}
