package io.github.arsngrobg.luam.driver

import java.io.File

import io.github.arsngrobg.luam.compiler.LuamConstants

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
import io.github.arsngrobg.luam.compiler.LuamOutputFormat

class LuamCommand : CliktCommand("luam") {
    val files: List<File>
        by argument("file")
               .file(mustExist = true, canBeDir = false)
               .multiple()
               .help("Sequence of Lua source files")

    val outName: File
        by option("-o")
               .file(mustExist = false, canBeDir = false)
               .default(File(LuamConstants.DATAPACK_DEFAULT))
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
              .default(LuamConstants.LATEST_FORMAT to LuamConstants.LATEST_FORMAT)
              .help("Specifies the format of the datapack")

    init {
        versionOption(LuamConstants.VERSION_STRING, names = setOf("--version"))
    }

    override fun run() {
        if (files.isEmpty()) {
            throw UsageError("no files given")
        }

        LuamOutputFormat.entries.forEach {
            println(it.actualName)
        }

        // function check(x)
        //     if x > 0 then
        //         print('foo')
        //     else
        //         print('bar')
        //     end
        // end
        // val tokens = buildList<LuaToken> {
        //     add(LuaToken(LuaTokenKind.FUNCTION))
        //     add(LuaToken(LuaTokenKind.NAME, SemInfo.String("check")))
        //     add(LuaToken(LuaTokenKind.LPAREN))
        //     add(LuaToken(LuaTokenKind.NAME, SemInfo.String("x")))
        //     add(LuaToken(LuaTokenKind.RPAREN))
        //     add(LuaToken(LuaTokenKind.IF))
        //     add(LuaToken(LuaTokenKind.NAME, SemInfo.String("x")))
        //     add(LuaToken(LuaTokenKind.LT))
        //     add(LuaToken(LuaTokenKind.NUMBER, SemInfo.Number(0.0)))
        //     add(LuaToken(LuaTokenKind.THEN))
        //     add(LuaToken(LuaTokenKind.NAME, SemInfo.String("print")))
        //     add(LuaToken(LuaTokenKind.LPAREN))
        //     add(LuaToken(LuaTokenKind.STRING, SemInfo.String("foo")))
        //     add(LuaToken(LuaTokenKind.RPAREN))
        //     add(LuaToken(LuaTokenKind.ELSE))
        //     add(LuaToken(LuaTokenKind.NAME, SemInfo.String("print")))
        //     add(LuaToken(LuaTokenKind.LPAREN))
        //     add(LuaToken(LuaTokenKind.STRING, SemInfo.String("bar")))
        //     add(LuaToken(LuaTokenKind.RPAREN))
        //     add(LuaToken(LuaTokenKind.END))
        //     add(LuaToken(LuaTokenKind.END))
        // }
        // tokens.forEach { println(it) }

        repeat(files.size) { idx ->
            echo(files[idx].name)
        }
    }
}
