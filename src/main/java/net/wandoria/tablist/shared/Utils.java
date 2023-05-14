package net.wandoria.tablist.shared;

import java.util.Optional;

public class Utils {

    public static Optional<Integer> getInteger(String str) {
        try {
            return Optional.ofNullable(Integer.parseInt(str));
        } catch(Exception ex) {
            return Optional.empty();
        }
    }

    public static Optional<Boolean> getBoolean(String str) {
        try {
            return Optional.ofNullable(Boolean.parseBoolean(str));
        } catch(Exception ex) {
            return Optional.empty();
        }
    }

}
