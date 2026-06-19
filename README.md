# Luam */luːm/*
An **ahead-of-time (AOT)** compiler for Minecraft datapacks, using Lua *(5.1)* as the language frontend.

## Why Does This Project Exist?
It can be incredibly mentally cumbersome to write datapack logic.
You are restricted to Minecraft's command syntax, making it feel like the equivalent of writing raw assembly.
Due to that, there *currently* are not any high-level programming constructs like control flow or importantly complex data management like structures.
On top of that, every line of code must be wrapped within an `.mcfunction` file.
Projects get difficult to maintain as project size grows.
It has a very sparse build process
Lastly, datapacks treat **data** as **code**.

## Why Lua?
Lua is *easy to parse*, it is very disambiguous with its syntax.
Originally, Javascript was considered, but the language is too ambigous (I did not want to test the hundreds of little quirks of the language in order to have complete parity with Javascript).
Lua is very narrow in the scope of its applications, it is primarily used in scripting in software such as NeoVim or Garry's Mod for example.

I just really like Lua as a language.

*This project does not use the actual Lua source code, rather I have written the parser from scratch using Java as I want the external dependencies required to use this project at **zero** - in short, if you can run Minecraft, you can use the compiler.*

## Similar Projects
- Beet - a data-driven Python *"development kit"* for creating datapacks
- Sandstone - a Typescript datapack library
- ObjD - a framework for developing datapacks in the Dart programming language

## Getting Started
Download the jar or compile the source tree directly.

Write some Lua code:
```lua
print('Hello, World!')
```

Invoke the compiler as follows:
```bash
~ > luam script.lua
```

This will produce the equivalent `.mcfunction` file:
```mcfunction
say Hello, World!
```

Obviously, the compiler will generate the respective `pack.mcmeta` and required JSON data in order for the datapack to be executable.

To get further information on how to use Luam:
```bash
~ > luam --help
```

## Useful Options
Some compiler options that are useful when compiling source code:
- `-o <name>`: meaning *output name*, sets the name of the datapack after compilation
- `-img=<url>`: the `pack.png` image bundled with the compiled datapack
- `-format=<min>(,<max>)?`: the minimum pack format the datapack should support (and optional maximum)

## Compiler Optimizations
By default, Luam does not make any effort to optimize.
The only optimization it makes is tail call optimization (TCO) as it is a feature of the language.
You configure the optimization level of the compiler using the `-O[LEVEL]` flag, where `-O0` is the default.
This is heavily inspired by the design of the GNU C Compiler (GCC).
A list of each optimization is listed below:

### Level 0 (-O0) (Default)
- Tail Call Optimization (TCO)

*The Lua specification defines TCO as a language feature, so it cannot be disabled.*

### Level 1 (-O1)
- Constant Folding             (`-fconst-fold`)
- Dead Branch Elimination      (`-fremove-dead-subtree`)
- Algebraic Simplifications    (`-fsimplify-math`)
- Function Inlining            (`-fsimple-inline`)

### Level 2 (-O2)
- Constant Propagation         (`-fconst-prop`)
- Dead Code Elimination        (`-fremove-dead-code`)
- Static Loop Optimizations    (`-floop-opt`)
- Aggressive Function Inlining (`-faggressive-inline`)

*Lua functions in a tail call are not inlined!*

### Example
Take this piece of Lua code:
```lua
for n=0,5 do
    print(n)
end
```

At optimization level 0, it will extract this loop into an `.mcfunction` file that executes the `say` command 5 times by repeatedly calling itself.

At optimization level 1 (or using the `-floop-opt` flag), it will produce equivalent `.mcfunction` code:
```lua
print(0)
print(1)
print(2)
print(3)
print(4)
print(5)
```

### References
- https://www.lua.org/about.html
- https://www.lua.org/manual/5.1/manual.html
- https://www.reddit.com/r/feedthebeast/comments/1iq8u1h/datapacks_are_criminally_underused_in_modpacks
- https://notes.highlysuspect.agency/datapacks-bad.html
- https://mcbeet.dev
- https://sandstone.dev
- https://objd.stevertus.com
