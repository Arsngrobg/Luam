-- player data

local me = Player.get("Arsngrobg")
me:give("minecraft:diamond", 64)

Entity.spawn({type:"minecraft:zombie", })

local zombie = Entity.spawn("minecraft:zombie", me)
me:teleportTo(zombie)
me:damage(10)
me:kill()
