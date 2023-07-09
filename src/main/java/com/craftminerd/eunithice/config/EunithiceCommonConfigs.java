package com.craftminerd.eunithice.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class EunithiceCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> NEUDONITE_ORE_VEINS_PER_CHUNK;
    public static final ForgeConfigSpec.ConfigValue<Integer> NEUDONITE_ORE_VEIN_SIZE;


    static {
        BUILDER.push("Ore Generation");

        NEUDONITE_ORE_VEINS_PER_CHUNK = BUILDER.comment("How many Neudonite Ore veins spawn per chunk").worldRestart()
                        .define("Neudonite Veins Per Chunk", 8);
        NEUDONITE_ORE_VEIN_SIZE = BUILDER.comment("How many Neudonite Ore blocks spawn per vein").worldRestart()
                        .defineInRange("Neudonite Vein Size", 7, 2, 30);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
