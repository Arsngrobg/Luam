package io.github.arsngrobg.luam.compiler

data class PackFormat(
    val major: UInt,
    val minor: UInt = 0u
)

// https://minecraft.wiki/w/Pack_format
enum class LuamOutputVersion(val packFormat: PackFormat) {
    MC1_15   (PackFormat(5u)),
    MC1_15_1 (PackFormat(5u)),
    MC1_15_2 (PackFormat(5u)),
    MC1_16   (PackFormat(5u)),
    MC1_16_1 (PackFormat(5u)),

    MC1_16_2 (PackFormat(6u)),
    MC1_16_3 (PackFormat(6u)),
    MC1_16_4 (PackFormat(6u)),
    MC1_16_5 (PackFormat(6u)),

    MC1_16_6 (PackFormat(7u)),
    MC1_17   (PackFormat(7u)),
    MC1_17_1 (PackFormat(7u)),

    MC1_18   (PackFormat(8u)),
    MC1_18_1 (PackFormat(8u)),

    MC1_18_2 (PackFormat(9u)),

    MC1_19   (PackFormat(10u)),
    MC1_19_1 (PackFormat(10u)),
    MC1_19_2 (PackFormat(10u)),
    MC1_19_3 (PackFormat(10u)),

    MC1_19_4 (PackFormat(12u)),

    MC1_20   (PackFormat(15u)),
    MC1_20_1 (PackFormat(15u)),

    MC1_20_2 (PackFormat(18u)),

    MC1_20_3 (PackFormat(26u)),
    MC1_20_4 (PackFormat(26u)),

    MC1_20_5 (PackFormat(41u)),
    MC1_20_6 (PackFormat(41u)),

    MC1_21   (PackFormat(48u)),
    MC1_21_1 (PackFormat(48u)),

    MC1_21_2 (PackFormat(57u)),
    MC1_21_3 (PackFormat(57u)),

    MC1_21_4 (PackFormat(61u)),

    MC1_21_5 (PackFormat(71u)),

    MC1_21_6 (PackFormat(80u)),

    MC1_21_7 (PackFormat(81u)),
    MC1_21_8 (PackFormat(81u)),

    MC1_21_9 (PackFormat(88u, 0u)),
    MC1_21_10(PackFormat(88u, 0u)),

    MC1_21_11(PackFormat(94u, 1u)),

    MC26_1   (PackFormat(101u, 1u)),
    MC26_1_1 (PackFormat(101u, 1u)),
    MC26_1_2 (PackFormat(101u, 1u)),

    MC26_2   (PackFormat(107u, 1u)),

    MC26_3   (PackFormat(121u, 0u));

    /** The actual version string seen on Minecraft */
    val actualName: String =
        name.substring(2)
            .replace('_', '.')
}
