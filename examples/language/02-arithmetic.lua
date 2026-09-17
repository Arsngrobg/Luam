-- arithmetic

-- the compiler will always prefer to use 'add' and 'remove' over the 'operation' subcommand
-- to reduce output size and the overhead of the 'edx' register
-- though, the compiler would only emit this code if the `-debug` flag was given
-- in addition, the compiler will swap between the '#' & '$' prefix depending on whether `-debug` was given

-- .mcfunction:
--  /scoreboard objectives add reg dummy
--  /scoreboard objectives setdisplay sidebar reg (for debugging)
--  /scoreboard players add $eax reg 0
--  /scoreboard players add $edx reg 0

-- .mcfunction:
--  /scoreboard players set $eax reg 5
--  /scoreboard players set $edx reg 2
--  /scoreboard players operation $eax reg *= $edx reg
--  /scoreboard players add $eax reg 10
10 + 5 * 2

-- .mcfunction:
--  /scoreboard players set $eax reg 9
--  /scoreboard players remove $eax reg 3
--  /scoreboard players set $edx reg 2
--  /scoreboard players operation $eax reg /= $edx reg
(9 - 3) / 2

-- .mcfunction:
--  /scoreboard players set $eax reg -2
--  /scoreboard players add $eax reg 3
(-2 + 3)

-- .mcfunction:
--  /scoreboard players set $eax reg 10
--  /scoreboard players set $edx reg 3
--  /scoreboard players operation $eax reg %= $edx reg
10 % 3

-- .mcfunction:
--  TODO
2^8

-- .mcfunction:
--  TODO
4.5

-- registers:
--  #eax - accumulator
--  #edx - data (used alongside #eax)
--  #ecs - counter (e.g. FOR loops)
--  #esp - stack pointer
