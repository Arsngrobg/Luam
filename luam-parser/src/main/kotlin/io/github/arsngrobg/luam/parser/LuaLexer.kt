package io.github.arsngrobg.luam.parser

import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.Files

import kotlin.io.path.Path
import kotlin.math.absoluteValue

// types
typealias Pos = LuaSourcePosition

/**
 * The set of valid tokens in Lua 5.1.
 */
enum class LuaTokenKind {
    NAME,

    STRING, NUMBER,

    AND,      BREAK,    DO,       ELSE,     ELSEIF,
    END,      FALSE,    FOR,      FUNCTION, IF,
    IN,       LOCAL,    NIL,      NOT,      OR,
    REPEAT,   RETURN,   THEN,     TRUE,     UNTIL,    WHILE,

    ADD, SUB, MUL, DIV, MOD, POW, LEN,

    EQ,  NEQ, LTE, GTE, LT,  GT,  ASSIGN,

    LPAREN, RPAREN, LBRACE, RBRACE, LBRACK, RBRACK
}

/**
 * A 2-dimensional positional type that maps to a position in a Lua source file.
 * @property line the vertical component
 * @property column the horizontal component
 */
data class LuaSourcePosition(val line: Int, val column: Int) {
    override fun toString(): String = "(${line},${column}})"
}

/**
 * An 8-bit clean, encoding agnostic byte representation of Lua source code.
 * @property lineTotal the total number of lines in the source code
 * @property length the actual size of the source code *(in bytes)*
 * @property eof the position of the EOF
*/
class LuaSource(private val bytes: ByteArray) : Iterable<Char> {
    private val lineIndices: IntArray = buildList {
        add(0)
        for ((offset, byte) in bytes.withIndex()) {
            val ch = byte.toInt().toChar()
            if (ch != '\n') continue
            add(offset + 1)
        }
    }.toIntArray()

    val lineTotal: Int = lineIndices.size
    val length:    Int = bytes.size
    val eof:       Pos = Pos(lineTotal - 1, bytes.size - lineIndices.last())

    fun toIndex(line: Int, column: Int): Int {
        if (line !in lineIndices.indices) {
            return -1
        }

        val idx = lineIndices[line] + column
        if (idx !in bytes.indices) {
            return -1
        }
        if (line >= lineIndices.lastIndex || idx >= lineIndices[line+1]) {
            return -1
        }
        return idx
    }

    operator fun get(pos: Pos): Char =
        get(toIndex(pos.line, pos.column))

    operator fun get(index: Int): Char =
        if (index in bytes.indices) bytes[index].toInt().toChar()
        else '\n'

    override fun iterator(): Iterator<Char> =
        String(bytes, StandardCharsets.ISO_8859_1).iterator()

    override fun hashCode():          Int     = bytes.contentHashCode()
    override fun equals(other: Any?): Boolean = other is LuaSource && bytes.contentEquals(other.bytes)
    override fun toString():          String  = "LuaSource[SIZE=${length} LINES=${lineTotal}]"

    companion object {
        fun ofCode(code: String): LuaSource =
            LuaSource(code.toByteArray(StandardCharsets.ISO_8859_1))

        fun ofFile(file: File): LuaSource =
            LuaSource(Files.readAllBytes(file.toPath()))
    }
}
