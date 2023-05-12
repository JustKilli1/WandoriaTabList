package net.wandoria.tablist.commands;

import org.bukkit.command.CommandExecutor;

public abstract class WandoriaCommand implements CommandExecutor {

    private final String name, syntax, description;

    protected WandoriaCommand(String name, String syntax, String description) {
        this.name = name;
        this.syntax = syntax;
        this.description = description;
    }
}
