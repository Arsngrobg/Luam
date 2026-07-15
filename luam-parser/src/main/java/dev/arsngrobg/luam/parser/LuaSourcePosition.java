package dev.arsngrobg.luam.parser;

import java.util.Objects;

import dev.arsngrobg.luam.utils.Constraint;

/**
 * A two-dimensional coordinate system based on a line and column in a {@link LuaSource}.
 */
public final class LuaSourcePosition {
    private final int line;
    private final int column;

    /**
     * Creates a new {@code LuaSourcePosition} object with the supplied parameters.
     *
     * @param line   the line coordinate component
     * @param column the column coordinate component
     *
     * @throws IllegalArgumentException if either {@code line} or {@code column} are negative
     */
    public LuaSourcePosition(int line, int column) throws IllegalArgumentException {
        this.line   = Constraint.unsignedInt(line);
        this.column = Constraint.unsignedInt(column);
    }

    /**
     * Displaces this position by the amount supplied to this method.
     *
     * <p>
     * This creates a new {@code LuaSourcePosition} only if {@code dline} and {@code dcolumn} are not zero.
     *
     * @param  dline   the amount of lines to shift this position by
     * @param  dcolumn the amount of columns to shift this position by
     * @return         the new position that reflects the displacement
     *
     * @throws IllegalArgumentException if the resulting position by this displacement results in corrdinates
     *                                  that are signed
     */
    public LuaSourcePosition displacedBy(int dline, int dcolumn) {
        if (dline == 0 && dcolumn == 0) return this;
        return new LuaSourcePosition(getLine()+dline, getColumn()+dcolumn);
    }

    /**
     * The line coordinate component.
     */
    public int getLine() {
        return line;
    }

    /**
     * The column coordinate component.
     */
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
