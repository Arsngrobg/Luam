#> luam:vm/init()

scoreboard objectives add luam.vm.reg dummy "Luam Virtual Machine Registers"
scoreboard players set $R(0) luam.vm.reg 0 # accumulator (caller-saved)
scoreboard players set $R(1) luam.vm.reg 0 # operand (caller-saved)
scoreboard players set $R(2) luam.vm.reg 0 # counter (caller-saved)
scoreboard players set $R(3) luam.vm.reg 0 # stack pointer (globally managed & 1.20.1+)
scoreboard players set $R(4) luam.vm.reg 0 # general purpose 1 (callee-saved)
scoreboard players set $R(5) luam.vm.reg 0 # general purpose 2 (callee-saved)
scoreboard players set $R(6) luam.vm.reg 0 # general purpose 3 (callee-saved)
scoreboard players set $R(7) luam.vm.reg 0 # general purpose 4 (callee-saved)

data modify storage luam:vm ".stack" set value [I;]
data modify storage luam:vm ".heap" set value {}
