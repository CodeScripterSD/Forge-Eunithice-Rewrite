package com.craftminerd.eunithice.block;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.block.custom.BounceInfusedAsphalt;
import com.craftminerd.eunithice.block.custom.HoneyInfusedAsphalt;
import com.craftminerd.eunithice.block.custom.LeuriteCrop;
import com.craftminerd.eunithice.block.custom.SpeedInfusedAsphalt;
import com.craftminerd.eunithice.item.EunithiceItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class EunithiceBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Eunithice.MODID);

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return EunithiceItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }

    public static final RegistryObject<Block> ASPHALT = registerBlock("asphalt",
            () -> new Block(BlockBehaviour.Properties
                    .of(Material.STONE, MaterialColor.COLOR_BLACK)
                    .speedFactor(1.1f)),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<SpeedInfusedAsphalt> SPEED_INFUSED_ASPHALT = registerBlock("speed_infused_asphalt",
            () -> new SpeedInfusedAsphalt(BlockBehaviour.Properties
                    .of(Material.STONE, MaterialColor.COLOR_BLUE)
                    .strength(0.3f, 20f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.BASALT),
                    1.9D),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<BounceInfusedAsphalt> BOUNCE_INFUSED_ASPHALT = registerBlock("bounce_infused_asphalt",
            () -> new BounceInfusedAsphalt(BlockBehaviour.Properties
                    .of(Material.STONE, MaterialColor.COLOR_LIGHT_GREEN)
                    .strength(0.3f, 20f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.BASALT)),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<HoneyInfusedAsphalt> HONEY_INFUSED_ASPHALT = registerBlock("honey_infused_asphalt",
            () -> new HoneyInfusedAsphalt(BlockBehaviour.Properties
                    .of(Material.STONE, MaterialColor.COLOR_ORANGE)
                    .strength(0.3f, 20f)
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
            () -> new Block(BlockBehaviour.Properties
                    .of(Material.METAL, MaterialColor.RAW_IRON)
                    .strength(5f, 15f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<Block> HEAVY_COMPRESSED_PLATED_IRON_BLOCK = registerBlock("heavy_compressed_iron_plate_block",
            () -> new Block(BlockBehaviour.Properties
                    .of(Material.METAL, MaterialColor.RAW_IRON)
                    .strength(5f, 25f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<Block> IRON_PLATE_DOOR = registerBlock("iron_plate_door",
            () -> new DoorBlock(BlockBehaviour.Properties
                    .of(Material.METAL, MaterialColor.RAW_IRON)
                    .strength(5.0F, 6.0F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.METAL)),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<Block> IRON_PLATE_TRAPDOOR = registerBlock("iron_plate_trapdoor",
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
            () -> new Block(BlockBehaviour.Properties
                    .of(Material.METAL, MaterialColor.GOLD)
                    .strength(6f, 15f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<Block> HEAVY_COMPRESSED_PLATED_GOLD_BLOCK = registerBlock("heavy_compressed_gold_plate_block",
            () -> new Block(BlockBehaviour.Properties
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
            () -> new Block(BlockBehaviour.Properties
                    .of(Material.METAL, MaterialColor.DIAMOND)
                    .strength(7f,50f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<Block> HEAVY_COMPRESSED__DIAMOND_PLATE_BLOCK = registerBlock("heavy_compressed_diamond_plate_block",
            () -> new Block(BlockBehaviour.Properties
                    .of(Material.METAL, MaterialColor.DIAMOND)
                    .strength(7f,65f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)),
            Eunithice.EUNITHICE_ITEMS_TAB);

    public static final RegistryObject<Block> LEURITE = BLOCKS.register("leurite_crop",
            () -> new LeuriteCrop(BlockBehaviour.Properties
                    .copy(net.minecraft.world.level.block.Blocks.WHEAT)));
}
