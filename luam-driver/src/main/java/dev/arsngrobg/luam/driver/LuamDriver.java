package dev.arsngrobg.luam.driver;

import dev.arsngrobg.luam.parser.*;

public final class LuamDriver {
    public static void main(String[] args) {
        for (LuaTokenKind tknKind : LuaTokenKind.values()) {
            System.err.printf("%s\n", tknKind.toString().toLowerCase());
        }

        LuaSourcePosition pos = LuaSourcePosition.START;
        System.out.println(pos);
    }
}
