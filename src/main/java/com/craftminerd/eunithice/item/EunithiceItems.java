package com.craftminerd.eunithice.item;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.block.EunithiceBlocks;
import com.craftminerd.eunithice.fluid.EunithiceFluids;
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

    public static final RegistryObject<Item> IRON_PLATE = ITEMS.register("iron_plate",
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
                .defaultDurability(128)
        ));

    public static final RegistryObject<HammerItem> TIER_TWO_HAMMER = ITEMS.register("tier_two_hammer", () ->
            new HammerItem(new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .defaultDurability(1024)
            ));

/*
    public static final RegistryObject<MotionController> MOTION_CONTROLLER = ITEMS.register("motion_controller",
            () -> new MotionController(new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)
                    .stacksTo(1)
            ));
*/

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

    // WEAPONS


    public static final RegistryObject<SerratedBlade> SERRATED_BLADE = ITEMS.register("serrated_blade",
            () -> new SerratedBlade(EunithiceToolTiers.SERRATED, 3, -2.4f, new Item.Properties()
                    .tab(Eunithice.EUNITHICE_ITEMS_TAB)));

    //NEUDONITE GEAR

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

    public static final RegistryObject<BucketItem> EXTRACTION_BUCKET = ITEMS.register("extraction_bucket",
            () -> new BucketItem(EunithiceFluids.EXTRACTION_FLUID,
                    new Item.Properties().tab(Eunithice.EUNITHICE_ITEMS_TAB).stacksTo(1)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
