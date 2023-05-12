package net.wandoria.tablist.base;

import net.luckperms.api.LuckPerms;
import net.wandoria.tablist.commands.CmdTest;
import net.wandoria.tablist.commands.WandoriaCommand;
import net.wandoria.tablist.shared.config.GeneralMessagesConfig;
import net.wandoria.tablist.shared.config.PermGroupsConfig;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public final class TabList extends JavaPlugin {

    private static Set<WandoriaCommand> commands = new HashSet<>();

    @Override
    public void onEnable() {

        createConfigs();
        registerCommands();

    }

    private void createConfigs() {
        GeneralMessagesConfig config = new GeneralMessagesConfig(this);
        LuckPerms luckPerms = getServer().getServicesManager().load(LuckPerms.class);
        new PermGroupsConfig(this, luckPerms);
    }

    private void registerCommands() {
        WandoriaCommand testCommand = new CmdTest();
        getCommand(testCommand.getName()).setExecutor(testCommand);
        commands.add(testCommand);
    }

    @Override
    public void onDisable() {

    }

    public static Set<WandoriaCommand> getCommands() {
        return commands;
    }

}
