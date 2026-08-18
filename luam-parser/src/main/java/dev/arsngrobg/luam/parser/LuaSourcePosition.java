package dev.arsngrobg.luam.parser;

import java.util.Objects;

/**
 * A 2-dimensional positional type that maps to a position in a Lua source file.
 */
public final class LuaSourcePosition {
    private final int line;
    private final int column;

    public LuaSourcePosition(int line, int column) {
        if (line   < 0) throw new IllegalArgumentException("line of LuaSourcePosition must be unsigned");
        if (column < 0) throw new IllegalArgumentException("column of LuaSourcePosition must be unsigned");
        this.line   = line;
        this.column = column;
    }

    /**
     * The {@code line} property <b>(unsigned)</b>
     */
    public int line() {
        return line;
    }

    /**
     * The {@code column} property <b>(unsigned)</b>
     */
    public int column() {
        return column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(line(), column());
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof LuaSourcePosition pos)
          &&   (line() == pos.line() && column() == pos.column());
    }

    @Override
    public String toString() {
        return "(%d,%d)".formatted(line(), column());
    }
}
