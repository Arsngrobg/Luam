package io.github.arsngrobg.luam.compiler

/** The set of valid token in Lua 5.1 */
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
 * Union type for semantic information about a [LuaToken]
 *  - [None]:   no semantic information for the [LuaToken]
 *  - [Number]: IEEE-754 floating point decimal
 *  - [String]: length-based string
 */
sealed interface SemInfo {
    object None : SemInfo
    @JvmInline value class Number(val x: kotlin.Double) : SemInfo
    @JvmInline value class String(val s: kotlin.String) : SemInfo
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
            is SemInfo.String -> "$kind(${info.s})"
        }
}
