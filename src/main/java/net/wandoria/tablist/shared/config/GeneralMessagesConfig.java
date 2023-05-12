package net.wandoria.tablist.shared.config;

import net.wandoria.tablist.shared.logging.LogLevel;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public class GeneralMessagesConfig extends ConfigManager{

    public static final ConfigValue COMMAND_NOT_FOUND = new ConfigValue("general.command_not_found", "Invalid Command Syntax use " + ConfigParams.COMMAND, ConfigParams.COMMAND);
    public static final ConfigValue ONLY_PLAYER_CAN_EXECUTE = new ConfigValue("general.only_player_can_execute", "Only Player can execute this command");

    public GeneralMessagesConfig(JavaPlugin plugin) {
        super(plugin, "general_messages.yml");
        createDefaultConfig();
    }

    private void createDefaultConfig() {
        for(Field declaredField : this.getClass().getDeclaredFields()) {
            try {
                Object value = declaredField.get(null);
                if(!(value instanceof ConfigValue)) continue;
                ConfigValue configValue = (ConfigValue) value;
                addDefault(configValue);
            } catch(Exception ex) {
                logger.log(LogLevel.ERROR, "Could not create default Config", ex);
            }
        }
    }
}
