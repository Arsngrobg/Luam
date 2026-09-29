#> luam:stdlib/math/neg(x)
#  @param x a number
#  @returns the same number with its sign bit toggled

# IEEE-754:
#  Exponent Bias = 01111111 (127)
#  Exponent = bin(True Exponent) + 01111111
#  [1][   8    ][          23           ]
#   0  00000000  00000000000000000000000

execute store result score $R(0) luam.vm.reg run data get storage luam:vm ".stack"[-1]

function luam:stdlib/.scopes/math/neg/flip_bit

execute store result storage luam:vm ".stack"[-1] int 1 run scoreboard players get $R(0) luam.vm.reg
