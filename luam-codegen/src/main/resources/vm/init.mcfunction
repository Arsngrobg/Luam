#> luam:vm/init[0]
#  Intitialises the Luam Virtual Machine Runtime Environment

scoreboard objectives add luam.vm.reg dummy "Luam Virtual Machine Registers"
scoreboard players set $R(0) luam.vm.reg 0 # accumulator
scoreboard players set $R(1) luam.vm.reg 0 # operand
scoreboard players set $R(2) luam.vm.reg 0 # counter

scoreboard players set $F(0) luam.vm.reg 0 # sign permutation register
scoreboard players set $F(1) luam.vm.reg 0 # mantissa register 1
scoreboard players set $F(2) luam.vm.reg 0 # exponent register 1
scoreboard players set $F(3) luam.vm.reg 0 # mantissa register 2
scoreboard players set $F(4) luam.vm.reg 0 # exponent register 2

data modify storage luam:vm ".stack" set value []
data modify storage luam:vm ".heap" set value {}
