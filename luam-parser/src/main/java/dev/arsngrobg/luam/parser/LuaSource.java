package dev.arsngrobg.luam.parser;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

public final class LuaSource implements CharSequence, Iterable<Character>, Serializable {
    public static Optional<LuaSource> ofFile(String filepath) {
        Objects.requireNonNull(filepath, "filepath cannot be NULL");

        try {
            File file = new File(filepath);
            byte[] bytes = Files.readAllBytes(file.toPath());
            return Optional.of(new LuaSource(bytes));
        } catch (IOException e) {
            System.err.println(e);
            return Optional.empty();
        }
    }

    public static LuaSource ofString(String source) {
        Objects.requireNonNull(source, "source cannot be NULL");
        return new LuaSource(source.getBytes(StandardCharsets.UTF_8));
    }

    private final byte[] bytes;
    private final int[]  lineOffsets;

    public LuaSource(byte[] bytes) {
        this.bytes = Objects.requireNonNull(bytes, "bytes cannot be NULL");

        List<Integer> buffer = new ArrayList<>(List.of(0));
        for (int idx = 0; idx < length(); idx++) {
            char ch = (char) bytes[idx];
            if (ch == '\n') {
                buffer.add(idx+1);
            }
        }
        lineOffsets = buffer.stream().mapToInt(Integer::valueOf).toArray();
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        if (start == 0 && end == length()-1) {
            return this;
        }

        byte[] subsequence = Arrays.copyOfRange(bytes, start, end);
        return new LuaSource(subsequence);
    }

    public char charAt(LuaSourcePosition position) {
        Objects.requireNonNull(position, "position cannot be NULL");
        if (position.column() > columnCountForLine(position.line())) {
            throw new IndexOutOfBoundsException("column position is out of bounds");
        }

        return charAt(lineOffsets[position.line()] + position.column());
    }

    @Override
    public char charAt(int index) {
        return (char) bytes[index];
    }

    public String getContent() {
        StringBuilder buffer = new StringBuilder();
        for (char ch : this) {
            buffer.append(ch);
        }
        return buffer.toString();
    }

    public LuaSourcePosition eof() {
        return new LuaSourcePosition(lineCount(), columnCountForLine(lineCount()-1));
    }

    public int columnCountForLine(int line) {
        if (line <  0)           throw new IllegalArgumentException("line must be unsigned");
        if (line >= lineCount()) throw new IndexOutOfBoundsException("line is out of bounds");

        int offset = lineOffsets[line];
        int next   = (line == (lineCount()-1)) ? bytes.length : lineOffsets[line+1];
        return next - offset;
    }

    public int lineCount() {
        return lineOffsets.length;
    }

    @Override
    public int length() {
        return bytes.length;
    }

    public byte[] toByteArray() {
        return Arrays.copyOf(bytes, bytes.length);
    }

    @Override
    public Iterator<Character> iterator() {
        return new Iterator<Character>() {
            private int idx = 0;

            @Override
            public Character next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("LuaSourceIterator reached end of byte buffer");
                }

                return (char) bytes[idx++];
            }

            @Override
            public boolean hasNext() {
                return idx != length();
            }
        };
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(bytes);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LuaSource src)) {
            return false;
        }

        return Arrays.equals(bytes, src.bytes);
    }

    @Override
    public String toString() {
        return String.format("LuaSource[SIZE=%dB,LINES=%d]", length(), lineCount());
    }
}
