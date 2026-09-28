#> luam:stdlib/addf(a, b)
#  @param a the first operand
#  @param b the second operand

#> function addf(a, b)
#>     local pos_a = absf(a)
#>     local pos_b = absf(b)
#>     local sign  = ...
#>     
#> end

# IEEE-754:
#  Exponent Bias = 01111111 (127)
#  Exponent = bin(True Exponent) + 01111111
#  [1][   8    ][          23           ]
#   0  00000000  00000000000000000000000

# a
execute store result score $R(0) luam.vm.reg run data get storage luam:vm ".stack"[-2]

# abs(a)
data modify storage luam:vm ".stack" append value 0
execute store result storage luam:vm ".stack"[-1] int 1 run scoreboard players get $R(0) luam.vm.reg
function luam:stdlib/absf

# b
execute store result score $R(0) luam.vm.reg run data get storage luam:vm ".stack"[-2]

# abs(b)
data modify storage luam:vm ".stack" append value 0
execute store result storage luam:vm ".stack"[-1] int 1 run scoreboard players get $R(0) luam.vm.reg
function luam:stdlib/absf

# a, b
execute store result score $R(0) luam.vm.reg run data get storage luam:vm ".stack"[-4]
execute store result score $R(1) luam.vm.reg run data get storage luam:vm ".stack"[-3]

# sign bit (Sa ^ Sb) = (Sa + Sb) % 2
execute store result score $R(0) luam.vm.reg if score $R(0) luam.vm.reg matches ..-1
execute store result score $R(1) luam.vm.reg if score $R(1) luam.vm.reg matches ..-1
scoreboard players operation $R(0) luam.vm.reg += $R(1) luam.vm.reg
scoreboard players set $R(1) luam.vm.reg 2 # 0b11
scoreboard players operation $R(0) luam.vm.reg %= $R(1) luam.vm.reg

# push sign bit
data modify storage luam:vm ".stack" append value 0
execute store result storage luam:vm ".stack"[-1] int 1 run scoreboard players get $R(0) luam.vm.reg

data remove storage lauam:vm ".stack"[-1] # pop [abs(b)]
data remove storage lauam:vm ".stack"[-1] # pop [abs(b)]
data remove storage lauam:vm ".stack"[-1] # pop [Sb]
data remove storage lauam:vm ".stack"[-1] # pop [Sa]
