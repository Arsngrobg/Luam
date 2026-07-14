package dev.arsngrobg.luam.parser;

/**
 * The set of valid tokens in Lua.
 */
public enum LuaTokenKind {
    /**
     * Any string of letters, digits, and underscores, not beginning with a digit and not being a reserved word.
     *
     * <p>
     * <i>They are also called identifiers.</i>
     */
    NAME,

    /**
     * A string literal is any sequence of characters, enclosed with matching double or single quotes.
     * The matching quote is decided by the starting quote.
     *
     * <p>
     * Lua strings, just like its source code, is formatted as <b>UTF-8</b>.
     *
     * <p>
     * <i>For example: {@code "Hello, World!"} or {@code 'Hello, World!'}</i>
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/2.4.html">PIL - Strings</a></i>
     */
    STRING,
    /**
     * A number literal represents any double-precision floating-point number.
     * Luam uses double-precision floating-point numbers as datapacks natively expect doubles (or integers).
     *
     * <p>
     * <i>Numbers can be represented as such: {@code 4}, {@code 0.4}, {@code 4.57e-3}, {@code 0.3e12}, or {@code 5e+20}</i>
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/2.3.html">PIL - Numbers</a></i>
     */
    NUMBER,

    /**
     * An assignment operation is an operation that mutates a variable or a table field.
     *
     * <p>
     * <i>For example: {@code local x = 1} or {@code tbl.x = 1}</i>
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/4.1.html">PIL - Assignment</a></i>
     */
    ASSIGN,

