-- numbers

-- numbers in luam are represented as IEEE-754 (64-bit) floating-point decimals
-- both the $max and $mgx registers have two high and low subdivisions to cater to this
-- the low register maps to the low 32-bits
-- the high register maps to the high 32-bits

-- IEEE-754:
--  Exponent Bias = 01111111111 (1023)
--  Exponent = True Value + 1023
--  [                        52                          ][     11    ][1]
--  [                     Mantissa                       ][  Exponent ][S]
--   0000000000000000000000000000000000000000000000000000   00000000000 0
--   HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHLLLLLLLLLLLLLLLLLLLL   LLLLLLLLLLL L

-- IEEE-754:
--  Exponent Bias = 01111111111 (1023)
--  Exponent = 2 + 1023 = 1025
--  [                        52                          ][     11    ][1]
--  [                     Mantissa                       ][  Exponent ][S]
--   0000000000000000000000000000000000000000000000000000  10000000001  0
--   HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHLLLLLLLLLLLLLLLLLLLL  LLLLLLLLLLL  L
-- .mcfunction:
--  /scoreboard players set $mal luamreg 2050
--  /scoreboard players set $mah luamreg 0
4

-- IEEE-754:
--  Exponent Bias = 01111111111 (1023)
--  Exponent = -2 + 1023 = 1021
--  [                        52                          ][     11    ][1]
--  [                     Mantissa                       ][  Exponent ][S]
--   1001100110011001100110011001100110011001100110011010  01111111101 0
--   HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHLLLLLLLLLLLLLLLLLLLL  LLLLLLLLLLL L
-- .mcfunction:
--  /scoreboard players set $mal luamreg -1717983238
--  /scoreboard players set $mah luamreg -1717986919
0.4

-- .mcfunction:
--  /scoreboard players set $mal luamreg 145685291
--  /scoreboard players set $mah luamreg 1064482814
4.57e-3

-- .mcfunction:
--  /scoreboard players set $mal luamreg 771751936
--  /scoreboard players set $mah luamreg 1112634969
0.3e12

-- .mcfunction:
--  /scoreboard players set $mal luamreg 455527490
--  /scoreboard players set $mah luamreg 1144726355
5e+20
