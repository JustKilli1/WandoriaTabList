package net.wandoria.tablist.base;

import net.wandoria.tablist.shared.logging.ILogger;
import net.wandoria.tablist.shared.logging.LogLevel;
import net.wandoria.tablist.shared.logging.loggers.BaseLogger;
import net.wandoria.tablist.shared.logging.output.ConsolePrinter;
import org.bukkit.plugin.java.JavaPlugin;

public final class TabList extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        ILogger testConsoleLogger = new BaseLogger("TestLogger", new ConsolePrinter());
        testConsoleLogger.log(LogLevel.INFO, "Test Info Message");
        testConsoleLogger.log(LogLevel.WARN, "Test Warn Message");
        testConsoleLogger.log(LogLevel.ERROR, "Test Error Message");
        testConsoleLogger.log(LogLevel.DEBUG, "Test Debug Message");
        testConsoleLogger.log(LogLevel.ERROR, "Test Exception Message", new Exception());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
