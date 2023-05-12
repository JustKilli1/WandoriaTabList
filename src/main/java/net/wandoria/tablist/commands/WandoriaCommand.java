package net.wandoria.tablist.commands;

import org.bukkit.command.CommandExecutor;

public abstract class WandoriaCommand implements CommandExecutor {

    protected final String name, syntax, description;

    protected WandoriaCommand(String name, String syntax, String description) {
        this.name = name;
        this.syntax = syntax;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getSyntax() {
        return syntax;
    }

    public String getDescription() {
        return description;
    }
}
