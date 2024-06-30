package com.craftminerd.eunithice.item;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.block.EunithiceBlocks;
import com.craftminerd.eunithice.item.custom.*;
import com.craftminerd.eunithice.item.custom.foods.*;
import com.craftminerd.eunithice.item.tiers.EunithiceArmorMaterials;
import com.craftminerd.eunithice.item.tiers.EunithiceToolTiers;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EunithiceItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Eunithice.MODID);

//    public static final RegistryObject<FluidCapableItem> FLUID_CAPABLE_ITEM = ITEMS.register("fluid_capable_item",
//            () -> new FluidCapableItem(new Item.Properties().tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<Item> KICKBACK_TEST = ITEMS.register("kickback_test",
            () -> new KickbackItemTest(new Item.Properties().tab(Eunithice.EUNITHICE_ITEMS_TAB), 3D));

    public static final RegistryObject<CustomBowItem> EXPERIMENTAL_BOW = ITEMS.register("experimental_bow",
            () -> new CustomBowItem(new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .defaultDurability(384)
                    .rarity(Rarity.RARE)
                    .fireResistant()
            ));

    //// Omni-tools
    public static final RegistryObject<OmnitoolItem> GOLDEN_OMNITOOL = ITEMS.register("golden_omnitool",
            () -> new OmnitoolItem(Tiers.GOLD, new Item.Properties().tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<OmnitoolItem> WOODEN_OMNITOOL = ITEMS.register("wooden_omnitool",
            () -> new OmnitoolItem(Tiers.WOOD, new Item.Properties().tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<OmnitoolItem> STONE_OMNITOOL = ITEMS.register("stone_omnitool",
            () -> new OmnitoolItem(Tiers.STONE, new Item.Properties().tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<OmnitoolItem> IRON_OMNITOOL = ITEMS.register("iron_omnitool",
            () -> new OmnitoolItem(Tiers.IRON, new Item.Properties().tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<OmnitoolItem> NEUDONITE_OMNITOOL = ITEMS.register("neudonite_omnitool",
            () -> new OmnitoolItem(EunithiceToolTiers.NEUDONITE, new Item.Properties().tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<OmnitoolItem> DIAMOND_OMNITOOL = ITEMS.register("diamond_omnitool",
            () -> new OmnitoolItem(Tiers.DIAMOND, new Item.Properties().tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<OmnitoolItem> NETHERITE_OMNITOOL = ITEMS.register("netherite_omnitool",
            () -> new OmnitoolItem(Tiers.NETHERITE, new Item.Properties().tab(Eunithice.EUNITHICE_ITEMS_TAB).fireResistant()));

    public static final RegistryObject<OmnitoolItem> LYMINE_OMNITOOL = ITEMS.register("lymine_omnitool",
            () -> new OmnitoolItem(EunithiceToolTiers.LYMINE, new Item.Properties().tab(Eunithice.EUNITHICE_ITEMS_TAB).fireResistant()));

    public static final RegistryObject<OmnitoolItem> MYELITE_OMNITOOL = ITEMS.register("myelite_omnitool",
            () -> new OmnitoolItem(EunithiceToolTiers.MYELITE, new Item.Properties().tab(Eunithice.EUNITHICE_ITEMS_TAB).fireResistant()));

    //// NEUDONITE

    public static final RegistryObject<Item> NEUDONITE_INGOT = ITEMS.register("neudonite_ingot",
            () -> new Item(new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<Item> RAW_NEUDONITE = ITEMS.register("raw_neudonite",
            () -> new Item(new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<SwordItem> NEUDONITE_SWORD = ITEMS.register("neudonite_sword",
            () -> new SwordItem(EunithiceToolTiers.NEUDONITE, 3, -2.4f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<ShovelItem> NEUDONITE_SHOVEL = ITEMS.register("neudonite_shovel",
            () -> new ShovelItem(EunithiceToolTiers.NEUDONITE, 1.5f, -3.0f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<PickaxeItem> NEUDONITE_PICKAXE = ITEMS.register("neudonite_pickaxe",
            () -> new PickaxeItem(EunithiceToolTiers.NEUDONITE, 1, -2.8f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<AxeItem> NEUDONITE_AXE = ITEMS.register("neudonite_axe",
            () -> new AxeItem(EunithiceToolTiers.NEUDONITE, 5.5f, -3.1f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<HoeItem> NEUDONITE_HOE = ITEMS.register("neudonite_hoe",
            () -> new HoeItem(EunithiceToolTiers.NEUDONITE, 0, -2.5f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<ArmorItem> NEUDONITE_HELMET = ITEMS.register("neudonite_helmet",
            () -> new ArmorItem(EunithiceArmorMaterials.NEUDONITE, EquipmentSlot.HEAD, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<ArmorItem> NEUDONITE_CHESTPLATE = ITEMS.register("neudonite_chestplate",
            () -> new ArmorItem(EunithiceArmorMaterials.NEUDONITE, EquipmentSlot.CHEST, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<ArmorItem> NEUDONITE_LEGGINGS = ITEMS.register("neudonite_leggings",
            () -> new ArmorItem(EunithiceArmorMaterials.NEUDONITE, EquipmentSlot.LEGS, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<ArmorItem> NEUDONITE_BOOTS = ITEMS.register("neudonite_boots",
            () -> new ArmorItem(EunithiceArmorMaterials.NEUDONITE, EquipmentSlot.FEET, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    //// LYMINE

    public static final RegistryObject<Item> LYMINE = ITEMS.register("lymine",
            () -> new Item(new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .fireResistant()));

    public static final RegistryObject<SwordItem> LYMINE_SWORD = ITEMS.register("lymine_sword",
            () -> new SwordItem(EunithiceToolTiers.LYMINE, 3, -2.4f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .fireResistant()));

    public static final RegistryObject<ShovelItem> LYMINE_SHOVEL = ITEMS.register("lymine_shovel",
            () -> new ShovelItem(EunithiceToolTiers.LYMINE, 1.5f, -3.0f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .fireResistant()));

    public static final RegistryObject<PickaxeItem> LYMINE_PICKAXE = ITEMS.register("lymine_pickaxe",
            () -> new PickaxeItem(EunithiceToolTiers.LYMINE, 1, -2.8f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .fireResistant()));

    public static final RegistryObject<AxeItem> LYMINE_AXE = ITEMS.register("lymine_axe",
            () -> new AxeItem(EunithiceToolTiers.LYMINE, 5.5f, -3.1f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .fireResistant()));

    public static final RegistryObject<HoeItem> LYMINE_HOE = ITEMS.register("lymine_hoe",
            () -> new HoeItem(EunithiceToolTiers.LYMINE, 0, -2.5f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .fireResistant()));

    public static final RegistryObject<ArmorItem> LYMINE_HELMET = ITEMS.register("lymine_helmet",
            () -> new ArmorItem(EunithiceArmorMaterials.LYMINE, EquipmentSlot.HEAD, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .fireResistant()));

    public static final RegistryObject<ArmorItem> LYMINE_CHESTPLATE = ITEMS.register("lymine_chestplate",
            () -> new ArmorItem(EunithiceArmorMaterials.LYMINE, EquipmentSlot.CHEST, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .fireResistant()));

    public static final RegistryObject<ArmorItem> LYMINE_LEGGINGS = ITEMS.register("lymine_leggings",
            () -> new ArmorItem(EunithiceArmorMaterials.LYMINE, EquipmentSlot.LEGS, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .fireResistant()));

    public static final RegistryObject<ArmorItem> LYMINE_BOOTS = ITEMS.register("lymine_boots",
            () -> new ArmorItem(EunithiceArmorMaterials.LYMINE, EquipmentSlot.FEET, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .fireResistant()));
    
    //// MYELITE

    public static final RegistryObject<Item> MYELITE_INGOT = ITEMS.register("myelite_ingot",
            () -> new Item(new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .fireResistant()));

    public static final RegistryObject<SwordItem> MYELITE_SWORD = ITEMS.register("myelite_sword",
            () -> new SwordItem(EunithiceToolTiers.MYELITE, 3, -2.4f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .fireResistant()));

    public static final RegistryObject<ShovelItem> MYELITE_SHOVEL = ITEMS.register("myelite_shovel",
            () -> new ShovelItem(EunithiceToolTiers.MYELITE, 1.5f, -3.0f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .fireResistant()));

    public static final RegistryObject<PickaxeItem> MYELITE_PICKAXE = ITEMS.register("myelite_pickaxe",
            () -> new PickaxeItem(EunithiceToolTiers.MYELITE, 1, -2.8f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .fireResistant()));

    public static final RegistryObject<AxeItem> MYELITE_AXE = ITEMS.register("myelite_axe",
            () -> new AxeItem(EunithiceToolTiers.MYELITE, 5.5f, -3.1f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .fireResistant()));

    public static final RegistryObject<HoeItem> MYELITE_HOE = ITEMS.register("myelite_hoe",
            () -> new HoeItem(EunithiceToolTiers.MYELITE, 0, -2.5f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .fireResistant()));

    public static final RegistryObject<ArmorItem> MYELITE_HELMET = ITEMS.register("myelite_helmet",
            () -> new ArmorItem(EunithiceArmorMaterials.MYELITE, EquipmentSlot.HEAD, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .fireResistant()));

    public static final RegistryObject<ArmorItem> MYELITE_CHESTPLATE = ITEMS.register("myelite_chestplate",
            () -> new ArmorItem(EunithiceArmorMaterials.MYELITE, EquipmentSlot.CHEST, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .fireResistant()));

    public static final RegistryObject<ArmorItem> MYELITE_LEGGINGS = ITEMS.register("myelite_leggings",
            () -> new ArmorItem(EunithiceArmorMaterials.MYELITE, EquipmentSlot.LEGS, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .fireResistant()));

    public static final RegistryObject<ArmorItem> MYELITE_BOOTS = ITEMS.register("myelite_boots",
            () -> new ArmorItem(EunithiceArmorMaterials.MYELITE, EquipmentSlot.FEET, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .fireResistant()));

    ////

    // CORES

    public static final RegistryObject<BurnCore> BURN_CORE = ITEMS.register("burn_core",
            () -> new BurnCore(new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .defaultDurability(321)
            ));

    public static final RegistryObject<ExtractionCore> EXTRACTION_CORE = ITEMS.register("extraction_core",
            () -> new ExtractionCore(new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .defaultDurability(321)
            ));

    // INGREDIENTS

    public static final RegistryObject<Item> SPEED_GEL = ITEMS.register("speed_gel",
            () -> new GelItem(new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .defaultDurability(4),
                    EunithiceBlocks.SPEED_INFUSED_ASPHALT.get()
            ));

    public static final RegistryObject<Item> BOUNCE_GEL = ITEMS.register("bounce_gel",
            () -> new GelItem(new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .defaultDurability(4),
                    EunithiceBlocks.BOUNCE_INFUSED_ASPHALT.get()
            ));

    public static final RegistryObject<Item> HONEY_GEL = ITEMS.register("honey_gel",
            () -> new GelItem(new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .defaultDurability(4),
                    EunithiceBlocks.HONEY_INFUSED_ASPHALT.get()
            ));
//
//    public static final RegistryObject<Item> IRON_PLATE = ITEMS.register("iron_plate",
//            () -> new Item(new Item.Properties()
//                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
//            ));
//
//    public static final RegistryObject<Item> GOLD_PLATE = ITEMS.register("gold_plate",
//            () -> new Item(new Item.Properties()
//                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
//            ));
//
//    public static final RegistryObject<Item> DIAMOND_PLATE = ITEMS.register("diamond_plate",
//            () -> new Item(new Item.Properties()
//                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
//            ));

    public static final RegistryObject<Item> LEURITE_GRAINS = ITEMS.register("leurite_grains",
            () -> new Item(new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

//    public static final RegistryObject<HammerItem> TIER_ONE_HAMMER = ITEMS.register("tier_one_hammer", () ->
//        new HammerItem(new Item.Properties()
//                .tab(Eunithice.EUNITHICE_ITEMS_TAB)
//                .defaultDurability(128)
//        ));
//
//    public static final RegistryObject<HammerItem> TIER_TWO_HAMMER = ITEMS.register("tier_two_hammer", () ->
//            new HammerItem(new Item.Properties()
//                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
//                    .defaultDurability(1024)
//            ));

    public static final RegistryObject<Item> LEURITE_BREAD = ITEMS.register("leurite_bread",
            () -> new Item(new Item.Properties()
                    .food(EunithiceFoods.LEURITE_BREAD)
                    .rarity(Rarity.UNCOMMON)
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<MeatballSoup> MEATBALL_SOUP = ITEMS.register("meatball_soup",
            () -> new MeatballSoup(new Item.Properties()
                    .food(EunithiceFoods.MEATBALL_SOUP)
                    .rarity(Rarity.RARE)
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<VegetableSalad> VEGETABLE_SALAD = ITEMS.register("vegetable_salad",
            () -> new VegetableSalad(new Item.Properties()
                    .food(EunithiceFoods.VEGETABLE_SALAD)
                    .rarity(Rarity.RARE)
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<FruitDish> FRUIT_DISH = ITEMS.register("fruit_dish",
            () -> new FruitDish(new Item.Properties()
                    .food(EunithiceFoods.FRUIT_DISH)
                    .rarity(Rarity.RARE)
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<RevivalSandwich> REVIVAL_SANDWICH = ITEMS.register("revival_sandwich",
            () -> new RevivalSandwich(new Item.Properties()
                    .food(EunithiceFoods.REVIVAL_SANDWICH)
                    .rarity(Rarity.EPIC)
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<BlockItem> LEURITE_SEEDS = ITEMS.register("leurite_seeds",
            () -> new BlockItem(EunithiceBlocks.LEURITE_CROP.get(), new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<SerratedBlade> SERRATED_BLADE = ITEMS.register("serrated_blade",
            () -> new SerratedBlade(EunithiceToolTiers.SERRATED, 3, -2.4f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

//    public static final RegistryObject<BucketItem> CHROMA_BUCKET = ITEMS.register("chroma_bucket",
//            () -> new BucketItem(EunithiceFluids.CHROMA,
//                    new Item.Properties().tab(Eunithice.EUNITHICE_ITEMS_TAB).stacksTo(1)));




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
