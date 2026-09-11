package io.github.arsngrobg.luam.driver

import java.io.File

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.UsageError
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.multiple
import com.github.ajalt.clikt.parameters.arguments.help
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.help
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.convert
import com.github.ajalt.clikt.parameters.options.versionOption
import com.github.ajalt.clikt.parameters.types.uint
import com.github.ajalt.clikt.parameters.types.file
import com.github.ajalt.clikt.parameters.types.restrictTo

import io.github.arsngrobg.luam.parser.*

object TODO {
    const val LUAM_VERSION         = "1.0"
    const val DEFAULT_ARCHIVE_NAME = "dinnerbone" // funny minecraft easter egg haha
    const val LATEST_FORMAT        = 48u          // will use latest version by default
}

class LuamCommand : CliktCommand("luam") {
    val files: List<File>
        by argument("file")
               .file(mustExist = true, canBeDir = false)
               .multiple()
               .help("Sequence of Lua source files")

    val outName: File
        by option("-o")
               .file(mustExist = false, canBeDir = false)
               .default(File(TODO.DEFAULT_ARCHIVE_NAME))
               .help("The name of the output archive")

    val outImg: String?
        by option("-i")
               .help("The image used to represent the datapack")

    val verbose: Boolean
        by option("-v")
               .flag()
               .help("Whether Luam should be verbose")

    val debug: Boolean
        by option("-debug")
            .flag()
            .help("Do not perform optimizations when compiling the source tree")

    val desc: String?
        by option("--description")
               .help("Tags the datapack with this description")

    val format: Pair<UInt, UInt>
        by option("--format")
               .convert { fmts ->
                   val pairs = fmts.split(",").map {
                       it.trim().toUIntOrNull()?:fail("Format arg must be an uint")
                   }

                   when (pairs.size) {
                       1    -> pairs[0] to pairs[0]
                       2    -> pairs[0] to pairs[1]
                       else -> fail("Format arg must be given in a pair")
                   }
               }
              .default(TODO.LATEST_FORMAT to TODO.LATEST_FORMAT)
              .help("Specifies the format of the datapack")

    init {
        versionOption(TODO.LUAM_VERSION, names = setOf("--version"))
    }

    override fun run() {
        if (files.isEmpty()) {
            throw UsageError("no files given")
        }

        repeat(files.size) { idx ->
            echo(files[idx].name)
        }
    }
}
