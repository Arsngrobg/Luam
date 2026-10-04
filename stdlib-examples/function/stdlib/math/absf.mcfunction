#> luam:stdlib/math/absf(x)
#  Returns the absolute value of `x`
#  @param x a number
#  @returns the magnitude of x

# IEEE-754:
#  Exponent Bias = 01111111 (127)
#  Exponent = bin(True Exponent) + 01111111
#  [1][   8    ][          23           ]
#   0  00000000  00000000000000000000000

execute store result score $R(0) luam.vm.reg run data get storage luam:vm ".stack"[-1]

execute if score $R(0) luam.vm.reg matches ..-1 run function luam:.inline/flip_bit

execute store result storage luam:vm ".stack"[-1] int 1 run scoreboard players get $R(0) luam.vm.reg
