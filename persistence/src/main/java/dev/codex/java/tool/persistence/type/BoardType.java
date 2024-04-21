package dev.codex.java.tool.persistence.type;

import java.util.HashMap;
import java.util.Map;

public enum BoardType {
    KANBAN, SCRUM;

    private static final Map<Character, BoardType> BOARD_TYPE_LOOKUP = new HashMap<>();
    static {
        BOARD_TYPE_LOOKUP.put('K', KANBAN);
        BOARD_TYPE_LOOKUP.put('S', SCRUM);
    }

    public static BoardType lookupBoardType(Character key) {
        if (key == null) {
            throw new NullPointerException("argument must not be null");
        }

        BoardType type = BoardType.BOARD_TYPE_LOOKUP.get(Character.toUpperCase(key));
        if (type != null) {
            return type;
        }

        throw new IllegalArgumentException("No constant found for `key` " + key);
    }
}