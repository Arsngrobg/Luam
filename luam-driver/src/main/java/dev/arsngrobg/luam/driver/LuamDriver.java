package dev.arsngrobg.luam.driver;

import dev.arsngrobg.luam.parser.LuaTokenKind;

public final class LuamDriver {
    public static void main(String[] args) {
        for (LuaTokenKind tknKind : LuaTokenKind.values()) {
            System.err.printf("%s\n", tknKind.toString().toLowerCase());
        }
    }
}
