package dev.arsngrobg.luam.parser;

public enum LuaTokenKind {
    // Identifiers
    NAME,

    // Literals
    STRING, NUMBER,

    // Keywords
    AND,      BREAK,    DO,       ELSE,     ELSEIF,
    END,      FALSE,    FOR,      FUNCTION, IF,
    IN,       LOCAL,    NIL,      NOT,      OR,
    REPEAT,   RETURN,   THEN,     TRUE,     UNTIL,    WHILE,

    // Arithmetic Operators
    ADD, SUB, MUL, DIV, MOD, POW, LEN,

    // Relational Operators
    EQ, NEQ, LTE, GTE, LT, GT, ASSIGN,

    // Delimiters
    LPAREN, RPAREN, LBRACE, RBRACE, LBRACK, RBRACK
}
