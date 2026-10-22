package io.github.arsngrobg.luam.compiler

import java.nio.charset.StandardCharsets
import java.nio.file.Path

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
 *  - [None]:   no semantic information for the [LuaToken]
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

fun tokenize(src: String): List<LuaToken> {
    val bytes = src.toByteArray(StandardCharsets.ISO_8859_1)
    return buildList {
        var position = 0
        while (position < bytes.size) {
            while (bytes[position].toInt().toChar().isWhitespace()) {
                position++
            }
            val ch = bytes[position++].toInt().toChar()
            // ADD,    SUB,    MUL,    DIV,    MOD,    POW,    LEN,
            // EQ,     NEQ,    LTE,    GTE,    LT,     GT,     ASSIGN,
            // LPAREN, RPAREN, LBRACE, RBRACE, LBRACK, RBRACK,
            // SEMI_COLON
            when (ch) {
                '+' -> add(LuaToken(LuaTokenKind.ADD))
                '-' -> {
                    if (position < bytes.size && bytes[position].toInt().toChar() == '-') {
                        while (position < bytes.size && bytes[position].toInt().toChar() != '\n') {
                            position++
                        }
                    } else {
                        add(LuaToken(LuaTokenKind.SUB))
                    }
                }
                '*' -> add(LuaToken(LuaTokenKind.MUL))
                '/' -> add(LuaToken(LuaTokenKind.DIV))
                '%' -> add(LuaToken(LuaTokenKind.MOD))
                '^' -> add(LuaToken(LuaTokenKind.POW))
                '#' -> add(LuaToken(LuaTokenKind.LEN))
                '=' -> {
                    if (position < bytes.size && bytes[position].toInt().toChar() == '=') {
                        position++
                        add(LuaToken(LuaTokenKind.EQ))
                    } else {
                        add(LuaToken(LuaTokenKind.ASSIGN))
                    }
                }
                '~' -> {
                    if (position < bytes.size && bytes[position].toInt().toChar() == '=') {
                        position++
                        add(LuaToken(LuaTokenKind.NEQ))
                    } else {
                        error("unexpected symbol near '~'")
                    }
                }
                '<' -> {
                    if (position < bytes.size && bytes[position].toInt().toChar() == '=') {
                        position++
                        add(LuaToken(LuaTokenKind.LTE))
                    } else {
                        add(LuaToken(LuaTokenKind.LT))
                    }
                }
                '>' -> {
                    if (position < bytes.size && bytes[position].toInt().toChar() == '=') {
                        position++
                        add(LuaToken(LuaTokenKind.GTE))
                    } else {
                        add(LuaToken(LuaTokenKind.GT))
                    }
                }
                '(' -> add(LuaToken(LuaTokenKind.LPAREN))
                ')' -> add(LuaToken(LuaTokenKind.RPAREN))
                '{' -> add(LuaToken(LuaTokenKind.LBRACE))
                '}' -> add(LuaToken(LuaTokenKind.RBRACE))
                '[' -> add(LuaToken(LuaTokenKind.LBRACK))
                ']' -> add(LuaToken(LuaTokenKind.RBRACK))
                '\'' -> {
                    val start = position
                    while (position < bytes.size && bytes[position].toInt().toChar() != '\'') {
                        position++
                    }
                    val str = String(bytes.copyOfRange(start, position))
                    position++
                    add(LuaToken(LuaTokenKind.STRING, SemInfo.String(str)))
                }
                '\"' -> {
                    val start = position
                    while (position < bytes.size && bytes[position].toInt().toChar() != '\"') {
                        position++
                    }
                    val str = String(bytes.copyOfRange(start, position))
                    position++
                    add(LuaToken(LuaTokenKind.STRING, SemInfo.String(str)))
                }
                ';' -> add(LuaToken(LuaTokenKind.SEMI_COLON))
                else -> {
                    val start = position - 1
                    while (position < bytes.size && !bytes[position].toInt().toChar().isWhitespace()) {
                        position++
                    }
                    // AND,      BREAK,    DO,       ELSE,     ELSEIF,
                    // END,      FALSE,    FOR,      FUNCTION, IF,
                    // IN,       LOCAL,    NIL,      NOT,      OR,
                    // REPEAT,   RETURN,   THEN,     TRUE,     UNTIL,    WHILE
                    val word = String(bytes.copyOfRange(start, position))
                    val kind = when (word) {
                        "and"      -> LuaTokenKind.AND
                        "break"    -> LuaTokenKind.BREAK
                        "do"       -> LuaTokenKind.DO
                        "else"     -> LuaTokenKind.ELSE
                        "elseif"   -> LuaTokenKind.ELSEIF
                        "end"      -> LuaTokenKind.END
                        "false"    -> LuaTokenKind.FALSE
                        "for"      -> LuaTokenKind.FOR
                        "function" -> LuaTokenKind.FUNCTION
                        "if"       -> LuaTokenKind.IF
                        "in"       -> LuaTokenKind.IN
                        "local"    -> LuaTokenKind.LOCAL
                        "nil"      -> LuaTokenKind.NIL
                        "not"      -> LuaTokenKind.NOT
                        "or"       -> LuaTokenKind.OR
                        "repeat"   -> LuaTokenKind.REPEAT
                        "return"   -> LuaTokenKind.RETURN
                        "then"     -> LuaTokenKind.THEN
                        "true"     -> LuaTokenKind.TRUE
                        "until"    -> LuaTokenKind.UNTIL
                        "while"    -> LuaTokenKind.WHILE
                        else       -> LuaTokenKind.NAME
                    }
                    if (kind == LuaTokenKind.NAME) {
                        add(LuaToken(kind, SemInfo.String(word)))
                    } else {
                        add(LuaToken(kind))
                    }
                }
            }
        }
    }
}
