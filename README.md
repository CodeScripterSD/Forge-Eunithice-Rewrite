# Forge Eunithice 1.18.2

A Forge Minecraft Mod

[Latest Release](https://github.com/CodeScripterSD/Forge-Eunithice-Rewrite/releases/tag/1.18.2-0.0.2)  
[Latest Release Patch Notes](#version-002-released)

_Note: Every single thing is subject to change_  
_Note 2: You will need a lot of resources to progress in this mod._

## Version 0.0.3 (In Development)

### Additions

#### Blocks
- Added Darkwood
  - New wood variant, currently missing Sign and Boat
- Added Stone Door and Trapdoor
- Added a new fluid "Chroma" (Doesn't act like a fluid due to lacking fluid API)
  - Extends usage of Extraction Core in Extractor recipes; Extraction Core now turns into Chroma
- Added Deepslate Neudonite Ore
- Added much stronger Speed Infused Asphalt variant, Calcified Speed Infused Asphalt (Name will definitely change, this was named as a joke)
#### Enchantments
- Added an "Autosmelt" enchantment, blocks mined with a tool using this enchantment will have their normal drops smelted. Incompatible with Silk Touch
- Added a "Firing Speed" enchantment
  - Levels 1-4
  - Decreases the cool down on Shortbow items
- Added a "Teleportitis" enchantment
  - Levels 2-4
  - Inspired by Hypixel Skyblock's "Aspect of the End" item
  - Right click to teleport forward a short distance proportional to its level
#### Gear
- Added "Experimental Shortbow"
  - Inspired by Hypixel Skyblock's Juju Shortbow
  - Shoots arrow instantly upon right click, but has a cool down
- Added 3 new Tool and Armor Tiers
  - Neudonite
  - Lymine
  - Myelite
- Added Serrated Blade
  - Uses old Diamond Plated Sword texture as a placeholder
  - Damage increases the more kills you have with it
#### Misc
- Eunithice's Metal Blocks may be used as a base for beacons
  - Neudonite, Lymine, and Myelite
- Eunithice's Metal Ingots may be used as payment for beacons
  - Neudonite, Lymine, and Myelite
#### Technical
- Added a tag for shortbow items so that other mods can allow Eunithice's "Firing Speed" enchantment
  - The enchantment has no base implementation; implementation must be done by the other mod's developer
- Added a tag for blocks specifically mineable by each of Eunithice's tool tiers
- Modified Neudonite spawn rate and range. (New range: -20 to 40)
- Added configs for Neudonite Ore vein spawning & size
  
### Changes

- Gave Speed, Bounce, and Honey gels independent textures
- Asphalt now has a more significant speed-up
- Burn Core now has an animated texture
- Honey/Bounce Infused Asphalt trades have been moved from LVL 4 to LVL 2 and halved the cost
- Extraction cores now supply Extraction Fluid for extractor recipes
- Extractor recipes can now support multiple outputs
- Changed the way Bounce Infused Asphalt calculates bounce power
- Increased the strength of Honey Infused Asphalt
- Search bar added to Eunithice creative tab
- Tweaked Withered Thorns enchantment
- Added functionality to Gels
  - Right click Gel on Asphalt turns the asphalt into the gel's infuse type
  - Added durability (8 uses)
  - Tweaked recipe in Asphalt Infuser
    - 1 Durability and 1 Asphalt creates 2 Infused Asphalt of the Gel's type
- Changed hammer durability values
  - Tier 1 from 640 to 128
  - Tier 2 from 5120 to 1024
- Changed recipe for Speed Imbued Asphalt to mimic the other Infused variant's recipes

### Fixes

- Burn Core now works when used by a dispenser
- Fixed a bug in Extractor & Asphalt Infuser
  - Previously would use a durability item past 0, leading to negative values

### Removals

- Removed Iron Core and Iron Core Fragment
- Removed Withering Thorns enchantment
- Removed handheld ender chest
  - Moved to a new mod, "Handheld Utilities"
- Removed Iron/Gold/Diamond plated tool sets and armor
- Removed Plated variants of Iron/Gold/Diamond blocks
- Removed hammers
- Removed plates

## Version 0.0.2 (Released)

### Additions

- Added 3 "Gels"
  - [Honey Gel], [Bounce Gel], [Speed Gel] (Textures will be added in a later update)
  - Used in Asphalt Infuser to create Infused variants of Asphalt
- Added a new Ore, Neudonite
  - Used in some recipes
  - Found (Hopefully) in a range of Y=64 to Y=-64, Becoming increasingly more common closer to -64 (Only spawns below Y=0 if it can replace stone; no Deepslate version)
- Added crafting stations (Models are placeholder, old models from earlier attempts at Eunithice)
  - Asphalt Infuser
    - Apply gels to asphalt blocks
  - Extractor
    - Use Cores and Hammers to extract resources

### Changes

- Made Revival Sandwich less overpowered
  - Regeneration 3 for 40 seconds
  - Resistance 2 for 3 minutes
  - Fire Resistance for 6 minutes
  - Absorption 5 for 2 minutes
- Iron Core is obsolete
- Tweaked recipes
  - Bounce Infused Asphalt, Honey Infused Asphalt
    - Now requires Asphalt instead of Speed Infused Asphalt
  - Gold Plate, Iron Plate
    - Now can be crafted with all hammer levels
  - Handheld Ender Chest
    - Uses Neudonite Ingots instead of Iron Plates
  - Tier 2 Hammer
    - Uses Neudonite Ingots instead of Gold Plates
    - Uses Iron Ingots instead of Sticks
    - Uses Iron Plate instead of Iron Core

### New Recipes

- Crafting Station Recipes
  - Asphalt from extracting Honey, Bounce, or Speed Infused variants
  - Charcoal
    - Burn Core + Log that burns in extractor -> 2 Charcoal
    - Extraction Core + Log that burns -> 3 Charcoal
  - Coal
    - Extraction Core + Coal Ore or Deepslate Coal Ore -> 3 Coal
  - Copper Ingot
    - Extraction Core + Raw Copper -> 2 Copper Ingot
    - Extraction Core + Copper Ore or Deepslate Copper Ore -> 3 Copper Ingot
  - Diamond
    - Extraction Core + Diamond Ore -> 2 Diamond
    - Extraction Core + Deepslate Diamond Ore -> 3 Diamond
  - Emerald
    - Extraction Core + Emerald Ore -> 2 Emerald
    - Extraction Core + Deepslate Emerald Ore -> 3 Emerald
  - Gold Ingot
    - Extraction Core + Raw Gold -> 2 Gold Ingot
    - Extraction Core + Gold Ore or Deepslate Gold Ore -> 3 Gold Ingot
  - Iron Ingot
    - Extraction Core + Raw Iron -> 2 Iron Ingot
    - Extraction Core + Iron Ore or Deepslate Iron Ore -> 3 Iron Ingot
  - Neudonite Ingot
    - Extraction Core + Raw Neudonite -> 2 Iron Ingot
    - Extraction Core + Neudonite Ore -> 3 Neudonite Ingot
  - Bounce Infused Asphalt
    - Infusing Asphalt with Bounce Gel -> 8 Bounce Infused Asphalt
  - Honey Infused Asphalt
    - Infusing Asphalt with Honey Gel -> 8 Honey Infused Asphalt
  - Speed Infused Asphalt
    - Infusing Asphalt with Speed Gel -> 8 Speed Infused Asphalt

## Version 0.0.1 (Released)

- Iron, Gold, and Diamond Plates for excessive micro-crafting
- 2 Tiers of hammer used for producing plates and other micro-crafting recipes
- 3 Sets of "Plated" blocks
  - Iron, Gold, and Diamond (textures subject to change) for extended use in micro-crafting and base-building (If you have the resources to waste)
- 3 Tiers of tools/armor
  - Plated Iron, Plated Gold, Plated Diamond (upgraded from their respective sets and definitely not cheap)
- Set of 4 "Asphalts"
  - Normal
    - Minor speed increase while walking on it
  - Speed Infused
    - Multiplies your x+z motion while standing on it, creating an effect like ice that makes you move faster (Unless holding sneak)
  - Bounce Infused
    - Increases jump height when jumping from the block
    - Forces the character upwards when moving at "high speeds" (Scales with speed)
  - Honey Infused
    - Acts to slow down movement to just under normal walking speed
    - Reduces fall damage more than a hay block / honey block, but not fully
- 2 Cores
  - Extraction Core (No purpose currently)
  - Burn Core
    - Burns for 800 ticks (4 vanilla items) for each durability
    - Can be used as flint and steel
- 2 Functional Blocks
  - Iron Plate Door
  - Iron Plate Trapdoor
- Handheld Ender Chest
  - Ender Chest on a Stick
- 5 Foods (All textures likely to change)
  - Leurite Bread
    - Made from Leurite Grains, grown from Leurite Seeds (Knockoff wheat but better but absurdly expensive)
  - Meatball Soup
    - Cooked Mutton, Rabbit Stew, Cooked Chicken, Steak, Cooked Salmon, Cooked Cod, and Cooked Porkchop combined into a disgustingly good concoction
  - Veggie Salad
    - Carrot, Baked Potato, Beetroot soup, and Dried Kelp combined into an oddly flavored delectable
  - Fruit Dish
    - Glow Berries, Sweet Berries, Pumpkin Pie, Melon Slice, and Apple mixed together to make... _something_
  - Revival Sandwich (Name subject to change)
    - The above 4 foods and an Enchanted Golden Apple mixed to form an oddly powerful... sandwich?
