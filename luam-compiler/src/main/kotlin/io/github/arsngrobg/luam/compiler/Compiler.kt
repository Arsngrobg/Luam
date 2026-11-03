package io.github.arsngrobg.luam.compiler

/** The set of valid tokens in Lua 5.1 */
enum class LuaTokenKind {
    NAME,

    STRING, NUMBER,

    AND,      BREAK,    DO,       ELSE,     ELSEIF,
    END,      FALSE,    FOR,      FUNCTION, IF,
    IN,       LOCAL,    NIL,      NOT,      OR,
    REPEAT,   RETURN,   THEN,     TRUE,     UNTIL,    WHILE,

    ADD, SUB, MUL, DIV, MOD, POW, LEN,

    EQ,  NEQ, LTE, GTE, LT,  GT,  ASSIGN,

    LPAREN, RPAREN, LBRACE, RBRACE, LBRACK, RBRACK,

    DOT, DOT_PAIR, ELLIPSIS,

    SEMI_COLON
}

/**
 * Union type for semantic information about a [LuaToken]
 *  - [None]: no semantic information for the [LuaToken]
 *  - [Number]: IEEE-754 single-precision floating-point decimal
 *  - [String]: length-based string
 */
sealed interface SemInfo {
    object None : SemInfo
    data class Number(val x: Float)         : SemInfo
    data class String(val s: kotlin.String) : SemInfo
}

/**
 * A lexical unit in Lua source code
 *  @property[kind] the "type" of [LuaToken] this is
 *  @property[info] the semantic information about this [LuaToken]
 */
data class LuaToken(
    val kind: LuaTokenKind,
    val info: SemInfo = SemInfo.None
) {
    override fun toString(): String =
        when (info) {
            is SemInfo.None   -> "$kind"
            is SemInfo.Number -> "$kind(${info.x})"
            is SemInfo.String -> "$kind(\"${info.s}\")"
        }
}

fun lex(bytes: ByteArray): Iterator<LuaToken> = sequence {
    var position = 0

    while (position < bytes.size) {
        when (val current = bytes[position].toInt().toChar()) {
            ' ', '\t', '\r', '\n' -> position++

            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.' -> {
                val start = position++
                var dpoint = current == '.'

                while (position < bytes.size) {
                    when (bytes[position].toInt().toChar()) {
                        '.' -> {
                            if (!dpoint) {
                                dpoint = true
                                position++
                            } else {
                                break
                            }
                        }
                        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> position++
                        else -> break
                    }
                }
                val n = String(bytes.slice(start..position).toByteArray()).toFloat()
                yield(LuaToken(LuaTokenKind.NUMBER, SemInfo.Number(n)))
            }

            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
            '_' -> {
                
            }
        }
    }
}.iterator()
