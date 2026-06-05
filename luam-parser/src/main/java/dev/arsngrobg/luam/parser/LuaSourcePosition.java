package dev.arsngrobg.luam.parser;

public final class LuaSourcePosition {
    public static final LuaSourcePosition START = new LuaSourcePosition(0, 0);

    public static LuaSourcePosition at(int line, int column) {
        if (line   < 0) throw new NumberFormatException("line must be non-negative");
        if (column < 0) throw new NumberFormatException("column must be non-negative");

        if (line == 0 && column == 0)
            return LuaSourcePosition.START;
        return new LuaSourcePosition(line, column);
    }

    private final int line;
    private final int column;

    private LuaSourcePosition(int line, int column) {
        this.line   = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public int hashCode() {
        return getLine() ^ getColumn();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LuaSourcePosition other)) {
            return false;
        }
        return getLine() == other.getLine() && getColumn() == other.getColumn();
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", getLine(), getColumn());
    }
}
