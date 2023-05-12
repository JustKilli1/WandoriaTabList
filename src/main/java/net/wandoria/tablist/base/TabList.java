package net.wandoria.tablist.base;

import net.luckperms.api.LuckPerms;
import net.wandoria.tablist.shared.config.GeneralMessagesConfig;
import net.wandoria.tablist.shared.config.PermGroupsConfig;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

public final class TabList extends JavaPlugin {

    @Override
    public void onEnable() {
        GeneralMessagesConfig config = new GeneralMessagesConfig(this);
        config.save();
        LuckPerms luckPerms = getServer().getServicesManager().load(LuckPerms.class);
        new PermGroupsConfig(this, luckPerms);
    }

    @Override
    public void onDisable() {

    }
}
