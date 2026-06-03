package dev.arsngrobg.luam.driver;

import dev.arsngrobg.luam.parser.LuaToken;

public final class LuamDriver {
    public static void main(String[] args) {
        for (LuaToken tknKind : LuaToken.values()) {
            System.err.printf("%s\n", tknKind.toString().toLowerCase());
        }
    }
}
