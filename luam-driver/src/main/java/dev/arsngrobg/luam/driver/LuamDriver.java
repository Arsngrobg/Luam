package dev.arsngrobg.luam.driver;

import dev.arsngrobg.luam.parser.*;

public final class LuamDriver {
    public static void main(String[] args) {
        LuaSource src = LuaSource.ofFile("../examples/2-arithmetic.lua").orElseThrow();
        System.out.println(src.getContent());

        System.out.println(src.charAt(new LuaSourcePosition(2, 10)));
        System.out.println(src.eof());
        System.out.println(src.stringAt("local x", new LuaSourcePosition(2, 0)));
    }
}
