# Luam */luːm/*
An **ahead-of-time (AOT)** Lua compiler for Minecraft datapacks, using Lua 5.1 as the language frontend.

> [!NOTE]
> Current development supports Minecraft versions starting from **1.15**, as it introduced the global `storage` NBT data container.
> Work **may** be done in the future to be backwards compatible with versions from **1.13** onwards.
> Currently, there are no plans to support Minecraft snapshots.

## Installing Luam
See below on how to install Luam and build your first datapack:

 - Download the latest **[release](https://github.com/Arsngrobg/Luam/releases/latest)**
 - Unpack the distribution archive
 - Place the `luam` folder in a directory of your choosing *(e.g. programs)*
 - Add `luam` to your `PATH` variable
 - Check using this command:
```bash
~ luam
```

## Getting to Know The Compiler
Luam provides a help text when you invoke the Luam driver:
```bash
~ luam help
```

To get the version of Luam Currently installed on your system:
```bash
~ luam version
```

## Building a project
To build a project in Luam you will need to invoke Luam with the `build` subcommand:
```bash
~ luam build
```

This compiles all `.lua` source files within the current working directory.
You can specify the directory by supplying a path argument:
```bash
~ luam build examples/language/
```

You can specify a single file, using the `hello-world.lua` examples script:
```bash
~ luam build examples/language/ hello-world.lua -file
```

## Naming your Compiled Datapack
By default, Luam won't name your compiled datapack.

To change this use the `-out:...` flag:
```bash
~ luam build -out:my-datapack
```

If you want to include spaces in your name surround the value with double-quotes.

For example:
```bash
~ luam build -out:"my datapack"
```

## Giving your Compiled Datapack an Icon
Luam will search for a `pack.png` file in your directory.
If there is not one or you would like to reference an image from elsewhere on your machine, you can use the `-icon:<URL>` flag:
```bash
~ luam build -icon:~/images/image.png
```

Much like the `-out:...` flag, you can wrap the path in double-quotes:
```bash
~ luam build -icon:"~/images/datapack icon.png"
```

## Targeting a Specific Minecraft Version
By default, Luam will compile for the latest Minecraft version it knows of.

You can specify a version:
```bash
~ luam build -version:1.20.1
```

Pack formats have a one to many relationship with Minecraft versions.
This means that if you support 1.20.1 *(pack format: `15`)* you also support 1.20.

## Compiler Optimizations
Luam, by default, optimizes at the highest level (`3`).
You can tweak this level with the `-oplvl=...` flag:
```bash
~ luam build -oplvl=0
```

Because Luam maintains compatability with the Lua specification, it always ensures that Tail Call Opimization is baked into compilation.
At optimization level zero, this is the only optimization made by the compiler.

See below for a comprehensive list of optimizations:
 - ### at `-oplvl=0`:
   - Tail Call Optimization (TCO)
 - ### at `-oplvl=1`:
   - **TBC**
 - ### at `-oplvl=2`:
   - **TBC**
 - ### at `-oplvl=3`:
   - **TBC**

*Optimization levels inherit all the optimizations from the one before it.*

## Modules (TBC)
Luam provides quite a number of ready-to-use modules to include in your scripts.
Some are in global scope (`_G`), some must be imported using the `require` function:
 - ### in global (`_G`) scope:
   - math
 - ### must be imported (`require(...)`):
   - World
   - Player
   - Entity

## The Runtime
Luam embeds a small runtime per datapack.
This allows for compatibility with how the Lua runtime operates.

It includes:
 - a Memory Allocator
 - a Garbage Collector (GC)
 - a Runtime Library

### The Runtime Library
To be able to natively support the `number` type (`float`), Luam ships floating-point arithmetic functions into compiled datapacks.

## Appendix
### Similar Projects
 - [Beet](https://mcbeet.dev) - a data-driven [Python](https://www.python.org) *"development kit"* for creating datapacks
 - [Sandstone](https://sandstone.dev) - a [Typescript](https://www.typescriptlang.org) datapack library
 - [ObjD](https://objd.stevertus.com) - a framework for developing datapacks in the [Dart](https://dart.dev) programming language

### Links
 - https://mcbeet.dev
 - https://sandstone.dev
 - https://objd.stevertus.com
 - https://www.python.org
 - https://www.typescriptlang.org
 - https://dart.dev

### Bibliography
 - [Lua Manual](https://www.lua.org/manual/5.1)
