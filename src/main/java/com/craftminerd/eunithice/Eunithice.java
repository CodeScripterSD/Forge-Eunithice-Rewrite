package com.craftminerd.eunithice;

import com.craftminerd.eunithice.block.EunithiceBlocks;
import com.craftminerd.eunithice.block.entity.EunithiceBlockEntities;
import com.craftminerd.eunithice.effect.EunithiceEffects;
import com.craftminerd.eunithice.enchantments.EunithiceEnchantments;
import com.craftminerd.eunithice.item.EunithiceItems;
import com.craftminerd.eunithice.recipe.EunithiceRecipes;
import com.craftminerd.eunithice.screen.EunithiceMenuTypes;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Eunithice.MODID)
public class Eunithice
{
    public static final String MODID = "eunithice";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final CreativeModeTab EUNITHICE_ITEMS_TAB = new CreativeModeTab(MODID) { // itemGroup.eunithice
        @Override
        public ItemStack makeIcon() {
            return EunithiceItems.BURN_CORE.get().getDefaultInstance();
        }

        @Override
        public boolean hasSearchBar() {
            return true;
        }
    }.setBackgroundImage(new ResourceLocation("minecraft", "textures/gui/container/creative_inventory/tab_item_search.png"));

    public Eunithice()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register the setup method for modloading
        eventBus.addListener(this::setup);

        EunithiceItems.ITEMS.register(eventBus);
        EunithiceBlocks.register(eventBus);
        EunithiceBlockEntities.register(eventBus);
        EunithiceMenuTypes.register(eventBus);
        EunithiceRecipes.register(eventBus);
        EunithiceEnchantments.register(eventBus);
        EunithiceEffects.register(eventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        DispenserBlock.registerBehavior(EunithiceItems.BURN_CORE.get(), new OptionalDispenseItemBehavior()
        {
            /**
             * Dispense the specified stack, play the dispense sound and spawn particles.
             */
            protected ItemStack execute(BlockSource p_123412_, ItemStack p_123413_) {
                Level level = p_123412_.getLevel();
                this.setSuccess(true);
                Direction direction = p_123412_.getBlockState().getValue(DispenserBlock.FACING);
                BlockPos blockpos = p_123412_.getPos().relative(direction);
                BlockState blockstate = level.getBlockState(blockpos);
                if (BaseFireBlock.canBePlacedAt(level, blockpos, direction)) {
                    level.setBlockAndUpdate(blockpos, BaseFireBlock.getState(level, blockpos));
                    level.gameEvent((Entity)null, GameEvent.BLOCK_PLACE, blockpos);
                } else if (!CampfireBlock.canLight(blockstate) && !CandleBlock.canLight(blockstate) && !CandleCakeBlock.canLight(blockstate)) {
                    if (blockstate.isFlammable(level, blockpos, p_123412_.getBlockState().getValue(DispenserBlock.FACING).getOpposite())) {
                        blockstate.onCaughtFire(level, blockpos, p_123412_.getBlockState().getValue(DispenserBlock.FACING).getOpposite(), null);
                        if (blockstate.getBlock() instanceof TntBlock)
                            level.removeBlock(blockpos, false);
                    } else {
                        this.setSuccess(false);
                    }
                } else {
                    level.setBlockAndUpdate(blockpos, blockstate.setValue(BlockStateProperties.LIT, Boolean.valueOf(true)));
                    level.gameEvent((Entity)null, GameEvent.BLOCK_CHANGE, blockpos);
                }

                if (this.isSuccess() && p_123413_.hurt(1, level.random, (ServerPlayer)null)) {
                    p_123413_.setCount(0);
                }

                return p_123413_;
            }
        });
        // some preinit code
//        LOGGER.info("HELLO FROM PREINIT");
//        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}
