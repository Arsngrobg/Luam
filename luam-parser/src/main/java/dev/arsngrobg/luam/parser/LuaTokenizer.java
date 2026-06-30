package dev.arsngrobg.luam.parser;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Queue;

public final class LuaTokenizer implements Iterator<LuaToken>, Iterable<LuaToken> {
    private final String source;

    private final Queue<LuaToken>   tokenStack   = new ArrayDeque<>();
    private       LuaSourcePosition lastPosition = new LuaSourcePosition(0, 0);
    private       int               position     = 0;

    public LuaTokenizer(String source) {
        this.source = Objects.requireNonNull(source, "LuaTokenizer source cannot be NULL");
    }

    private char popCharacter() throws IndexOutOfBoundsException {
        return source.charAt(position++);
    }

    private char peekCharacter() throws IndexOutOfBoundsException {
        return source.charAt(position);
    }

    @Override
    public LuaToken next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException("LuaTokenizer has reached EOF");
        }

        LuaTokenKind kind = LuaTokenKind.ILLEGAL;
        char ch = popCharacter();

        // keywords
        if (Character.isAlphabetic(ch)) {
            int wordLength = 1;
            while (!isEOF() && Character.isAlphabetic(peekCharacter())) {
                popCharacter();
                wordLength++;
            }

            if (source.regionMatches(position-wordLength, "and", 0, wordLength)) {
                kind = LuaTokenKind.AND;
            } else if (source.regionMatches(position-wordLength, "break", 0, wordLength)) {
                kind = LuaTokenKind.BREAK;
            } else if (source.regionMatches(position-wordLength, "do", 0, wordLength)) {
                kind = LuaTokenKind.DO;
            } else if (source.regionMatches(position-wordLength, "else", 0, wordLength)) {
                kind = LuaTokenKind.ELSE;
            } else if (source.regionMatches(position-wordLength, "elseif", 0, wordLength)) {
                kind = LuaTokenKind.ELSEIF;
            } else if (source.regionMatches(position-wordLength, "end", 0, wordLength)) {
                kind = LuaTokenKind.END;
            } else if (source.regionMatches(position-wordLength, "false", 0, wordLength)) {
                kind = LuaTokenKind.FALSE;
            } else if (source.regionMatches(position-wordLength, "for", 0, wordLength)) {
                kind = LuaTokenKind.FOR;
            } else if (source.regionMatches(position-wordLength, "function", 0, wordLength)) {
                kind = LuaTokenKind.FUNCTION;
            } else if (source.regionMatches(position-wordLength, "if", 0, wordLength)) {
                kind = LuaTokenKind.IF;
            } else if (source.regionMatches(position-wordLength, "in", 0, wordLength)) {
                kind = LuaTokenKind.IN;
            } else if (source.regionMatches(position-wordLength, "local", 0, wordLength)) {
                kind = LuaTokenKind.LOCAL;
            } else if (source.regionMatches(position-wordLength, "nil", 0, wordLength)) {
                kind = LuaTokenKind.NIL;
            } else if (source.regionMatches(position-wordLength, "not", 0, wordLength)) {
                kind = LuaTokenKind.NOT;
            } else if (source.regionMatches(position-wordLength, "or", 0, wordLength)) {
                kind = LuaTokenKind.OR;
            } else if (source.regionMatches(position-wordLength, "repeat", 0, wordLength)) {
                kind = LuaTokenKind.REPEAT;
            } else if (source.regionMatches(position-wordLength, "return", 0, wordLength)) {
                kind = LuaTokenKind.RETURN;
            } else if (source.regionMatches(position-wordLength, "then", 0, wordLength)) {
                kind = LuaTokenKind.THEN;
            } else if (source.regionMatches(position-wordLength, "true", 0, wordLength)) {
                kind = LuaTokenKind.TRUE;
            } else if (source.regionMatches(position-wordLength, "until", 0, wordLength)) {
                kind = LuaTokenKind.UNTIL;
            } else if (source.regionMatches(position-wordLength, "while", 0, wordLength)) {
                kind = LuaTokenKind.WHILE;
            }
        } else {
            char adjChar;
            switch (ch) {
                // arithmetic operators
                case '+':
                    kind = LuaTokenKind.ADD;
                    break;
                case '-':
                    kind = LuaTokenKind.SUB;
                    break;
                case '*':
                    kind = LuaTokenKind.MUL;
                    break;
                case '/':
                    kind = LuaTokenKind.DIV;
                    break;
                case '%':
                    kind = LuaTokenKind.MOD;
                    break;
                case '#':
                    kind = LuaTokenKind.LEN;
                    break;

                // relational operators
                case '=':
                    adjChar = popCharacter();
                    switch (adjChar) {
                        case '=':
                            kind = LuaTokenKind.EQ;
                            break;
                        case '~':
                            kind = LuaTokenKind.NEQ;
                            break;
                        default:
                            kind = LuaTokenKind.ASSIGN;
                    }
                    break;
                case '<':
                    if (isEOF()) {
                        kind = LuaTokenKind.LT;
                    } else {
                        adjChar = popCharacter();
                        switch (adjChar) {
                            case '=':
                                kind = LuaTokenKind.LTE;
                                break;
                            default:
                                kind = LuaTokenKind.LT;
                        }
                    }
                    break;
                case '>':
                    if (isEOF()) {
                        kind = LuaTokenKind.GT;
                    } else {
                        adjChar = popCharacter();
                        switch (adjChar) {
                            case '=':
                                kind = LuaTokenKind.GTE;
                                break;
                            default:
                                kind = LuaTokenKind.GT;
                        }
                    }
                    break;

                // delimiters
                case ',':
                    kind = LuaTokenKind.COMMA;
                    break;
                case '.':
                    kind = LuaTokenKind.DOT;
                    break;
                case '(':
                    kind = LuaTokenKind.LPAREN;
                    break;
                case ')':
                    kind = LuaTokenKind.RPAREN;
                    break;
                case '{':
                    kind = LuaTokenKind.LBRACE;
                    break;
                case '}':
                    kind = LuaTokenKind.RBRACE;
                    break;
                case '[':
                    kind = LuaTokenKind.LBRACK;
                    break;
                case ']':
                    kind = LuaTokenKind.RBRACK;
                    break;

                case '\n':
                    lastPosition = new LuaSourcePosition(lastPosition.line()+1, lastPosition.column());
                    return next();

                default:
                    kind = LuaTokenKind.ILLEGAL;
            };
        }

        return new LuaToken(kind, new LuaSourcePosition(position, position), new LuaSourcePosition(position, position));
    }

    @Override
    public boolean hasNext() {
        return !isEOF();
    }

    public boolean isEOF() {
        return position >= source.length();
    }

    @Override
    public Iterator<LuaToken> iterator() {
        return this;
    }

    public String source() {
        return source;
    }
}
