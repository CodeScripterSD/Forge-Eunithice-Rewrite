package com.craftminerd.eunithice.block;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.block.custom.*;
import com.craftminerd.eunithice.block.custom.stations.AsphaltInfuser;
import com.craftminerd.eunithice.block.custom.stations.Extractor;
import com.craftminerd.eunithice.item.EunithiceItems;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class EunithiceBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Eunithice.MODID);

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    private static <T extends Block> RegistryObject<T> registerBlockWithTooltip(String name, Supplier<T> block, CreativeModeTab tab, Component tooltip) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItemTooltip(name, toReturn, tab, tooltip);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItemTooltip(String name, RegistryObject<T> block, CreativeModeTab tab, Component tooltip) {
        return EunithiceItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)) {
            @Override
            public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
                pTooltip.add(tooltip);
                super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
            }
        });
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<T> registerBlockFuel(String name, Supplier<T> block, CreativeModeTab tab, int burnTime) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockFuelItem(name, toReturn, tab, burnTime);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return EunithiceItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }

    private static <T extends Block>RegistryObject<Item> registerBlockFuelItem(String name, RegistryObject<T> block, CreativeModeTab tab, int burnTime) {
        return EunithiceItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)) {
            @Override
            public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return burnTime;
            }
        });
    }

    public static final RegistryObject<Block> ASPHALT = registerBlock("asphalt",
            () -> new Block(BlockBehaviour.Properties
                    .of(Material.STONE, MaterialColor.COLOR_BLACK)
                    .strength(1f, 6f)
                    .speedFactor(1.1f)),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<Block> RAW_NEUDONITE_BLOCK = registerBlock("raw_neudonite_block",
            () -> new Block(BlockBehaviour.Properties
                    .of(Material.STONE, MaterialColor.COLOR_BLUE)
                    .strength(5f, 7f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<Block> NEUDONITE_BLOCK = registerBlock("neudonite_block",
            () -> new Block(BlockBehaviour.Properties
                    .of(Material.METAL, MaterialColor.COLOR_BLUE)
                    .strength(5f, 7f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType. METAL)),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<Block> NEUDONITE_ORE = registerBlock("neudonite_ore",
            () -> new Block(BlockBehaviour.Properties
                    .of(Material.STONE, MaterialColor.STONE)
                    .strength(3f, 3.5f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<Block> DEEPSLATE_NEUDONITE_ORE = registerBlock("deepslate_neudonite_ore",
            () -> new Block(BlockBehaviour.Properties
                    .copy(NEUDONITE_ORE.get())
                    .color(MaterialColor.DEEPSLATE)
                    .strength(4.5f, 3.5f)
                    .sound(SoundType.DEEPSLATE)),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<SpeedInfusedAsphalt> SPEED_INFUSED_ASPHALT = registerBlock("speed_infused_asphalt",
            () -> new SpeedInfusedAsphalt(BlockBehaviour.Properties
                    .of(Material.STONE, MaterialColor.COLOR_BLUE)
                    .strength(3f, 7f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.BASALT),
                    1.9D),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<SpeedInfusedAsphalt> SUPER_SPEED_INFUSED_ASPHALT = registerBlock("super_speed_infused_asphalt",
            () -> new SpeedInfusedAsphalt(BlockBehaviour.Properties
                    .of(Material.STONE, MaterialColor.COLOR_BLUE)
                    .strength(3f, 7f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.BASALT),
                    10D),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<BounceInfusedAsphalt> BOUNCE_INFUSED_ASPHALT = registerBlock("bounce_infused_asphalt",
            () -> new BounceInfusedAsphalt(BlockBehaviour.Properties
                    .of(Material.STONE, MaterialColor.COLOR_LIGHT_GREEN)
                    .strength(3f, 7f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.BASALT)),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<HoneyInfusedAsphalt> HONEY_INFUSED_ASPHALT = registerBlock("honey_infused_asphalt",
            () -> new HoneyInfusedAsphalt(BlockBehaviour.Properties
                    .of(Material.STONE, MaterialColor.COLOR_ORANGE)
                    .strength(3f, 7f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.BASALT)),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<Block> PLATED_IRON_BLOCK = registerBlock("iron_plate_block",
            () -> new Block(BlockBehaviour.Properties
                    .of(Material.METAL, MaterialColor.RAW_IRON)
                    .strength(5f, 8f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<Block> COMPRESSED_PLATED_IRON_BLOCK = registerBlock("compressed_iron_plate_block",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties
                    .of(Material.METAL, MaterialColor.RAW_IRON)
                    .strength(5f, 15f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<Block> HEAVY_COMPRESSED_PLATED_IRON_BLOCK = registerBlock("heavy_compressed_iron_plate_block",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties
                    .of(Material.METAL, MaterialColor.RAW_IRON)
                    .strength(5f, 25f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<DoorBlock> IRON_PLATE_DOOR = registerBlock("iron_plate_door",
            () -> new DoorBlock(BlockBehaviour.Properties
                    .of(Material.METAL, MaterialColor.RAW_IRON)
                    .strength(5.0F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.METAL)),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<TrapDoorBlock> IRON_PLATE_TRAPDOOR = registerBlock("iron_plate_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties
                    .of(Material.METAL, MaterialColor.RAW_IRON)
                    .strength(5.0F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.METAL)),
            Eunithice.EUNITHICE_ITEMS_TAB);


    public static final RegistryObject<Block> PLATED_GOLD_BLOCK = registerBlock("gold_plate_block",
            () -> new Block(BlockBehaviour.Properties
                    .of(Material.METAL, MaterialColor.GOLD)
                    .strength(5f, 8f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<Block> COMPRESSED_PLATED_GOLD_BLOCK = registerBlock("compressed_gold_plate_block",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties
                    .of(Material.METAL, MaterialColor.GOLD)
                    .strength(6f, 15f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<Block> HEAVY_COMPRESSED_PLATED_GOLD_BLOCK = registerBlock("heavy_compressed_gold_plate_block",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties
                    .of(Material.METAL, MaterialColor.GOLD)
                    .strength(6f, 25f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)),
            Eunithice.EUNITHICE_ITEMS_TAB);


    public static final RegistryObject<Block> DIAMOND_PLATE_BLOCK = registerBlock("diamond_plate_block",
            () -> new Block(BlockBehaviour.Properties
                    .of(Material.METAL, MaterialColor.DIAMOND)
                    .strength(7f, 35f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<Block> COMPRESSED_DIAMOND_PLATE_BLOCK = registerBlock("compressed_diamond_plate_block",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties
                    .of(Material.METAL, MaterialColor.DIAMOND)
                    .strength(7f,50f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<Block> HEAVY_COMPRESSED__DIAMOND_PLATE_BLOCK = registerBlock("heavy_compressed_diamond_plate_block",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties
                    .of(Material.METAL, MaterialColor.DIAMOND)
                    .strength(7f,65f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<LeuriteCrop> LEURITE = BLOCKS.register("leurite_crop",
            () -> new LeuriteCrop(BlockBehaviour.Properties
                    .copy(net.minecraft.world.level.block.Blocks.WHEAT)));

    public static final RegistryObject<AsphaltInfuser> ASPHALT_INFUSER = registerBlock("asphalt_infuser",
            () -> new AsphaltInfuser(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<Extractor> EXTRACTOR = registerBlockWithTooltip("extractor",
            () -> new Extractor(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()),
            Eunithice.EUNITHICE_ITEMS_TAB, new TranslatableComponent("tooltip.eunithice.extractor_hover"));

    public static final RegistryObject<DisplayCase> DISPLAY_CASE = registerBlock("display_case",
            () -> new DisplayCase(BlockBehaviour.Properties
                    .of(Material.STONE, MaterialColor.COLOR_BLACK)
                    .strength(1f, 6f)
                    .speedFactor(1.1f)
                    .noOcclusion()),
            Eunithice.EUNITHICE_ITEMS_TAB);


    public static final RegistryObject<DoorBlock> STONE_DOOR = registerBlock("stone_door",
            () -> new DoorBlock(BlockBehaviour.Properties
                    .of(Material.STONE, MaterialColor.STONE)
                    .strength(1.5F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
                    .noOcclusion()),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<TrapDoorBlock> STONE_TRAPDOOR = registerBlock("stone_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties
                    .of(Material.STONE, MaterialColor.STONE)
                    .strength(1.5F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
                    .noOcclusion()),
            Eunithice.EUNITHICE_ITEMS_TAB);
}
