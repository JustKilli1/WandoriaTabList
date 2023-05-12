package net.wandoria.tablist.base;

import net.wandoria.tablist.shared.config.GeneralMessagesConfig;
import org.bukkit.plugin.java.JavaPlugin;

public final class TabList extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        GeneralMessagesConfig config = new GeneralMessagesConfig(this);
        config.save();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
