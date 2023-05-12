package net.wandoria.tablist.shared.config;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentBuilder;
import net.kyori.adventure.text.format.TextColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigValue {

    private String path, rawValue, value;
    private List<ConfigParam> params;
    private Component valueDisplay;

    public ConfigValue(String path, String rawValue, List<ConfigParam> params) {
        this.path = path;
        this.params = params;
        setRawValue(rawValue);
    }

    public ConfigValue(String path, String rawValue, ConfigParam... params) {
        this(path, rawValue, Arrays.stream(params).toList());
    }

    public ConfigValue(String path, String rawValue, ConfigParam param) { this(path, rawValue, List.of(param)); }
    public ConfigValue(String path, String rawValue) {
        this(path, rawValue, new ArrayList<>());
    }

    public ConfigValue setConfigParamValue(String name, String value, TextColor normal, TextColor highlight) {
        for(ConfigParam param : params) {
            if(param.getName().equals(name)) {
                param.setValue(value);
                setValue(normal, highlight);
                break;
            }
        }
        return this;
    }

    //TextColor.Chat + Hello my name is TextColor.highlighter + #param1# TextColor.Chat + How are u TextColor.highlighter + #param2#
    public ConfigValue setConfigParamValue(ConfigParam param) {
        return setConfigParamValue(param.getName(), param.getValue(), null, null);
    }

    public ConfigValue setConfigParamValue(ConfigParam param, TextColor normal, TextColor highlight) {
        return setConfigParamValue(param.getName(), param.getValue(), normal, highlight);
    }

    private void setValue(TextColor normal, TextColor highlight) {
        value = rawValue;
        params.forEach(param -> value = value.replace(param.getName(), param.getValue()));

        Pattern pattern = Pattern.compile("#([\\w-]+)#");

        Matcher matcher = pattern.matcher(rawValue);
        ComponentBuilder builder = Component.text();
        int lastIndex = 0;
        while (matcher.find()) {
            builder.append(Component.text(rawValue.substring(lastIndex, matcher.start()), normal));
            String parameter = matcher.group(1);
            String value = getParamValue(parameter).orElse("");

            builder.append(Component.text(value, highlight));
            lastIndex = matcher.end();
        }
        builder.append(Component.text(rawValue.substring(lastIndex), normal));

       valueDisplay = builder.build();

    }

    public Optional<String> getParamValue(String name) {
        for(ConfigParam param : params) {
            String paramName = name.contains(String.valueOf(ConfigParam.PREFIX)) ? name : ConfigParam.PREFIX + name + ConfigParam.PREFIX;
            if(param.getName().equals(paramName)) return Optional.ofNullable(param.getValue());
        }
        return Optional.empty();
    }

    public void setRawValue(String rawValue) {
        this.rawValue = rawValue;
        setValue(null, null);
    }

    public String getPath() {
        return path;
    }

    public String getRawValue() {
        return rawValue;
    }

    public String getValue() {
        return value;
    }

    public List<ConfigParam> getParams() {
        return params;
    }
    public Optional<ConfigParam> getParams(String paramName) {
        for(ConfigParam param : params) {
            if(param.getName().equals(paramName)) return Optional.of(param);
        }
        return Optional.empty();
    }

    public void addParam(ConfigParam param) {
        params.add(param);
        setValue(null, null);
    }

    @Override
    public ConfigValue clone() {
        return new ConfigValue(path, rawValue, params);
    }

    public Component getValueDisplay() {
        return valueDisplay;
    }
}
