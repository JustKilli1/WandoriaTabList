package net.wandoria.tablist.shared.logging;

public enum LogLevel {

    INFO("Info"),
    WARN("Warn"),
    ERROR("Du hast verkackt"),
    DEBUG("Debug")
    ;

    private String name;

    LogLevel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
