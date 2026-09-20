-- numbers
--  |- are primitives
--  |- not managed by the luam garbage collector (LuamGC)
--  |- are managed via stack memory directly

-- numbers in luam are represented as IEEE-754 (32-bit) floating-point decimals
-- IEEE-754:
--  Exponent Bias = 01111111 (127)
--  Exponent = True Value + 127
--  [1][   8    ][          23           ]
--   0  00000000  00000000000000000000000

-- IEEE-754:
--  [1][   8    ][          23           ]
--   0  10000001  00000000000000000000000
-- .mcfunction:
--  /scoreboard players set $max luamvm_reg 1082130432
4

-- IEEE-754:
--  [1][   8    ][          23           ]
--   0  01111101  10011001100110011001101
-- .mcfunction:
--  /scoreboard players set $max luamvm_reg 1053609165
0.4

-- IEEE-754:
--  [1][   8    ][          23           ]
--   0  01110111  00101011011111111110000
-- .mcfunction:
--  /scoreboard players set $max luamvm_reg 999669744
4.57e-3

-- IEEE-754:
--  [1][   8    ][          23           ]
--   0  10100101  00010111011001011001001
-- .mcfunction:
--  /scoreboard players set $max luamvm_reg 1384886985
0.3e12

-- IEEE-754:
--  [1][   8    ][          23           ]
--   0  11000011  10110001101011100100111
-- .mcfunction:
--  /scoreboard players set $max luamvm_reg 1641600807
5e+20

-- IEEE-754:
--  [1][   8    ][          23           ]
--   0  11111110  11111111111111111111111
-- .mcfunction:
--  /scoreboard players set $max luamvm_reg 2139095039
3.4028235e+38

-- IEEE-754:
--  [1][   8    ][          23           ]
--   0  00000000  00000000000000000000001
-- .mcfunction:
--  /scoreboard players set $max luamvm_reg 1
1.4e-45

-- IEEE-754:
--  [1][   8    ][          23           ]
--   0  11111111  00000000000000000000000
-- .mcfunction:
--  /scoreboard players set $max luamvm_reg 2139095040
1/0

-- IEEE-754:
--  [1][   8    ][          23           ]
--   1  11111111  00000000000000000000000
-- .mcfunction:
--  /scoreboard players set $max luamvm_reg -8388608
-(1/0)

-- IEEE-754:
--  [1][   8    ][          23           ]
--   0  11111111  10000000000000000000000
-- .mcfunction:
--  /scoreboard players set $max luamvm_reg 2143289344
0/0