    /**
     * The literal Lua keyword {@code and}.
     *
     * <p>
     * Returns its first argument if it is {@code false}; otherwise, it returns its second argument.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/3.3.html">PIL - Logical Operators</a></i>
     */
    AND,
    /**
     * The literal Lua keyword {@code break}.
     *
     * <p>
     * Allows jumping out of looping code blocks.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/4.4.html">PIL - Break and Return</a></i>
     */
    BREAK,
    /**
     * The literal Lua keyword {@code do}.
     *
     * <p>
     * Controls scope of code or variables.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/4.2.html">PIL - Local Variables and Blocks</a></i>
     */
    DO,
    /**
     * The literal Lua keyword {@code else}.
     *
     * <p>
     * Provides a base-case for an {@code if} or {@code elseif} statement.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/4.3.1.html">PIL - If Then Else</a></i>
     */
    ELSE,
    /**
     * The literal Lua keyword {@code elseif}.
     *
     * <p>
     * Provides an alternate condition for an {@code if} block.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/4.3.1.html">PIL - If Then Else</a></i>
     */
    ELSEIF,
    /**
     * The literal Lua keyword {@code end}.
     *
     * <p>
     * Terminates a scoped block.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/4.2.html">PIL - Local Variables and Blocks</a></i>
     */
    END,
    /**
     * The literal Lua keyword {@code false}
     *
     * <p>
     * Denotes a true <b>falsey</b> value.
     *
     * <p>
     * {@code false} and {@code nil} are the only values considered <b>falsey</b>.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/2.2.html">PIL - Booleans</a></i>
     */
    FALSE,
    /**
     * The literal Lua keyword {@code for}.
     *
     * <p>
     * Denotes the beginning of a numeric or <b>iterator</b>-based loop.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/4.3.4.html">PIL - Numeric For</a></i>
     */
    FOR,
    /**
     * The literal Lua keyword {@code function}.
     *
     * <p>
     * Abstracts and localises a block of code using scoped variables.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/4.3.4.html">PIL - Functions</a></i>
     */
    FUNCTION,
    /**
     * The literal Lua keyword {@code if}.
     *
     * <p>
     * Contains a conditional expression and can be chained with multiple {@code elseif}, or an {@code else} block.
     * Must always be terminated with the {@code end} keyword.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/4.3.1.html">PIL - If Then Else</a></i>
     */
    IF,
    /**
     * The literal Lua keyword {@code in}.
     *
     * <p>
     * Syntactic sugar over Lua iterators.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/7.1.html">PIL - Iterators and Closures</a></i>
     */
    IN,
    /**
     * The literal Lua keyword {@code local}.
     *
     * <p>
     * Access specifier for a variable, scoping it within the block it was defined in.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/4.2.html">PIL - Local Variables and Blocks</a></i>
     */
    LOCAL,
    /**
     * The literal Lua keyword {@code nil}.
     *
     * <p>
     * Main property is that it is different from any value except from itself.
     * Any global value is {@code nil} by default, and assigning a variable to {@code nil} essentially deletes it.
     * It represents the absence of a value.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/2.1.html">PIL - Nil</a></i>
     */
    NIL,
    /**
     * The literal Lua keyword {@code not}.
     *
     * <p>
     * Negates the conditional expression that follows it.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/3.3.html">PIL - Logical Operators</a></i>
     */
    NOT,
    /**
     * The literal Lua keyword {@code or}.
     *
     * <p>
     * Returns its first argument if it is {@code true}; otherwise, it returns its second argument.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/3.3.html">PIL - Logical Operators</a></i>
     */
    OR,
    /**
     * The literal Lua keyword {@code repeat}.
     *
     * <p>
     * Declares a loop block that while execute at least once until a condition is {@code true}.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/4.3.3.html">PIL - Repeat</a></i>
     */
    REPEAT,
    /**
     * The literal Lua keyword {@code return}.
     *
     * <p>
     * Allows jumping out of any block.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/4.4.html">PIL - Break and Return</a></i>
     */
    RETURN,
    /**
     * The literal Lua keyword {@code then}.
     *
     * <p>
     * Terminates a conditional of an {@code if} or {@code elseif} statement.
     * This makes the end of a condition unambiguous from the code block that follows it.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/4.3.1.html">PIL - If Then Else</a></i>
     */
    THEN,
    /**
     * The literal Lua keyword {@code true}
     *
     * <p>
     * Denotes a true <b>truthy</b> value.
     *
     * <p>
     * Any value that is not {@code false} or {@code nil} is considered <b>truthy</b>.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/2.2.html">PIL - Booleans</a></i>
     */
    TRUE,
    /**
     * The literal Lua keyword {@code until}.
     *
     * <p>
     * Terminates a {@code repeat} loop, and denotes the start of the condition.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/4.3.3.html">PIL - Repeat</a></i>
     */
    UNTIL,
    /**
     * The literal Lua keyword {@code while}.
     *
     * <p>
     * Denotes the beginning of a loop block that iterates as long as the condition is {@code true}.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/4.3.2.html">PIL - While</a></i>
     */
    WHILE,
    /**
     * The literal Lua addition operator ({@code +}).
     *
     * <p>
     * Sums the left and right operands together.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/3.1.html">PIL - Arithmetic Operators</a></i>
     */
    ADD,
    /**
     * The literal Lua subtraction operator ({@code -}).
     *
     * <p>
     * Subtracts the right operand from the left operand.
     *
     * <p>
     * Can also be written in unary form and will negate the right operand.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/3.1.html">PIL - Arithmetic Operators</a></i>
     */
    SUB,
    /**
     * The literal Lua multiplication operator ({@code *}).
     *
     * <p>
     * Multiplies the left operand with the right operand.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/3.1.html">PIL - Arithmetic Operators</a></i>
     */
    MUL,
    /**
     * The literal Lua division operator ({@code /}).
     *
     * <p>
     * Divides the left operand with the right operand.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/3.1.html">PIL - Arithmetic Operators</a></i>
     */
    DIV,
    /**
     * The literal Lua modulo operator ({@code %}).
     *
     * <p>
     * The remainder of the left operand divided by the right operand.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/3.1.html">PIL - Arithmetic Operators</a></i>
     */
    MOD,
    /**
     * The literal Lua exponentiation operator ({@code ^}).
     *
     * <p>
     * Multiplies the left operand by the right operand number of times.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/3.1.html">PIL - Arithmetic Operators</a></i>
     */
    POW,
    /**
     * The literal Lua length operator ({@code #}).
     *
     * <p>
     * The length of a string or table.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/manual/5.2/manual.html#3.4.6">Lua 5.1 - The Length Operator</a></i>
     */
    LEN,

