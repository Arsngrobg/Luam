-- player data

local me = Player.get("Arsngrobg")
me:give("minecraft:diamond", 64)

print(me.x, me.y, me.z)
me:teleportTo(0, 0, 0)
me:damage(10)
me:kill()
