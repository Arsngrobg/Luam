-- technical details about the luam virtual machine

-- the virtual machine consists of:
--  REGISTERS (`/scoreboard`):
--   |- $max (2 arithmetic storage 32-bit registers)
--      |- $mah = Memory Accumulator High
--      |- $mal = Memory Accumulator Low
--   |- $mgx (2 general purpose 32-bit registers)
--      |- $mgh = Memory General Low
--      |- $mgl = Memory General High
--   |- $mcx = Memory Counter Register (loops)
--   |- $msp = Memory Stack Pointer
--  PREFIXES:
--   |- '$' -> when `-debug` flag is given to luam
--   |- '#' -> otherwise (hides the register values)

--  STACK (`/data ... storage`):
--   |- NBT array that grows and shrinks in size
--   |- the $msp controls where the "top" is

--  MEMORY/ (`/data ... storage`):
--   |- NBT key/value data
--   |- interned strings
--   |- (escaped) variables

-- TESTING (.mcfunction):
--  REGISTERS:
--   /scoreboard objectives luamvm_reg dummy
--   /scoreboard setdisplay sidebar luamreg
--   /scoreboard players set $mah luamvm_reg 0
--   /scoreboard players set $mal luamvm_reg 0
--   /scoreboard players set $mgh luamvm_reg 0
--   /scoreboard players set $mgl luamvm_reg 0
--   /scoreboard players set $mcx luamvm_reg 0
--   /scoreboard players set $msp luamvm_reg 0

--  STACK, MEMORY:
--   /data merge storage luamvm:<namespace> {"stack":[],"mem":{"pool":[],"heap":{}}}

--  PERSISTENT MEMORY:
--   /data merge storage <namespace>:data {}
