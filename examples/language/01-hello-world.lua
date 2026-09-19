-- printing 'Hello, World!' to the chat

-- no external data will be required for the script
-- the compler will strip away any zero-initialization logic (unless `-debug` flag provided)
-- the compiler will emit the line
-- .mcfunction:
--  /tellraw @a {"text":"Hello, World!"}

print('Hello, World!')
