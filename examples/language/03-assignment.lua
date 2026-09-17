-- assignment

-- .mcfunction:
--  /scoreboard objectives add reg dummy
--  /scoreboard players add $eax reg 0
--  /scoreboard players add $edx reg 0

-- .mcfunction:
--  /scoreboard players set $eax reg 9
--  /scoreboard players remove $eax reg 3
--  /scoreboard players set $edx reg 2
--  /scoreboard players operation $eax reg /= $edx reg
local x = (9 - 3) / 2
