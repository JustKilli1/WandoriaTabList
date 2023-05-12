package net.wandoria.tablist.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CmdTest extends WandoriaCommand{
    public CmdTest() {
        super("test", "/test", "A Test Command to test things");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        sender.sendMessage("Hello World!");
        return false;
    }
}
