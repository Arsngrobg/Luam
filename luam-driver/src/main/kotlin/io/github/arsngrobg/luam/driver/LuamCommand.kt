package io.github.arsngrobg.luam.driver

import java.io.File

import io.github.arsngrobg.luam.compiler.LuamConstants
import io.github.arsngrobg.luam.compiler.LuamOutputVersion

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.core.UsageError
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.default
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
import java.net.URI
import java.nio.file.Paths

// class LuamCommand : CliktCommand("luam") {
//     val files: List<File>
//         by argument("file")
//                .file(mustExist = true, canBeDir = false)
//                .multiple()
//                .help("Sequence of Lua source files")

//     val outName: File
//         by option("-o")
//                .file(mustExist = false, canBeDir = false)
//                .default(File(LuamConstants.DATAPACK_DEFAULT))
//                .help("The name of the output archive")

//     val outImg: String?
//         by option("-i")
//                .help("The image used to represent the datapack")

//     val verbose: Boolean
//         by option("-v")
//                .flag()
//                .help("Whether Luam should be verbose")

//     val debug: Boolean
//         by option("-debug")
//             .flag()
//             .help("Do not perform optimizations when compiling the source tree")

//     val desc: String?
//         by option("--description")
//                .help("Tags the datapack with this description")

//     val format: Pair<UInt, UInt>
//         by option("--format")
//                .convert { fmts ->
//                    val pairs = fmts.split(",").map {
//                        it.trim().toUIntOrNull()?:fail("Format arg must be an uint")
//                    }

//                    when (pairs.size) {
//                        1    -> pairs[0] to pairs[0]
//                        2    -> pairs[0] to pairs[1]
//                        else -> fail("Format arg must be given in a pair")
//                    }
//                }
//               .default(LuamConstants.LATEST_FORMAT to LuamConstants.LATEST_FORMAT)
//               .help("Specifies the format of the datapack")

//     init {
//         versionOption(LuamConstants.VERSION_STRING, names = setOf("--version"))
//     }

//     override fun run() {
//         if (files.isEmpty()) {
//             throw UsageError("no files given")
//         }

//         LuamOutputVersion.entries.forEach {
//             println(it.actualName)
//         }

//         repeat(files.size) { idx ->
//             echo(files[idx].name)
//         }
//     }
// }

class LuamBuildCommand : CliktCommand(name = "build") {
    val directory: File
        by argument("directory")
            .file(mustExist = true, canBeFile = false, canBeDir = true)
            .help("relative path to a directory to compile")
            .default(Paths.get("").toAbsolutePath().toFile())

    val minVersion: LuamOutputVersion
        by option("--min-version")
            .convert {
                LuamOutputVersion.valueOf("MC" + it.replace('.', '_'))
            }
            .default(LuamOutputVersion.entries.last())
            .help("the minimum Minecraft version the datapack should support")

    val maxVersion: LuamOutputVersion
        by option("--max-version")
            .convert {
                LuamOutputVersion.valueOf("MC" + it.replace('.', '_'))
            }
            .default(LuamOutputVersion.entries.last())
            .help("the maximum Minecraft version the datapack should support")

    override fun run(): Unit = Unit

    override fun help(context: Context): String =
        "Compiles the folder of Lua source files into a datapack"
}

class LuamCLI : CliktCommand(name = "luam") {
    init {
        versionOption(LuamConstants.VERSION_STRING, names = setOf("--version"))
    }

    override fun run(): Unit = Unit
}
