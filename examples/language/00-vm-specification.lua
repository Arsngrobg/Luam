-- technical details about the luam virtual machine

-- the virtual machine consists of:
--  REGISTERS (`/scoreboard luam.vm.reg`):
--   |- $R(0) - accumulating register
--   |- $R(1) - operand register
--   |- $R(2) - counter (iteration)
--   |- $R(3) - general purpose register 1
--   |- $R(4) - general purpose register 2
--   |- $R(5) - general purpose register 3
--   |- $R(6) - general purpose register 4

--  STACK (`/data ... storage luam:vm ".stack" ...`):
--   |- NBT Integer array
--   |- the call stack for true function calls
--       |- will reuse stack frames whenever TCO is possible
--   |- contains numbers or heap object addresses
--       |- the compiler knows when to use either representations

--  MEMORY (`/data ... storage luam:vm ".heap" ...`):
--   |- NBT ID to Object mapping
--   |- garbage collector handles heap objects
--   |- each malloc creates a new entry in heap space if it does not alredy exist

--  SECTIONS (`/data ... storage <namespace>:sections ...`):
--   |- contains read-only data (".rodata")
--       |- NBT Object array
--       |- these objects will never be garbage collected

--  PERSISTENT (`/data ... storage <namespace>:data ...`):
--   |- is a generic container
--   |- contains persistent data to use between sessions
