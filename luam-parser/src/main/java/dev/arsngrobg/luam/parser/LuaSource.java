package dev.arsngrobg.luam.parser;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * A UTF-8 encoded character buffer that assumes all bytes are valid Lua characters.
 *
 * Characters can be indexed via flat indices, or 2D line and column coordinates.
 */
public final class LuaSource implements CharSequence, Iterable<Character> {
    private final byte[] bytes;
    private final int[]  index;

    private final LuaSourcePosition EOF;

    public LuaSource(byte[] bytes) {
        this.bytes = bytes;
        this.index = new ArrayList<Integer>() {{
            add(0);
            for (int idx = 0; idx < bytes.length; idx++) {
                char ch = (char) bytes[idx];
                if (ch == '\n') add(idx + 1);
            }
        }}.stream().mapToInt(i -> i).toArray();
        this.EOF = new LuaSourcePosition(
            getLineCount() - 1,
            length() - index[getLineCount() - 1]
        );
    }

    /**
     * Flattens the 2D coordinate system into equivalent flat index.
     * @param line   the line component
     * @param column the column component
     * @return a flat index ({@code -1} if invalid position)
     */
    public int toIndex(int line, int column) {
        return (line < getLineCount())
           ?   (index[line] + column)
           :   -1;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return (start == 0 && end == length())
          ?    this
          :    new LuaSource(Arrays.copyOfRange(bytes, start, end));
    }

    /**
     * Returns the character at the {@code line} and {@code column} pair.
     * @param line   the line position in the source
     * @param column the column position in the source
     * @return the character (if within bounds - {@code '\0'} by default)
     */
    public char charAt(int line, int column) {
        return charAt(toIndex(line, column));
    }

    @Override
    public char charAt(int index) {
        return (0 > index || index >= length())
           ?   '\0'
           :   ((char) bytes[index]);
    }

    /**
     * Converts this source into a UTF-8 string.
     *
     * @return
     */
    public String getContent() {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * The position of the EOF character.
     */
    public LuaSourcePosition getEOF() {
        return EOF;
    }

    /**
     * The number of lines property <b>(unsigned)</b>.
     */
    public int getLineCount() {
        return index.length;
    }

    @Override
    public int length() {
        return bytes.length;
    }

    @Override
    public Iterator<Character> iterator() {
        return getContent().chars().mapToObj(c -> (char) c).iterator();
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(bytes);
    }
}