    /**
     * The literal string {@code ==}.
     *
     * <p>
     * Used to check equality between two values.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/3.2.html">PIL - Relational Operators</a></i>
     */
    EQ,
    /**
     * The literal string {@code ~=}.
     *
     * <p>
     * Used to check inequality between two values.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/3.2.html">PIL - Relational Operators</a></i>
     */
    NEQ,
    /**
     * The literal string {@code <=}.
     *
     * <p>
     * Used to check if the value on the left is equal-to or less-than the value on the right.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/3.2.html">PIL - Relational Operators</a></i>
     */
    LTE,
    /**
     * The literal string {@code >=}.
     *
     * <p>
     * Used to check if the value on the left is equal-to or greater-than the value on the right.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/3.2.html">PIL - Relational Operators</a></i>
     */
    GTE,
    /**
     * The literal character {@code <}.
     *
     * <p>
     * Used to check if the value on the left is less-than the value on the right.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/3.2.html">PIL - Relational Operators</a></i>
     */
    LT,
    /**
     * The literal character {@code >}.
     *
     * <p>
     * Used to check if the value on the left is greater-than the value on the right.
     *
     * <p>
     * <i>Reference: <a href="https://www.lua.org/pil/3.2.html">PIL - Relational Operators</a></i>
     */
    GT,

    /**
     * The literal character {@code ,}.
     *
     * <p>
     * Used to seperate function parameters/arguments, or values in table literals.
     */
    COMMA,
    /**
     * The literal character {@code .}.
     *
     * <p>
     * Used to access variables within the namespaces of others <i>(i.e. {@code foo.x}</i>.
     */
    DOT,
    /**
     * The literal character {@code (}.
     *
     * <p>
     * Used to denote the beginning of a parameter/argument sequence for a function or a sub-expression.
     */
    LPAREN,
    /**
     * The literal character {@code )}.
     *
     * <p>
     * Used to denote the termination of a parameter/argument sequence for a function or a sub-expression.
     */
    RPAREN,
    /**
     * The literal character {@code {}.
     *
     * <p>
     * Used to denote the beginning of table construction.
     */
    LBRACE,
    /**
     * The literal character {@code {}.
     *
     * <p>
     * Used to denote the termination of table construction.
     */
    RBRACE,
    /**
     * The literal character {@code [}.
     *
     * <p>
     * Used to denote the beginning of table indexing, or mutiline strings.
     */
    LBRACK,
    /**
     * The literal character {@code [}.
     *
     * <p>
     * Used to denote the termination of table indexing, or mutiline strings.
     */
    RBRACK;

    /**
     * Queries the supplied {@code kind} to check if it is an <b>identifier</b>.
     *
     * @param  kind the {@code LuaTokenKind} to query
     * @return {@code true} if {@code kind} is an identifier, {@code false} otherwise
     */
    public static boolean isIdentifier(LuaTokenKind kind) {
        return kind == LuaTokenKind.NAME;
    }

    /**
     * Queries the supplied {@code kind} to check if it is a <b>literal</b>.
     *
     * <p>
     * A <b>literal</b> is either a {@code STRING} or {@code NUMBER}.
     *
     * @param  kind the {@code LuaTokenKind} to query
     * @return {@code true} if {@code kind} is a literal, {@code false} otherwise
     */
    public static boolean isLiteral(LuaTokenKind kind) {
        return kind == LuaTokenKind.STRING
          ||   kind == LuaTokenKind.NUMBER;
    }

    /**
     * Queries the supplied {@code kind} to check if it is an <b>assignment</b>.
     *
     * @param  kind the {@code LuaTokenKind} to query
     * @return {@code true} if {@code kind} is an assignment, {@code false} otherwise
     */
    public static boolean isAssignment(LuaTokenKind kind) {
        return kind == LuaTokenKind.ASSIGN;
    }

