package io.github.arsngrobg.luam.parser;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import io.github.arsngrobg.luam.common.Constraint;

/**
 * An 8-bit clean buffer of bytes containing Lua source code.
 *
 * Characters can be indexed via flat indices, or 2D line and column coordinates.
 */
public final class LuaSource implements CharSequence, Iterable<Character> {
    /**
     * Creates a {@code LuaSource} consisting of the data within the file.
     * @param filename the file to read
     *
     * @return a {@code LuaSource} mapped in from the file
     * @throws IOException if the file described by {@code filename} cannot be opened
     */
    public static LuaSource ofFile(String filename) throws IOException {
        try (FileChannel channel = FileChannel.open(Path.of(filename), StandardOpenOption.READ)) {
            long fileSize = channel.size();
            if (fileSize > Integer.MAX_VALUE) {
                throw new IOException("File size cannot exceed 2GB");
            }

            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            return new LuaSource(buffer);
        }
    }

    /**
     * Creates a {@code LuaSource} from the raw string.
     * @param source the raw Lua source code
     * @return a {@code LuaSource} containing the bytes of the string
     */
    public static LuaSource of(String code) {
        return new LuaSource(ByteBuffer.wrap(Constraint.notNull(code).getBytes()));
    }

    private final LuaSourcePosition EOF;

    private final ByteBuffer bytes;
    private final int[]      index;

    public LuaSource(ByteBuffer bytes) {
        this.bytes = Constraint.notNull(bytes).asReadOnlyBuffer();
        this.index = new ArrayList<Integer>() {{
            add(0);
            for (int idx = 0; idx < length(); idx++) {
                if (charAt(idx) == '\n') add(idx);
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
        return (start != 0 && end != length())
           ?   new LuaSource(bytes.slice(start, end - start))
           :   this;
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
        return (0 <= index && index < length())
           ?   ((char) bytes.get(index))
           :   '\0';
    }

    /**
     * The actual string content of this {@code LuaSource}.
     */
    public String getContent() {
        return new String(StandardCharsets.ISO_8859_1.decode(bytes).array());
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
        return bytes.limit();
    }

    @Override
    public java.util.Iterator<Character> iterator() {
        return new LuaSource.Iterator(this);
    }

    @Override
    public int hashCode() {
        return bytes.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof LuaSource src)
          &&   (bytes.equals(src.bytes));
    }

    @Override
    public String toString() {
        return "LuaSource[size=%dB lines=%d]".formatted(length(), getLineCount());
    }

    /**
     * The standard {@link java.util.Iterator} for a {@link LuaSource}.
     */
    public static final class Iterator implements java.util.Iterator<Character> {
        private final LuaSource target;

        private int position = 0;

        private Iterator(LuaSource target) {
            this.target = Constraint.notNull(target);
        }

        @Override
        public boolean hasNext() {
            return getPosition() != getLimit();
        }

        @Override
        public Character next() {
            if (!hasNext()) throw new NoSuchElementException("Reached EOF");

            return getTarget().charAt(position++);
        }

        /**
         * The limit of this iterator <b>(unsigned)</b>.
         */
        public int getLimit() {
            return getTarget().length();
        }

        /**
         * The current position of this iterator <b>(unsigned)</b>.
         */
        public int getPosition() {
            return position;
        }

        /**
         * This iterator's target {@link LuaSource} object.
         */
        public LuaSource getTarget() {
            return target;
        }

        @Override
        public int hashCode() {
            return getTarget().hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return (obj instanceof LuaSource.Iterator iter)
              &&   (getTarget().equals(iter.getTarget()))
              &&   (getPosition() == iter.getPosition());
        }

        @Override
        public String toString() {
            return "LuaSource.Iterator[position=%d]".formatted(position);
        }
    }
}
