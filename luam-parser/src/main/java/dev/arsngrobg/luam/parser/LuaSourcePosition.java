package dev.arsngrobg.luam.parser;

import java.util.Objects;

import dev.arsngrobg.luam.utils.Constraint;

public final class LuaSourcePosition {
    private final int line;
    private final int column;

    public LuaSourcePosition(int line, int column) {
        this.line   = Constraint.unsignedInt(line);
        this.column = Constraint.unsignedInt(column);
    }

    public LuaSourcePosition displacedBy(int dline, int dcolumn) {
        return new LuaSourcePosition(getLine()+dline, getColumn()+dcolumn);
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLine(), getColumn());
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof LuaSourcePosition other)
          &&   (getLine() == other.getLine() && getColumn() == other.getColumn());
    }

    @Override
    public String toString() {
        return "(%d, %d)".formatted(getLine(), getColumn());
    }
}
