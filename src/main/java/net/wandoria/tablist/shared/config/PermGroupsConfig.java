package net.wandoria.tablist.shared.config;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.group.Group;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;
import java.util.Set;

public class PermGroupsConfig extends ConfigManager{
    private LuckPerms luckPerms;

    public PermGroupsConfig(Plugin plugin, LuckPerms luckPerms) {
        super(plugin, "groups.yml");
        this.luckPerms = luckPerms;
        createDefaultConfig();
    }

    private void createDefaultConfig() {
        Set<Group> permGroups = luckPerms.getGroupManager().getLoadedGroups();
        for(Group permGroup : permGroups) {
            String groupPath = "groups." + permGroup.getName();
            addDefault(new ConfigValue(groupPath + ".show_in_tab", "false"));
            addDefault(new ConfigValue(groupPath + ".prefix", ""));
            addDefault(new ConfigValue(groupPath + ".suffix", ""));
        }
    }

}
