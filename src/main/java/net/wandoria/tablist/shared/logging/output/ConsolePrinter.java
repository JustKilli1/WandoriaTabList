package net.wandoria.tablist.shared.logging.output;

import net.wandoria.tablist.shared.logging.LogLevel;
import net.wandoria.tablist.shared.logging.LoggingUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ConsolePrinter implements IOutputPrinter{
    @Override
    public void print(LogLevel level, List<String> message) {
        ConsoleCommandSender cmd = Bukkit.getConsoleSender();
        switch(level) {
            case INFO:
                message.forEach(cmd::sendMessage);
                break;
            case WARN:
                message.forEach(msg -> cmd.sendMessage(ChatColor.GOLD + msg));
                break;
            case ERROR:
                message.forEach(msg -> cmd.sendMessage(ChatColor.RED + msg));
                break;
            case DEBUG:
                message.forEach(msg -> cmd.sendMessage(ChatColor.YELLOW + msg));
                break;
        }
    }

    @Override
    public void print(LogLevel level, String message) {
        print(level, List.of(message));
    }

    @Override
    public String format(LogLevel logLevel, String loggerName, List<String> message, Exception ex) {
        String messageMSG = message == null && message.size() == 0 ? "" : LoggingUtils.getMessageStr(message, false);
        String exceptionStr = ex == null ? "" : LoggingUtils.getStackTraceAsStr(ex);
        return "[" + loggerName + "][" + logLevel.getName() + "]" +
                messageMSG + "\n" + exceptionStr;
    }

    @Override
    public String format(LogLevel logLevel, String loggerName, String message, Exception ex) {
        return format(logLevel, loggerName, List.of(message), ex);
    }
}
