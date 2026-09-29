execute if score $R(0) luam.vm.reg matches ..-1 run function luam:stdlib/.scopes/math/abs/flip_bit
execute store result score $R(1) luam.vm.reg run data get storage luam:vm ".stack"[-6]
scoreboard players operation $R(0) luam.vm.reg -= $R(1) luam.vm.reg
scoreboard players operation $R(0) luam.vm.reg /= $R(1) luam.vm.reg
scoreboard players set $R(1) luam.vm.reg 2
scoreboard players operation $R(0) luam.vm.reg /= $R(1) luam.vm.reg
execute store result storage luam:vm ".stack"[-2] int 1 run scoreboard players get $R(0) luam.vm.reg
