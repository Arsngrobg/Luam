package io.github.arsngrobg.luam.compiler

/**
 * Lua is a dynamically typed langage.
 * There are no type definitions in the language; each value carries its own type.
 */
sealed interface LuaType

/**
 * **Nil** is a type with a single value, **nil**, whose main property is to be different from any other value.
 * A global variable has a **nil** value by default, and assignment to **nil** deletes it.
 */
object LuaNil : LuaType

/**
 * The boolean type has two values, **false** and **true**.
 * However, they do not hold a monopoly of condition values:
 * In Lua, any value can represent a condition.
 * Conditionals consider **false** and **nil** as false aand everything else as true.
 */
@JvmInline
value class LuaBoolean(val b: Boolean) : LuaType

/**
 * The number type represents real (single-precision floating-point).
 * Lua has not integer type, as it does not need it.
 */
@JvmInline
value class LuaNumber(val x: Float) : LuaType

/**
 * Strings have the usual meaning: a sequence of characters.
 * Lua is eight-bit clean and so strings may contain characters with any numeric value.
 * Strings in Lua are immutable values.
 * You cannot change a character inside a string.
 */
@JvmInline
value class LuaString(val s: String) : LuaType

/**
 * The table type implements associative arrays.
 * It can be indexed not just with numbers, but also with strings or any other value of the langage, except **nil**.
 * Moreover, tables have no fixed size; you can add as many elements as you want to a table dynamically.
 * Tables are the main data structuring mechanism in Lua.
 */
@JvmInline
value class LuaTable(val t: Map<LuaType, LuaType>) : LuaType

/**
 * Functions are first-class values in Lua.
 * That means functions can be stored in variables, passed as arguments to other functions, and returned as results.
 */
@JvmInline
value class LuaFunction(val i: UInt) : LuaType

/**
 * Arbitrary Luam module data to be stored in Lua variables.
 * Userdata is treated as read-only, compile-time tables that represent complex internal systems.
 */
@JvmInline
value class LuaUserData(val i: UInt) : LuaType

/**
 * A *coroutine* is similar to a thread.
 * A collaborative thread that executes alongside regular execution.
 */
@JvmInline
value class LuaThread(val i: UInt) : LuaType
