package io.github.arsngrobg.luam.driver

import com.github.ajalt.clikt.core.*
import com.github.ajalt.mordant.rendering.AnsiLevel
import com.github.ajalt.mordant.terminal.Terminal

fun main(argv: Array<String>): Unit =
    LuamCommand().context {
        terminal = Terminal(AnsiLevel.TRUECOLOR, interactive = true)
    }.main(argv)
