package dev.arsngrobg.luam.parser;

/**
 * <p>A token of the Lua scripting language.</p>
 * <p>Tokens are categorised into the following semantic groups, as per the
 *    <a href="https://www.lua.org/manual/5.1/manual.html#2.1">Lua manual</a>:
 *    <ul>
 *       <li><b>Literals</b>:   variadic data <i>(i.e. string literals, variable names, numbers)</i></li>
 *       <li><b>Keywords</b>:   language-reserved words <i>(e.g. {@code function}, {@code true}/{@code false})</i></li>
 *       <li><b>Operators</b>:  arithmetic and relational symbols used in expressions</li>
 *       <li><b>Delimiters</b>: structural symbols for scoping parameters, tables, and indexing</li>
 *    </ul>
 * </p>
 *
 * @see <a href="https://www.lua.org/manual/5.1/manual.html#2.1">Lua 5.1 Manual - Lexical Conventions</a>
 */
public enum LuaToken {
    /// Identifiers
    NAME,

    /// Literals
    STRING,
    NUMBER,

    /// Keywords
    AND,
    BREAK,
    DO,
    ELSE,
    ELSEIF,
    END,
    FALSE,
    FOR,
    FUNCTION,
    IF,
    IN,
    LOCAL,
    NIL,
    NOT,
    OR,
    REPEAT,
    RETURN,
    THEN,
    TRUE,
    UNTIL,
    WHILE,

    /// Arithmetic Operators
    ADD,
    SUB,
    MUL,
    DIV,
    MOD,
    POW,
    LEN,

    /// Relational Operators
    EQ,
    NEQ,
    LTE,
    GTE,
    LT,
    GT,
    ASSIGN,

    /// Delimiters
    LPAREN,
    RPAREN,
    LBRACE,
    RBRACE,
    LBRACK,
    RBRACK
}
