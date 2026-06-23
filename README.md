# Luam */luːm/*
An **ahead-of-time (AOT)** compiler for Minecraft datapacks, using Lua 5.1 as the language frontend.

## Writing Datapacks by Hand Can Be Annoying
- You are restricted to Minecraft's command syntax
- No direct support for iteration
- No direct support for structured data
- Control flow is supported, but only through callback functions
- A single "block" or unit of code must be within its own `.mcfunction` file
- For advanced projects, they become amalgamations of `.mcfunction`s and JSON files

## Why Lua?
- Lua is *easy to parse*, it has a very disambiguous syntax.
- Lua is known for being embedded in host-applications (e.g. NeoVim, Garry's Mod, Roblox)
- Lua tables align with how data is represented in Minecraft
- I like Lua

## Similar Projects
- [Beet](https://mcbeet.dev) - a data-driven Python *"development kit"* for creating datapacks
- [Sandstone](https://sandstone.dev) - a Typescript datapack library
- [ObjD](https://objd.stevertus.com) - a framework for developing datapacks in the Dart programming language

## Getting Started
See below on how to get started with Luam.

### 1. How to Install
- Go to the releases **[TODO]** page
- Download the compiled JAR file
- Place the JAR in a location (optional)
- Add the path of the JAR into your PATH (optional)
- Test the compiler using:
```bash
~> luam --version
```

### 2. Compile from Source
Compiling from source is just as simple as installing.
- Download the latest stable release of the source tree
- At the root of the repository, invoke this command
```bash
~> gradlew compileJava
```
- If successful you should have an executable JAR file
- Test the compiler using:
```bash
~> luam --version
```

### Your First Script
After sucessfully compiling/installing Luam on your device, check the `examples` directory for different Lua scripts to test the features of the compiler.
For example, here is the `hello-world.lua` snippet:

```lua
-- literally just prints 'Hello, World!'
print('Hello, World!')
```

Invoke the compiler (assuming cwd is `examples`):
```bash
~> luam hello-world.lua -o hello-world
```

The `-o` flag signals to the compiler that you want to specify a name for the datapack.
It is optional, and will default to a random name if left unspecified.

- Place the datapack in your Minecraft world data folder
- Load up the respective Minecraft world
- On load, you should see a message pop-up in the chat history:
```
[server] Hello, World!
```

### Adding an icon
Every proffessional datapack needs to have a thumbnail.

Going back to compiling the `hello-world.lua` script:
```bash
~> luam hello-world.lua -i icon.png
```

The `-i` flag signals to the compiler that you want to specify a URL to an image file that will show up in the datapacks list on Minecraft.

### Targetting Specific Minecraft Versions
You may want to compile for the latest version of mimecraft; or you may want to compile a different target version.
By default, Luam defaults to the latest compilable version known to it.
However, you are allowed to specify the maximum and minimum [pack format](https://minecraft.wiki/w/Pack_format) for the datapack:

```bash
~> luam hello-world.lua --format=48,81
```

the secondary format value (`81`) is an optional value and declares the maximum [pack format](https://minecraft.wiki/w/Pack_format).
For brevity, the syntax of the option is as follows:
```
--format=<min>(,<max>)?
```

## Compiler Optimizations
To retain 100% parity with Lua 5.1, Luam optimizes tail calls as the Lua manual states it is a [feature of the language](https://www.lua.org/pil/6.3.html).

But, by default, Luam makes no effort to optimize.
The compiler always assumes an optimization level of `0`, unless specified otherwise.
The `-O<level>` flag specifies the optimization level, with the maximum being `3`.
This is heavily inspired by the design of the GNU C Compiler (GCC).

A list if each optimization level, and what each enables, are listed below.

### Level 0 (`-O0`) **(Default)**
- Tail Call Optimization (TCO)

### Level 1 (-O1)
- Constant Folding             (`-fconst-fold`)
- Dead Branch Elimination      (`-fprune-dead`)
- Algebraic Simplifications    (`-ffast-math`)
- Function Inlining            (`-fsimple-inline`)

### Level 2 (-O2)
- Constant Propagation         (`-fconst-prop`)
- Dead Code Elimination        (`-fremove-dead-code`)
- Static Loop Optimizations    (`-floop-opt`)
- Aggressive Function Inlining (`-faggressive-inline`)

*Lua functions in a tail call are not inlined!*

### References
- https://www.lua.org/about.html
- https://www.lua.org/manual/5.1/manual.html
- https://www.reddit.com/r/feedthebeast/comments/1iq8u1h/datapacks_are_criminally_underused_in_modpacks
- https://notes.highlysuspect.agency/datapacks-bad.html
- https://mcbeet.dev
- https://sandstone.dev
- https://objd.stevertus.com
