#> luam:stdlib/absf(x)
#  @param x a number

#> function absf(x)
#>     if (x < 0) then
#>         x = negf(x)
#>     end
#>     return x
#> end

execute store result score $R(0) luam.vm.reg run data get storage luam:vm ".stack"[-1]

# if (x < 0) then x = negf(x) end
execute if score $R(0) luam.vm.reg matches ..-1 run function luam:stdlib/negf
