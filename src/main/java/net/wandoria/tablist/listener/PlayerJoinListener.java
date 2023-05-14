package net.wandoria.tablist.listener;

import net.wandoria.tablist.shared.config.ConfigManager;
import net.wandoria.tablist.shared.config.TabListConfig;
import net.wandoria.tablist.tablist.WandoriaTabList;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class PlayerJoinListener implements Listener {

    private TabListConfig config;

    public PlayerJoinListener(TabListConfig config) {
        this.config = config;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        WandoriaTabList tabList = new WandoriaTabList(config);
        tabList.buildHeader(player);
        tabList.buildFooter(player);
        tabList.addPlayerToTeam(player);
    }

}