    /**
     * Queries the supplied {@code kind} to check if it is a <b>keyword</b>.
     *
     * @param  kind the {@code LuaTokenKind} to query
     * @return {@code true} if {@code kind} is a keyword, {@code false} otherwise
     */
    public static boolean isKeyword(LuaTokenKind kind) {
        return kind == LuaTokenKind.AND
          ||   kind == LuaTokenKind.BREAK
          ||   kind == LuaTokenKind.DO
          ||   kind == LuaTokenKind.ELSE
          ||   kind == LuaTokenKind.ELSEIF
          ||   kind == LuaTokenKind.END
          ||   kind == LuaTokenKind.FALSE
          ||   kind == LuaTokenKind.FOR
          ||   kind == LuaTokenKind.FUNCTION
          ||   kind == LuaTokenKind.IF
          ||   kind == LuaTokenKind.IN
          ||   kind == LuaTokenKind.LOCAL
          ||   kind == LuaTokenKind.NIL
          ||   kind == LuaTokenKind.NOT
          ||   kind == LuaTokenKind.OR
          ||   kind == LuaTokenKind.REPEAT
          ||   kind == LuaTokenKind.RETURN
          ||   kind == LuaTokenKind.THEN
          ||   kind == LuaTokenKind.TRUE
          ||   kind == LuaTokenKind.UNTIL
          ||   kind == LuaTokenKind.WHILE;
    }

    /**
     * Queries the supplied {@code kind} to check if it is an <b>arithmetic operator</b>.
     *
     * <p>
     * An <b>arithmetic operator</b> is any mathematical operator, as follows:
     * <ul>
     * <li>{@code ADD}/{@code SUB}
     * <li>{@code MUL}/{@code DIV}/{@code MOD}
     * <li>{@code POW}
     * <li>{@code LEN}
     * </ul>
     *
     * @param  kind the {@code LuaTokenKind} to query
     * @return {@code true} if {@code kind} is an arithmetic operator, {@code false} otherwise
     */
    public static boolean isArithmetic(LuaTokenKind kind) {
        return kind == LuaTokenKind.ADD
          ||   kind == LuaTokenKind.SUB
          ||   kind == LuaTokenKind.MUL
          ||   kind == LuaTokenKind.DIV
          ||   kind == LuaTokenKind.MOD
          ||   kind == LuaTokenKind.POW
          ||   kind == LuaTokenKind.LEN;
    }

    /**
     * Queries the supplied {@code kind} to check if it is a <b>relational operator</b>.
     *
     * <p>
     * A <b>relational operator</b> is any token that compares two other tokens, as follows:
     * <ul>
     * <li>{@code EQ}/{@code NEQ}
     * <li>{@code LTE}/{@code GTE}
     * <li>{@code LT}/{@code GT}
     * </ul>
     *
     * @param  kind the {@code LuaTokenKind} to query
     * @return {@code true} if {@code kind} is a relational operator, {@code false} otherwise
     */
    public static boolean isRelational(LuaTokenKind kind) {
        return kind == LuaTokenKind.EQ
          ||   kind == LuaTokenKind.NEQ
          ||   kind == LuaTokenKind.LTE
          ||   kind == LuaTokenKind.GTE
          ||   kind == LuaTokenKind.LT
          ||   kind == LuaTokenKind.GT;
    }

    /**
     * Queries the supplied {@code kind} to check if it is a <b>delimiter</b>.
     *
     * <p>
     * A <b>delimiter</b> is any token that groups data, as follows:
     * <ul>
     * <li>{@code COMMA}/{@code DOT}
     * <li>{@code LPAREN}/{@code RPAREN}
     * <li>{@code LBRACE}/{@code RBRACE}
     * <li>{@code RBRACK}/{@code RBRACK}
     * </ul>
     *
     * @param  kind the {@code LuaTokenKind} to query
     * @return {@code true} if {@code kind} is a delimiter, {@code false} otherwise
     */
    public static boolean isDelimiter(LuaTokenKind kind) {
        return kind == LuaTokenKind.COMMA
          ||   kind == LuaTokenKind.DOT
          ||   kind == LuaTokenKind.LPAREN
          ||   kind == LuaTokenKind.RPAREN
          ||   kind == LuaTokenKind.LBRACE
          ||   kind == LuaTokenKind.RBRACE
          ||   kind == LuaTokenKind.LBRACK
          ||   kind == LuaTokenKind.RBRACK;
    }
}
