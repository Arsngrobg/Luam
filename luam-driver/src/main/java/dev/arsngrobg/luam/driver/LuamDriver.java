package dev.arsngrobg.luam.driver;

import dev.arsngrobg.luam.parser.*;

public final class LuamDriver {
    public static void main(String[] args) {
        for (LuaTokenKind tknKind : LuaTokenKind.values()) {
            System.err.printf("%s\n", tknKind.toString().toLowerCase());
        }

        LuaSourcePosition pos = new LuaSourcePosition(0, 0);
        System.out.println(pos);

        LuaTokenizer tokenizer = new LuaTokenizer("and.or.local.+-,==");
        for (LuaToken token : tokenizer) {
            System.out.println(token);
        }
    }
}
