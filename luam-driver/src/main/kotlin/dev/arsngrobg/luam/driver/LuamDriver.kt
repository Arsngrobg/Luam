package dev.arsngrobg.luam.driver

import dev.arsngrobg.luam.parser.LuaSource
import dev.arsngrobg.luam.parser.LuaSourcePosition
import kotlin.system.exitProcess

fun main(argv: Array<String>) {
    val src = LuaSource.ofFile("./examples/1-hello-world.lua")
    println(src.eof)
    println(src.lastIndex)

    for (c in src) {
        print(c)
    }

    exitProcess(if (src[src.lastIndex+1] != '\u0000') 1 else 0)
}
