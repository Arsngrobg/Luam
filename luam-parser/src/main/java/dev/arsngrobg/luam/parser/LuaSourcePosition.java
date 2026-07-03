package dev.arsngrobg.luam.parser;

public record LuaSourcePosition(int line, int column) {
    public LuaSourcePosition {
        if (line   < 0) throw new IllegalArgumentException("LuaSourcePosition line cannot be NULL");
        if (column < 0) throw new IllegalArgumentException("LuaSourcePosition column cannot be NULL");
    }

    @Override
    public final String toString() {
        return String.format("(%d, %d)", line(), column());
    }
}
