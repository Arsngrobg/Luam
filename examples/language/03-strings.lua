-- strings
--  |- are managed by the luam garbage collector (LuamGC)
--  |- are referenced by ID in the stack when they are to be used
--  |- the length operator ('#') can be applied to strings
--     \- this returns the mumber of bytes the string

-- .mcfunction:
--  /data merge storage luamvm:<namespace> {"mem":{"obj":{0:"Hello, World!"},{"rel":{"_":0}}}}
_="Hello, World!"
