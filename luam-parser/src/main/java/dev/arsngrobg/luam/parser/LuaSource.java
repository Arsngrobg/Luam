package dev.arsngrobg.luam.parser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class LuaSource {
    public static final LuaSource EMPTY = new LuaSource(new byte[0]);

    public static Optional<LuaSource> ofFile(String filename) {
        Objects.requireNonNull(filename, "LuaSource::ofFile filename cannot be NULL");
        try {
            byte[] fileBytes = Files.readAllBytes(new File(filename).toPath());
            return Optional.of(
                (fileBytes.length == 0)
                ? LuaSource.EMPTY
                : new LuaSource(fileBytes)
            );
        } catch (IOException e) { return Optional.empty(); }
    }

    public static LuaSource ofString(String source) {
        Objects.requireNonNull(source, "LuaSource::ofString source cannot be NULL");
        byte[] asBytes = source.getBytes(StandardCharsets.UTF_8);
        return (asBytes.length == 0) ? LuaSource.EMPTY : new LuaSource(asBytes);
    }

    private final byte[] bytes;
    private final int[]  lineLookup;

    private final LuaSourcePosition start = new LuaSourcePosition(0, 0);
    private final LuaSourcePosition eof;

    private LuaSource(byte[] bytes) {
        this.bytes = Objects.requireNonNull(bytes, "LuaSource bytes cannot be NULL");

        List<Integer> buffer = new ArrayList<>(List.of(0));

        int line = 0, column = 0;
        for (int idx = 0; idx < byteCount(); idx++) {
            char ch = (char) bytes[idx];
            line   += (ch == '\n') ? 1       : 0;
            column += (ch == '\n') ? -column : 1;

            if (ch != '\n') continue;
            buffer.add((idx == byteCount()-1) ? idx : idx+1);
        }

        lineLookup = buffer.stream()
                           .mapToInt(Integer::intValue)
                           .toArray();
        eof = new LuaSourcePosition(line, column);
    }

    public char charAt(LuaSourcePosition pos) {
        Objects.requireNonNull(pos, "LuaSource::charAt pos cannot be NULL");
        int idx = lineLookup[pos.line()] + pos.column();
        return (char) bytes[idx];
    }

    public LuaSourcePosition start() {
        return start;
    }

    public LuaSourcePosition eof() {
        return eof;
    }

    public int byteCount() {
        return bytes.length;
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

        return bytes.equals(src.bytes);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder("Lua Source (").append(byteCount()).append("B)");
        if (byteCount() == 0) {
            return buffer.toString();
        }

        buffer.append("\n| ");
        for (int idx = 0; idx < byteCount(); idx++) {
            char ch = (char) bytes[idx];
            if (ch == '\n') buffer.append("\n| ");
            else            buffer.append(ch);
        }

        return buffer.toString();
    }
}
