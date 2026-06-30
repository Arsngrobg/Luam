package dev.arsngrobg.luam.parser;

import java.util.Objects;

public record LuaToken(LuaTokenKind kind, LuaSourcePosition start, LuaSourcePosition end) {
    public LuaToken {
        Objects.requireNonNull(kind,  "LuaToken kind cannot be NULL");
        Objects.requireNonNull(start, "LuaToken start cannot be NULL");
        Objects.requireNonNull(end,   "LuaToken start cannot be NULL");
    }
}
