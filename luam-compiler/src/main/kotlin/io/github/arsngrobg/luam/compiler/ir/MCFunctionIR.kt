package io.github.arsngrobg.luam.compiler.ir

typealias LuamReg = UByte
typealias NBTPath = String

sealed interface MCFunctionIR

data class LI   (val dst: LuamReg, val src: Int    ) : MCFunctionIR // reg = CONSTANT
data class MOVE (val dst: LuamReg, val src: LuamReg) : MCFunctionIR // reg = src

data class LOAD (val dst: LuamReg, val src: NBTPath) : MCFunctionIR // reg = nbt
data class STORE(val dst: NBTPath, val src: LuamReg) : MCFunctionIR // nbt = nbt
