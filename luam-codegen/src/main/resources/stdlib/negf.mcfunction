#> luam:stdlib/negf(x)
#  @param x a number

#> function negf(x)
#>     return -x
#> end

execute store result score $R(0) luam.vm.reg run data get storage luam:vm ".stack"[-1]

scoreboard players set $R(1) luam.vm.reg -2147483648
scoreboard players operation $R(0) luam.vm.reg += $R(1) luam.vm.reg

execute store result storage luam:vm ".stack"[-1] int 1 run scoreboard players get $R(0) luam.vm.reg
