package net.wandoria.tablist.base;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.group.Group;
import net.wandoria.tablist.shared.config.GeneralMessagesConfig;
import net.wandoria.tablist.shared.config.PermGroupsConfig;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

public final class TabList extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        GeneralMessagesConfig config = new GeneralMessagesConfig(this);
        config.save();
        // Load an instance of 'LuckPerms' using the services manager.
        LuckPerms luckPerms = getServer().getServicesManager().load(LuckPerms.class);
        new PermGroupsConfig(this, luckPerms);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
