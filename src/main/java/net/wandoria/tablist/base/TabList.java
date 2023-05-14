package net.wandoria.tablist.base;

import net.luckperms.api.LuckPerms;
import net.wandoria.tablist.commands.CmdTest;
import net.wandoria.tablist.commands.WandoriaCommand;
import net.wandoria.tablist.listener.PlayerJoinListener;
import net.wandoria.tablist.shared.config.GeneralMessagesConfig;
import net.wandoria.tablist.shared.config.TabListConfig;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public final class TabList extends JavaPlugin {

    private static Set<WandoriaCommand> commands = new HashSet<>();
    private TabListConfig tabListConfig;

    @Override
    public void onEnable() {

        createConfigs();
        registerCommands();
        registerListener();
    }

    private void createConfigs() {
        GeneralMessagesConfig config = new GeneralMessagesConfig(this);
        LuckPerms luckPerms = getServer().getServicesManager().load(LuckPerms.class);
        tabListConfig = new TabListConfig(this, luckPerms);
    }

    private void registerCommands() {
        WandoriaCommand testCommand = new CmdTest();
        getCommand(testCommand.getName()).setExecutor(testCommand);
        commands.add(testCommand);
    }

    private void registerListener() {
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(tabListConfig), this);
    }

    @Override
    public void onDisable() {

    }

    public static Set<WandoriaCommand> getCommands() {
        return commands;
    }

}
