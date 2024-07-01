package com.craftminerd.eunithice.block;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.block.blocks.*;
//import com.craftminerd.eunithice.block.blocks.stations.AsphaltInfuser;
//import com.craftminerd.eunithice.block.blocks.stations.Extractor;
import com.craftminerd.eunithice.block.blocks.stations.AsphaltInfuser;
import com.craftminerd.eunithice.block.blocks.stations.Extractor;
import com.craftminerd.eunithice.item.EunithiceItems;
import com.craftminerd.eunithice.world.feature.tree.DarkwoodTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
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

    public static final RegistryObject<Block> STRIPPED_DARKWOOD_LOG = registerBlock("stripped_darkwood_log",
            () -> new EunithiceFlammableRotatedPillarBlock(BlockBehaviour.Properties
                    .copy(Blocks.STRIPPED_OAK_LOG),
                    null),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<Block> DARKWOOD_LOG = registerBlock("darkwood_log",
            () -> new EunithiceFlammableRotatedPillarBlock(BlockBehaviour.Properties
                    .copy(Blocks.OAK_LOG),
                    STRIPPED_DARKWOOD_LOG),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<Block> STRIPPED_DARKWOOD_WOOD = registerBlock("stripped_darkwood_wood",
            () -> new EunithiceFlammableRotatedPillarBlock(BlockBehaviour.Properties
                    .copy(Blocks.STRIPPED_OAK_WOOD),
                    null),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<Block> DARKWOOD_WOOD = registerBlock("darkwood_wood",
            () -> new EunithiceFlammableRotatedPillarBlock(BlockBehaviour.Properties
                    .copy(Blocks.OAK_WOOD),
                    STRIPPED_DARKWOOD_WOOD),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<Block> DARKWOOD_LEAVES = registerBlock("darkwood_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties
                    .copy(Blocks.OAK_LEAVES)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            }, Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<Block> DARKWOOD_SAPLING = registerBlock("darkwood_sapling",
            () -> new SaplingBlock(new DarkwoodTreeGrower(), BlockBehaviour.Properties
                    .copy(Blocks.OAK_SAPLING))
                , Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<Block> DARKWOOD_PLANKS = registerBlock("darkwood_planks",
            () -> new Block(BlockBehaviour.Properties
                    .copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            }, Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<Block> DARKWOOD_STAIRS = registerBlock("darkwood_stairs",
            () -> new StairBlock(() -> EunithiceBlocks.DARKWOOD_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties
                    .copy(Blocks.OAK_STAIRS))
            , Eunithice.EUNITHICE_ITEMS_TAB);
    public static final RegistryObject<Block> DARKWOOD_SLAB = registerBlock("darkwood_slab",
            () -> new SlabBlock(BlockBehaviour.Properties
                    .copy(Blocks.OAK_SLAB))
            , Eunithice.EUNITHICE_ITEMS_TAB);
    public static final RegistryObject<Block> DARKWOOD_FENCE = registerBlock("darkwood_fence",
            () -> new FenceBlock(BlockBehaviour.Properties
                    .copy(Blocks.OAK_FENCE))
            , Eunithice.EUNITHICE_ITEMS_TAB);
    public static final RegistryObject<Block> DARKWOOD_FENCE_GATE = registerBlock("darkwood_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties
                    .copy(Blocks.OAK_FENCE_GATE))
            , Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<Block> DARKWOOD_BUTTON = registerBlock("darkwood_button",
            () -> new WoodButtonBlock(BlockBehaviour.Properties
                    .copy(Blocks.OAK_BUTTON))
            , Eunithice.EUNITHICE_ITEMS_TAB);
    public static final RegistryObject<Block> DARKWOOD_PRESSURE_PLATE = registerBlock("darkwood_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties
                    .copy(Blocks.OAK_PRESSURE_PLATE))
            , Eunithice.EUNITHICE_ITEMS_TAB);
    public static final RegistryObject<Block> DARKWOOD_DOOR = registerBlock("darkwood_door",
            () -> new DoorBlock(BlockBehaviour.Properties
                    .copy(Blocks.OAK_DOOR))
            , Eunithice.EUNITHICE_ITEMS_TAB);
    public static final RegistryObject<Block> DARKWOOD_TRAPDOOR = registerBlock("darkwood_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties
                    .copy(Blocks.OAK_TRAPDOOR))
            , Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<Block> ASPHALT = registerBlock("asphalt",
            () -> new Block(BlockBehaviour.Properties
                    .of(Material.STONE, MaterialColor.COLOR_BLACK)
                    .strength(1f, 6f)
                    .requiresCorrectToolForDrops()
                    .speedFactor(1.4f)),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<Block> DIRT_STAIRS = registerBlock("dirt_stairs",
            () -> new StairBlock(Blocks.DIRT::defaultBlockState, BlockBehaviour.Properties
                    .copy(Blocks.DIRT))
            , Eunithice.EUNITHICE_ITEMS_TAB);
    public static final RegistryObject<Block> GRASS_STAIRS = registerBlock("grass_stairs",
            () -> new StairBlock(Blocks.DIRT::defaultBlockState, BlockBehaviour.Properties
                    .of(Material.GRASS).randomTicks().strength(0.6F).sound(SoundType.GRASS))
            , Eunithice.EUNITHICE_ITEMS_TAB);
    public static final RegistryObject<Block> DIRT_SLAB = registerBlock("dirt_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.DIRT))
            , Eunithice.EUNITHICE_ITEMS_TAB);
    public static final RegistryObject<Block> GRASS_SLAB = registerBlock("grass_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK))
            , Eunithice.EUNITHICE_ITEMS_TAB);

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
                    .strength(3f, 7f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<Block> LYMINE_BLOCK = registerBlock("lymine_block",
            () -> new Block(BlockBehaviour.Properties
                    .of(Material.METAL, MaterialColor.COLOR_PURPLE)
                    .strength(10f, 30f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<Block> MYELITE_BLOCK = registerBlock("myelite_block",
            () -> new Block(BlockBehaviour.Properties
                    .of(Material.METAL, MaterialColor.COLOR_RED)
                    .strength(15f, 70f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)),
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
                    .requiresCorrectToolForDrops()
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

//    public static final RegistryObject<Block> PLATED_IRON_BLOCK = registerBlock("iron_plate_block",
//            () -> new Block(BlockBehaviour.Properties
//                    .of(Material.METAL, MaterialColor.RAW_IRON)
//                    .strength(5f, 8f)
//                    .requiresCorrectToolForDrops()
//                    .sound(SoundType.METAL)),
//            Eunithice.EUNITHICE_ITEMS_TAB);
//
//    public static final RegistryObject<Block> COMPRESSED_PLATED_IRON_BLOCK = registerBlock("compressed_iron_plate_block",
//            () -> new RotatedPillarBlock(BlockBehaviour.Properties
//                    .of(Material.METAL, MaterialColor.RAW_IRON)
//                    .strength(5f, 15f)
//                    .requiresCorrectToolForDrops()
//                    .sound(SoundType.METAL)),
//            Eunithice.EUNITHICE_ITEMS_TAB);
//
//    public static final RegistryObject<Block> HEAVY_COMPRESSED_PLATED_IRON_BLOCK = registerBlock("heavy_compressed_iron_plate_block",
//            () -> new RotatedPillarBlock(BlockBehaviour.Properties
//                    .of(Material.METAL, MaterialColor.RAW_IRON)
//                    .strength(5f, 25f)
//                    .requiresCorrectToolForDrops()
//                    .sound(SoundType.METAL)),
//            Eunithice.EUNITHICE_ITEMS_TAB);

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


//    public static final RegistryObject<Block> PLATED_GOLD_BLOCK = registerBlock("gold_plate_block",
//            () -> new Block(BlockBehaviour.Properties
//                    .of(Material.METAL, MaterialColor.GOLD)
//                    .strength(5f, 8f)
//                    .requiresCorrectToolForDrops()
//                    .sound(SoundType.METAL)),
//            Eunithice.EUNITHICE_ITEMS_TAB);
//
//    public static final RegistryObject<Block> COMPRESSED_PLATED_GOLD_BLOCK = registerBlock("compressed_gold_plate_block",
//            () -> new RotatedPillarBlock(BlockBehaviour.Properties
//                    .of(Material.METAL, MaterialColor.GOLD)
//                    .strength(6f, 15f)
//                    .requiresCorrectToolForDrops()
//                    .sound(SoundType.METAL)),
//            Eunithice.EUNITHICE_ITEMS_TAB);
//
//    public static final RegistryObject<Block> HEAVY_COMPRESSED_PLATED_GOLD_BLOCK = registerBlock("heavy_compressed_gold_plate_block",
//            () -> new RotatedPillarBlock(BlockBehaviour.Properties
//                    .of(Material.METAL, MaterialColor.GOLD)
//                    .strength(6f, 25f)
//                    .requiresCorrectToolForDrops()
//                    .sound(SoundType.METAL)),
//            Eunithice.EUNITHICE_ITEMS_TAB);
//
//
//    public static final RegistryObject<Block> DIAMOND_PLATE_BLOCK = registerBlock("diamond_plate_block",
//            () -> new Block(BlockBehaviour.Properties
//                    .of(Material.METAL, MaterialColor.DIAMOND)
//                    .strength(7f, 35f)
//                    .requiresCorrectToolForDrops()
//                    .sound(SoundType.METAL)),
//            Eunithice.EUNITHICE_ITEMS_TAB);
//
//    public static final RegistryObject<Block> COMPRESSED_DIAMOND_PLATE_BLOCK = registerBlock("compressed_diamond_plate_block",
//            () -> new RotatedPillarBlock(BlockBehaviour.Properties
//                    .of(Material.METAL, MaterialColor.DIAMOND)
//                    .strength(7f,50f)
//                    .requiresCorrectToolForDrops()
//                    .sound(SoundType.METAL)),
//            Eunithice.EUNITHICE_ITEMS_TAB);
//
//    public static final RegistryObject<Block> HEAVY_COMPRESSED__DIAMOND_PLATE_BLOCK = registerBlock("heavy_compressed_diamond_plate_block",
//            () -> new RotatedPillarBlock(BlockBehaviour.Properties
//                    .of(Material.METAL, MaterialColor.DIAMOND)
//                    .strength(7f,65f)
//                    .requiresCorrectToolForDrops()
//                    .sound(SoundType.METAL)),
//            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<LeuriteCrop> LEURITE_CROP = BLOCKS.register("leurite_crop",
            () -> new LeuriteCrop(BlockBehaviour.Properties
                    .copy(net.minecraft.world.level.block.Blocks.WHEAT)));

    public static final RegistryObject<AsphaltInfuser> INFUSER = registerBlock("infuser",
            () -> new AsphaltInfuser(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()), // NOTICE
            Eunithice.EUNITHICE_ITEMS_TAB);

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
