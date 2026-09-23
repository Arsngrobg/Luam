-- technical details about the luam virtual machine

-- the virtual machine consists of:
--  REGISTERS (`/scoreboard`):
--   |- $R(0) - accumulating register
--   |- $R(1) - operand register
--   |- $R(2) - general purpose register 1
--   |- $R(3) - general purpose register 2
--   |- $R(4) - general purpose register 3
--   |- $R(5) - general purpose register 4
--   |- $R(6) - general purpose register 5
--   |- $R(7) - general purpose register 6
--   |- $R(8) - counter (e.g. FOR loops, WHILE with counter) (non-volatile)

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
--       |- contains objects that will never be garbage collected
--   |- contains pre-defined initialized globals (".data")
--       |- NBT Object array
--   |- contains defined uninitialized globals (".bss")
--       |- NBT Object array

--  PERSISTENT (`/data ... storage <namespace>:data ...`):
--   |- is a generic container
--   |- contains persistent data to use between sessions
--   |- the region that the `io` library interacts with
