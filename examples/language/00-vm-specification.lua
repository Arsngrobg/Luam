-- technical details about the luam virtual machine

-- the virtual machine consists of:
--  REGISTERS (`/scoreboard`):
--   |- $max = Memory Accumulator (primary arithmetic register)
--   |- $mgx = Memory General (general purpose register)
--   |- $mcx = Memory Counter Register (loops)
--   |- $msp = Memory Stack Pointer
--  PREFIXES:
--   |- '$' -> when `-debug` flag is given to luam
--   |- '#' -> otherwise (hides the register values)

--  STACK (`/data ... storage`):
--   |- NBT array that grows and shrinks in size
--   |- the $msp controls where the "top" is
--   |- is the call stack for function calls
--      \- stack frames are reused whenever TCO is possible
--   |- also contains:
--      \- numbers
--      \- object IDs

--  MEMORY (`/data ... storage`):
--   |- luam garbage collector manages this region
--   |- each object gets its own integer ID
--      \- maximum number of objects in a single moment is 4,294,967,295
--      \- your computer will run out of memory before we reach that limit
--   |- two primary NBT entries:
--      \- object pool        -> lua tables, strings
--      \- relationship table -> variable identifiers that index into the object pool

-- TESTING (.mcfunction):
--  REGISTERS:
--   /scoreboard objectives add luamvm_reg dummy
--   /scoreboard objectives setdisplay sidebar luamvm_reg
--   /scoreboard players set $max luamvm_reg 0
--   /scoreboard players set $mgx luamvm_reg 0
--   /scoreboard players set $mcx luamvm_reg 0
--   /scoreboard players set $msp luamvm_reg 0

--  STACK, MEMORY, OBJECT POOLING:
--   /data modify storage luamvm:datapack mem.obj set value {"stack":[I;],"mem":{"obj":{},"rel":{}}}

--  PERSISTENT MEMORY:
--   /data merge storage <namespace>:data {}
