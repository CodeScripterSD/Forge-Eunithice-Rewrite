package com.craftminerd.eunithice.item;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.block.EunithiceBlocks;
import com.craftminerd.eunithice.item.custom.BurnCore;
import com.craftminerd.eunithice.item.custom.HammerItem;
import com.craftminerd.eunithice.item.custom.HandheldEnderChest;
import com.craftminerd.eunithice.item.custom.MotionController;
import com.craftminerd.eunithice.item.custom.ExtractionCore;
import com.craftminerd.eunithice.item.custom.foods.*;
import com.craftminerd.eunithice.item.tiers.EunithiceArmorMaterials;
import com.craftminerd.eunithice.item.tiers.EunithiceToolTiers;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EunithiceItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Eunithice.MODID);

    // CORES

    public static final RegistryObject<BurnCore> BURN_CORE = ITEMS.register("burn_core",
            () -> new BurnCore(new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .defaultDurability(640)
            ));

    public static final RegistryObject<ExtractionCore> EXTRACTION_CORE = ITEMS.register("extraction_core",
            () -> new ExtractionCore(new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .defaultDurability(1280)
            ));

    // INGOTS

    public static final RegistryObject<Item> NEUDONITE_INGOT = ITEMS.register("neudonite_ingot",
            () -> new Item(new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
            ));

    public static final RegistryObject<Item> LEURIUM_INGOT = ITEMS.register("leurium_ingot",
            () -> new Item(new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
            ));

    // ORES

    public static final RegistryObject<Item> RAW_NEUDONITE = ITEMS.register("raw_neudonite",
            () -> new Item(new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
            ));

    // INGREDIENTS

    public static final RegistryObject<Item> SPEED_GEL = ITEMS.register("speed_gel",
            () -> new Item(new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
            ));

    public static final RegistryObject<Item> BOUNCE_GEL = ITEMS.register("bounce_gel",
            () -> new Item(new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
            ));

    public static final RegistryObject<Item> HONEY_GEL = ITEMS.register("honey_gel",
            () -> new Item(new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
            ));

    public static final RegistryObject<Item> IRON_PLATE = ITEMS.register("iron_plate",
            () -> new Item(new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
            ));

    public static final RegistryObject<Item> IRON_CORE = ITEMS.register("iron_core",
            () -> new Item(new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
            ));

    public static final RegistryObject<Item> IRON_CORE_FRAGMENT = ITEMS.register("iron_core_fragment",
            () -> new Item(new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
            ));

    public static final RegistryObject<Item> GOLD_PLATE = ITEMS.register("gold_plate",
            () -> new Item(new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
            ));

    public static final RegistryObject<Item> DIAMOND_PLATE = ITEMS.register("diamond_plate",
            () -> new Item(new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
            ));

    public static final RegistryObject<Item> LEURITE_GRAINS = ITEMS.register("leurite_grains",
            () -> new Item(new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    // TOOLS

    public static final RegistryObject<HammerItem> TIER_ONE_HAMMER = ITEMS.register("tier_one_hammer", () ->
        new HammerItem(new Item.Properties()
                .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                .defaultDurability(640)
        ));

    public static final RegistryObject<HammerItem> TIER_TWO_HAMMER = ITEMS.register("tier_two_hammer", () ->
            new HammerItem(new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .defaultDurability(5120)
            ));

    public static final RegistryObject<HandheldEnderChest> HANDHELD_ENDER_CHEST = ITEMS.register("handheld_ender_chest",
            () -> new HandheldEnderChest(new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .stacksTo(1)
            ));

/*
    public static final RegistryObject<MotionController> MOTION_CONTROLLER = ITEMS.register("motion_controller",
            () -> new MotionController(new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .stacksTo(1)
            ));
*/

    // OLD

    public static final RegistryObject<ShovelItem> PLATED_IRON_SHOVEL = ITEMS.register("plated_iron_shovel",
            () -> new ShovelItem(EunithiceToolTiers.PLATED_IRON, 1.5f, -3.0f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<PickaxeItem> PLATED_IRON_PICKAXE = ITEMS.register("plated_iron_pickaxe",
            () -> new PickaxeItem(EunithiceToolTiers.PLATED_IRON, 1, -2.8f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<AxeItem> PLATED_IRON_AXE = ITEMS.register("plated_iron_axe",
            () -> new AxeItem(EunithiceToolTiers.PLATED_IRON, 6.0f, -3.0f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<HoeItem> PLATED_IRON_HOE = ITEMS.register("plated_iron_hoe",
            () -> new HoeItem(EunithiceToolTiers.PLATED_IRON, -3, 0.0f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<SwordItem> PLATED_IRON_SWORD = ITEMS.register("plated_iron_sword",
            () -> new SwordItem(EunithiceToolTiers.PLATED_IRON, 3, -2.4f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));



    public static final RegistryObject<ShovelItem> PLATED_GOLD_SHOVEL = ITEMS.register("plated_gold_shovel",
            () -> new ShovelItem(EunithiceToolTiers.PLATED_GOLD, 1.5f, -3.0f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<PickaxeItem> PLATED_GOLD_PICKAXE = ITEMS.register("plated_gold_pickaxe",
            () -> new PickaxeItem(EunithiceToolTiers.PLATED_GOLD, 1, -2.8f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<AxeItem> PLATED_GOLD_AXE = ITEMS.register("plated_gold_axe",
            () -> new AxeItem(EunithiceToolTiers.PLATED_GOLD, 6.0f, -3.0f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<HoeItem> PLATED_GOLD_HOE = ITEMS.register("plated_gold_hoe",
            () -> new HoeItem(EunithiceToolTiers.PLATED_GOLD, -3, 0.0f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<SwordItem> PLATED_GOLD_SWORD = ITEMS.register("plated_gold_sword",
            () -> new SwordItem(EunithiceToolTiers.PLATED_GOLD, 3, -2.4f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));


    public static final RegistryObject<ShovelItem> PLATED_DIAMOND_SHOVEL = ITEMS.register("plated_diamond_shovel",
            () -> new ShovelItem(EunithiceToolTiers.PLATED_DIAMOND, 1.5f, -2.6f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<PickaxeItem> PLATED_DIAMOND_PICKAXE = ITEMS.register("plated_diamond_pickaxe",
            () -> new PickaxeItem(EunithiceToolTiers.PLATED_DIAMOND, 1, -2.6f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<AxeItem> PLATED_DIAMOND_AXE = ITEMS.register("plated_diamond_axe",
            () -> new AxeItem(EunithiceToolTiers.PLATED_DIAMOND, 6.2f, -2.6f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<HoeItem> PLATED_DIAMOND_HOE = ITEMS.register("plated_diamond_hoe",
            () -> new HoeItem(EunithiceToolTiers.PLATED_DIAMOND, -3, 0.2f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<SwordItem> PLATED_DIAMOND_SWORD = ITEMS.register("plated_diamond_sword",
            () -> new SwordItem(EunithiceToolTiers.PLATED_DIAMOND, 3, -2f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    // ARMOR
    // OLD

    public static final RegistryObject<ArmorItem> PLATED_IRON_HELMET = ITEMS.register("plated_iron_helmet",
            () -> new ArmorItem(EunithiceArmorMaterials.PLATED_IRON, EquipmentSlot.HEAD, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<ArmorItem> PLATED_IRON_CHESTPLATE = ITEMS.register("plated_iron_chestplate",
            () -> new ArmorItem(EunithiceArmorMaterials.PLATED_IRON, EquipmentSlot.CHEST, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<ArmorItem> PLATED_IRON_LEGGINGS = ITEMS.register("plated_iron_leggings",
            () -> new ArmorItem(EunithiceArmorMaterials.PLATED_IRON, EquipmentSlot.LEGS, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<ArmorItem> PLATED_IRON_BOOTS = ITEMS.register("plated_iron_boots",
            () -> new ArmorItem(EunithiceArmorMaterials.PLATED_IRON, EquipmentSlot.FEET, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));



    public static final RegistryObject<ArmorItem> PLATED_GOLD_HELMET = ITEMS.register("plated_gold_helmet",
            () -> new ArmorItem(EunithiceArmorMaterials.PLATED_GOLD, EquipmentSlot.HEAD, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<ArmorItem> PLATED_GOLD_CHESTPLATE = ITEMS.register("plated_gold_chestplate",
            () -> new ArmorItem(EunithiceArmorMaterials.PLATED_GOLD, EquipmentSlot.CHEST, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<ArmorItem> PLATED_GOLD_LEGGINGS = ITEMS.register("plated_gold_leggings",
            () -> new ArmorItem(EunithiceArmorMaterials.PLATED_GOLD, EquipmentSlot.LEGS, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<ArmorItem> PLATED_GOLD_BOOTS = ITEMS.register("plated_gold_boots",
            () -> new ArmorItem(EunithiceArmorMaterials.PLATED_GOLD, EquipmentSlot.FEET, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<ArmorItem> PLATED_DIAMOND_HELMET = ITEMS.register("plated_diamond_helmet",
            () -> new ArmorItem(EunithiceArmorMaterials.PLATED_DIAMOND, EquipmentSlot.HEAD, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));



    public static final RegistryObject<ArmorItem> PLATED_DIAMOND_CHESTPLATE = ITEMS.register("plated_diamond_chestplate",
            () -> new ArmorItem(EunithiceArmorMaterials.PLATED_DIAMOND, EquipmentSlot.CHEST, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<ArmorItem> PLATED_DIAMOND_LEGGINGS = ITEMS.register("plated_diamond_leggings",
            () -> new ArmorItem(EunithiceArmorMaterials.PLATED_DIAMOND, EquipmentSlot.LEGS, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    public static final RegistryObject<ArmorItem> PLATED_DIAMOND_BOOTS = ITEMS.register("plated_diamond_boots",
            () -> new ArmorItem(EunithiceArmorMaterials.PLATED_DIAMOND, EquipmentSlot.FEET, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    // FOODS

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
            () -> new BlockItem(EunithiceBlocks.LEURITE.get(), new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));
}
