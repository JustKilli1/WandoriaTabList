package net.wandoria.tablist.shared.config;

import net.wandoria.tablist.shared.logging.ILogger;
import net.wandoria.tablist.shared.logging.LogLevel;
import net.wandoria.tablist.shared.logging.loggers.BaseLogger;
import net.wandoria.tablist.shared.logging.output.ConsolePrinter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.List;

public class ConfigManager {

    private File file;
    private FileConfiguration config;
    private ILogger logger;

    public ConfigManager(Plugin plugin, String path) {
        this(plugin.getDataFolder().getAbsolutePath() + "/" + path);
    }

    public ConfigManager(String path) {
        file = new File(path);
        logger = new BaseLogger("Config", new ConsolePrinter());
        config = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Saves the Config file
     * @return true if success
     * */
    public boolean save() {
        try {
            config.save(file);
            return true;
        } catch (Exception ex) {
            logger.log(LogLevel.ERROR,"Could not save ConfigFile with Path: " + file.getAbsolutePath(), ex);
            return false;
        }
    }

    public void addDefault(List<ConfigValue> defaultValues) {
        defaultValues.forEach(value -> config.addDefault(value.getPath(), value.getRawValue()));
        save();
    }

    public void addDefault(ConfigValue defaultValue) {
        addDefault(List.of(defaultValue));
    }

    public void reload() {
        config = YamlConfiguration.loadConfiguration(file);
    }
    public File getFile() { return file; }
    public FileConfiguration getConfig() {
        return config;
    }

}
