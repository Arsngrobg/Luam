package dev.arsngrobg.luam.driver;

import dev.arsngrobg.luam.parser.*;

public final class LuamDriver {
    public static void main(String[] args) {
        LuaSource src1 = LuaSource.ofFile("../examples/1-hello-world.lua").orElseThrow();
        LuaSource src2 = LuaSource.ofFile("../examples/2-arithmetic.lua").orElseThrow();
        System.out.println(src1);
        System.out.println(src2);
    }
}
