package dev.arsngrobg.luam.parser;

public record LuaSourcePosition(int line, int column) {
    public LuaSourcePosition {
        if (line < 0) {
            throw new IllegalArgumentException("line componenent must be unsigned");
        }
        if (column < 0) {
            throw new IllegalArgumentException("column component must be unsigned");
        }
    }

    @Override
    public final String toString() {
        return String.format("(%d, %d)", line(), column());
    }
}
