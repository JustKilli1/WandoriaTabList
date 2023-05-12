package net.wandoria.tablist.shared.config;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class ConfigParam {

    public static final char PREFIX = '#';
    private String name, value;
    private Component valueDisplay;

    public ConfigParam(String name, String value) {
        this.name = PREFIX + name + PREFIX;
        this.value = value;
    }

    private ConfigParam(ConfigParam param) {
        name = param.getName();
        value = param.getValue();
    }
    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public ConfigParam setValue(String value) {
        this.value = value;
        valueDisplay = Component.text(this.value);
        return this;
    }

    public ConfigParam setValue(String value, TextColor highlighter) {
        setValue(value);
        setValueDisplay(highlighter);
        return this;
    }

    public ConfigParam setValue(int value) {
        setValue(String.valueOf(value));
        return this;
    }

    public ConfigParam setValue(int value, TextColor highlighter) {
        setValue(String.valueOf(value));
        setValueDisplay(highlighter);
        return this;
    }

    public void setValueDisplay(TextColor highlighter) {
        valueDisplay = Component.text().color(highlighter).content(value).build();
    }
    @Override
    public ConfigParam clone() {
        return new ConfigParam(this);
    }

    @Override
    public String toString() {
        return name;
    }

    public Component getValueDisplay() {
        return valueDisplay;
    }
}
