package io.github.arsngrobg.luam.driver

fun executeBuild(argv: Array<String>): Unit {
    var isDirectoryActuallyAFile = false
    for (argi in 1 until argv.size) {
        val arg = argv[argi]
        if (arg.startsWith("-out:")) {
            val value = arg.removePrefix("-out:")
            println(value)
        } else
        if (arg.startsWith("-icon:")) {
            val value = arg.removePrefix("-icon:")
            println(value)
        } else
        if (arg.startsWith("-version:")) {
            val value = arg.removePrefix("-version:")
            println(value)
        }
    }
}

fun executeHelp(argv: Array<String>): Unit {
    TODO("Help message")
}

fun main(argv: Array<String>): Unit {
    when (val command = argv.getOrNull(0)) {
        "build" -> executeBuild(argv)
        null    -> executeHelp(argv)
        else    -> error("Luam: unknown command: \"$command\"")
    }
}
