package com.craftminerd.eunithice.fluid;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.block.EunithiceBlocks;
import com.craftminerd.eunithice.item.EunithiceItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EunithiceFluids {
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation WATER_OVERLAY_RL = new ResourceLocation("block/water_overlay");

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Eunithice.MODID);

    public static final RegistryObject<FlowingFluid> EXTRACTION_FLUID
            = FLUIDS.register("extraction_fluid", () -> new ForgeFlowingFluid.Source(EunithiceFluids.EXTRACTION_PROPERTIES));

    public static final RegistryObject<FlowingFluid> EXTRACTION_FLOWING
            = FLUIDS.register("extraction_flowing", () -> new ForgeFlowingFluid.Flowing(EunithiceFluids.EXTRACTION_PROPERTIES));

    public static final ForgeFlowingFluid.Properties EXTRACTION_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> EXTRACTION_FLUID.get(), () -> EXTRACTION_FLOWING.get(), FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
            .density(15).luminosity(2).viscosity(5).sound(SoundEvents.HONEY_DRINK).overlay(WATER_OVERLAY_RL)
            .color(0xbfff0f53)).slopeFindDistance(3).levelDecreasePerBlock(1)
            .block(() -> EunithiceFluids.EXTRACTION_BLOCK.get()).bucket(() -> EunithiceItems.EXTRACTION_BUCKET.get());

    public static final RegistryObject<LiquidBlock> EXTRACTION_BLOCK = EunithiceBlocks.BLOCKS.register("extraction",
            () -> new LiquidBlock(() -> EunithiceFluids.EXTRACTION_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER)
                    .noCollission().strength(100f).noDrops()));

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
