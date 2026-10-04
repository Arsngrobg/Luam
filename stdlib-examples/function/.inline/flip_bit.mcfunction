#> luam:.inline/flip_bit
#  flips the bits of $R(0)

scoreboard players set $R(1) luam.vm.reg -2147483648
scoreboard players operation $R(0) luam.vm.reg += $R(1) luam.vm.reg
