package net.wandoria.tablist.tablist;

import net.wandoria.tablist.shared.config.ConfigValue;

public record ViewableGroup(String name, int order, boolean showInTab, String prefix, String suffix) {

}
