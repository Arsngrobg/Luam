package io.github.arsngrobg.luam.compiler.lua

import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path

/** A read-only view into Lua source code. */
@ExperimentalUnsignedTypes
class LuaSource private constructor(bytes: UByteArray) {
    companion object {
        val empty: LuaSource = LuaSource(ubyteArrayOf())

        fun loadFile(path: Path): LuaSource {
            val bytes = Files.readAllBytes(path).toUByteArray()
            return if (bytes.isEmpty()) empty else LuaSource(bytes)
        }

        fun loadString(src: String): LuaSource {
            val bytes = src.toByteArray(StandardCharsets.ISO_8859_1).toUByteArray()
            return if (bytes.isEmpty()) empty else LuaSource(bytes)
        }
    }

    private val bytes: UByteArray = bytes.copyOf()

    val stringContent: String = String(bytes.toByteArray(), StandardCharsets.ISO_8859_1)
    val length:        Int    = bytes.size

    operator fun get(index: Int): UByte = bytes[index]

    override fun hashCode():            Int     = bytes.contentHashCode()
    override fun equals  (other: Any?): Boolean = (other is LuaSource) && (bytes.contentEquals(other.bytes))
    override fun toString():            String  = "LuaSource($length bytes...)"
}

@JvmInline
value class LuaChar(val byte: UByte) {
    val codepoint:    UInt    get() = byte.toUInt()
    val isWhitespace: Boolean get() = codepoint in (9u..10u) || codepoint == 32u
    val isNumber:     Boolean get() = codepoint in (48u..57u)
    val isLetter:     Boolean get() = codepoint in (65u..90u) || codepoint in (97u..122u)
}

fun UByte.toLuaChar(): LuaChar =
    LuaChar(this)

@ExperimentalUnsignedTypes
class LuaSourceReader(val source: LuaSource) : Iterator<LuaChar> {
    var position: Int = 0
        private set

    val canRead: Boolean
        get() = position < source.length

    override fun hasNext(): Boolean =
        canRead

    private fun ensureReadability(): Unit {
        if (!canRead) {
            throw NoSuchElementException("reader has reached the end of file")
        }
    }

    fun peek(): LuaChar {
        ensureReadability()
        return source[position].toLuaChar()
    }

    fun pop(): LuaChar {
        ensureReadability()
        return source[position++].toLuaChar()
    }

    override fun next(): LuaChar =
        pop()
}
