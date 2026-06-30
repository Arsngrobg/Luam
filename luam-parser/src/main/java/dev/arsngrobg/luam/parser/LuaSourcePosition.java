package dev.arsngrobg.luam.parser;

import java.util.Objects;

public record LuaSourcePosition(int line, int column) {
    public LuaSourcePosition {
        Objects.requireNonNull(line,   "LuaSourcePosition line cannot be NULL");
        Objects.requireNonNull(column, "LuaSourcePosition column cannot be NULL");
    }

    @Override
    public final String toString() {
        return String.format("(%d, %d)", line(), column());
    }
}
