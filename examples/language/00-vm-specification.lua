-- technical details about the luam virtual machine

-- the virtual machine consists of:
--  REGISTERS (`/scoreboard`):
--   |- $R(0) - accumulating register
--   |- $R(1) - operand register
--   |- $R(2) - counter (e.g. FOR loops, WHILE with counter)

--  STACK (`/data ... storage luam:vm ".stack" ...`):
--   |- NBT Integer array
--   |- the call stack for true function calls
--       |- can be reused whenever TCO is possible
--   |- contains numbers or heap object addresses
--       |- the compiler knows when to use this formatting

--  MEMORY (`/data ... storage luam:vm ".heap" ...`):
--   |- NBT ID to Object mapping
--   |- garbage collector handles heap objects
--   |- each malloc creates a new entry in heap space if it does not alredy exist

--  SEGMENTS (`/data ... storage <namespace>:sections ...`):
--   |- contains read-only data (".rodata")
--       |- NBT Object array
--   |- contains pre-defined initialized globals (".data")
--       |- NBT Object array
--   |- contains defined uninitialized globals (".bss")
--       |- NBT Object array

--  PERSISTENT (`/data ... storage <namespace>:data ...`):
--   |- is a generic container
--   |- contains persistent data to use between sessions
--   |- is the destination when using the `io` library
