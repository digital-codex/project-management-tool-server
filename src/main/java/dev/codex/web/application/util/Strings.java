package dev.codex.web.application.util;

public class Strings {
    private Strings() {
        super();
    }

    public static boolean hasText(String str) {
        if (str == null || str.isEmpty())
            return false;

        for (int i = 0; i < str.length(); ++i) {
            if (!Character.isWhitespace(str.charAt(i)))
                return true;
        }

        return false;
    }
}