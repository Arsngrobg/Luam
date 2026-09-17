-- printing 'Hello, World!' to the chat

-- no external data will be required for the script
-- the compler will strip away any zero-initialization logic (unless `-debug` flag provided)
-- all that will be emitted by the compiler is the intrinsic
-- .mcfunction:
--  /tellraw @a {"text":"Hello, World!"}

print('Hello, World!')
