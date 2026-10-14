package io.github.arsngrobg.luam.driver

import java.nio.file.Path

import io.github.arsngrobg.luam.compiler.LuamConstants

object LuamFlags {
    var treatDirAsFile: Boolean = false
    var icon:           Path    = Path.of("pack.png")
    var outputLocation: Path    = Path.of(".")
}

fun executeBuild(argv: Array<String>): Unit {
    var dir = Path.of(".")
    for (argi in 1 until argv.size) {
        val arg = argv[argi]
        if (arg.startsWith("-out:")) {
            val value = arg.removePrefix("-out:")
            LuamFlags.outputLocation = Path.of(value)
        } else
        if (arg.startsWith("-icon:")) {
            val value = arg.removePrefix("-icon:")
            LuamFlags.icon = Path.of(value)
        } else
        if (arg.startsWith("-version:")) {
            val value = arg.removePrefix("-version:")
            println(value)
        } else
        if (arg == "-file") {
            LuamFlags.treatDirAsFile = true
        } else
        if (argi == 1) {
            dir = Path.of(arg)
        }
    }
}

fun executeVersion(argv: Array<String>): Unit {
    println("luam version ${LuamConstants.VERSION_STRING}")
}

fun executeHelp(argv: Array<String>): Unit {
    TODO("Help message")
}

fun main(argv: Array<String>): Unit {
    when (val command = argv.getOrNull(0)) {
        "build"      -> executeBuild(argv)
        "version"    -> executeVersion(argv)
        "help", null -> executeHelp(argv)
        else         -> error("Luam: unknown command: \"$command\"")
    }
}
