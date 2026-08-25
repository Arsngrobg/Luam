package dev.arsngrobg.luam.driver

import java.nio.file.Path

import com.github.ajalt.clikt.core.*
import com.github.ajalt.clikt.parameters.arguments.*
import com.github.ajalt.clikt.parameters.options.*
import com.github.ajalt.clikt.parameters.types.*

import dev.arsngrobg.luam.parser.*

object Meta {
    const val LUAM_VERSION: String = "1.0"
}

class LuamCommands : CliktCommand("luam") {
    val files:   List<Path>      by argument("file").path().multiple()    .help("Lua source files")
    val outName: String          by option("-o").default("dinnerbone.zip").help("The name of the output archive")
    val outImg:  String?         by option("-i")                          .help("The image used to represent the datapack when viewed in Datapacks")
    val verbose: Boolean         by option("-v").flag()                   .help("Whether Luam should be verbose")
    val optLvl:  Int             by option("-O").int().default(0)         .help("The optimization level")

    init {
        versionOption(Meta.LUAM_VERSION, names = setOf("--version"))
    }

    override fun run() {
        if (files.isEmpty()) {
            throw UsageError("no files given")
        }

        repeat(files.size) { idx ->
            println(files[idx])
        }
    }
}

fun main(args: Array<String>) = LuamCommands().main(args)
