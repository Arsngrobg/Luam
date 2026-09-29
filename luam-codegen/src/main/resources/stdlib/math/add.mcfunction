#> luam:stdlib/math/add(A, B)
#  @param A the first operand
#  @param B the second operand
#  @returns the sum of A and B

# // Check if the original signs are the same or different
# Result_Sign = Sign_Big
# Result_Mantissa = Big_Mantissa - Small_Mantissa;
# if (Sign_Big == Sign_Small) {
#     Result_Mantissa = Big_Mantissa + Small_Mantissa;
# }
# else {
#     // Because Big_Mantissa >= Small_Mantissa, this will NEVER underflow or be negative
#     Result_Mantissa = Big_Mantissa - Small_Mantissa;
# }

# IEEE-754:
#  Exponent Bias = 01111111 (127)
#  Exponent = bin(True Exponent) + 01111111
#  [1][   8    ][          23           ]
#   0  00000000  00000000000000000000000

# layout:
# [a , b , Ma, Mb, Sa, Sb]
# [-6, -5, -4, -3, -2, -1]

# allocate:
data modify storage luam:vm ".stack" append value 0
data modify storage luam:vm ".stack" append value 0
data modify storage luam:vm ".stack" append value 0
data modify storage luam:vm ".stack" append value 0

# Ma = |a| % 2^23
execute store result score $R(0) luam.vm.reg run data get luam:vm ".stack"[-6]
execute if score $R(0) luam.vm.reg matches ..-1 run function luam:stdlib/.scopes/math/abs/flip_bit
scoreboard players set $R(1) luam.vm.reg 8388608
scoreboard players operation $R(0) luam.vm.reg %= $R(1) luam.vm.reg
execute store result storage luam:vm ".stack"[-2] run scoreboard players get $R(0) luam.vm.reg

# Mb = |b| % 2^23
execute store result score $R(0) luam.vm.reg run data get luam:vm ".stack"[-5]
execute if score $R(0) luam.vm.reg matches ..-1 run function luam:stdlib/.scopes/math/abs/flip_bit
scoreboard players set $R(1) luam.vm.reg 8388608
scoreboard players operation $R(0) luam.vm.reg %= $R(1) luam.vm.reg
execute store result storage luam:vm ".stack"[-1] run scoreboard players get $R(0) luam.vm.reg

# Ma = max(Ma, Mb)
# Mb = min(Ma, Mb)
execute store result score $R(0) luam.vm.reg run data get luam:vm ".stack"[-4]
execute store result score $R(1) luam.vm.reg run data get luam:vm ".stack"[-3]
execute if score $R(1) luam.vm.reg > $R(0) luam.vm.reg store result storage luam:vm .stack[-2] int 1 run scoreboard players get $R(1) luam.vm.reg
execute if score $R(0) luam.vm.reg < $R(1) luam.vm.reg store result storage luam:vm .stack[-1] int 1 run scoreboard players get $R(0) luam.vm.reg

# Sa = (|a|-a)/2a [while a != 0 -> Sa = 0]
execute store result score $R(0) luam.vm.reg run data get luam:vm ".stack"[-2]
execute unless score $R(0) luam.vm.reg matches 0 run function luam:.scopes/stdlib/add/sign_a

# Sb = (|b|-b)/2b [while b != 0 -> Sb = 0]
execute store result score $R(0) luam.vm.reg run data get luam:vm ".stack"[-1]
execute unless score $R(0) luam.vm.reg matches 0 run function luam:.scopes/stdlib/add/sign_b

# return:
execute store result storage luam:vm ".stack"[-6] run scoreboard players get $R(0) luam.vm.reg

# deallocate:
data remove storage luam:vm ".stack"[-1]
data remove storage luam:vm ".stack"[-1]
data remove storage luam:vm ".stack"[-1]
data remove storage luam:vm ".stack"[-1]
data remove storage luam:vm ".stack"[-1]
