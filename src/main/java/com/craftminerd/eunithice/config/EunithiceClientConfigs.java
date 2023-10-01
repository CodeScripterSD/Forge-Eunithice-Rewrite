package com.craftminerd.eunithice.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class EunithiceClientConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

//    public static final ForgeConfigSpec.ConfigValue<Boolean> EXAMPLE;


    static {
        BUILDER.push("General");

//        EXAMPLE = BUILDER.comment("Example","Default: false")
//                .define("example", false);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
