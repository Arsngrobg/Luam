#> luam:vm/init()

scoreboard objectives add luam.vm.reg dummy "Luam Virtual Machine Registers"
scoreboard players set $R(0) luam.vm.reg 0 # accumulator
scoreboard players set $R(1) luam.vm.reg 0 # operand
scoreboard players set $R(2) luam.vm.reg 0 # counter

data modify storage luam:vm ".stack" set value [I;]
data modify storage luam:vm ".heap" set value {}
