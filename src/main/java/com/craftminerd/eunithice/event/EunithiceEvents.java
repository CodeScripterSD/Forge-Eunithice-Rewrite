package com.craftminerd.eunithice.event;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.block.EunithiceBlocks;
import com.craftminerd.eunithice.item.EunithiceItems;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.List;

@Mod.EventBusSubscriber(modid = Eunithice.MODID)
public class EunithiceEvents {

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.MASON) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();


            trades.get(1).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 16), new ItemStack(EunithiceBlocks.SPEED_INFUSED_ASPHALT.get(), 8), 16, 8, 0.02F));

            trades.get(4).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 32), new ItemStack(EunithiceBlocks.BOUNCE_INFUSED_ASPHALT.get(), 4), 32, 12, 0.02F));

            trades.get(4).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 32), new ItemStack(EunithiceBlocks.HONEY_INFUSED_ASPHALT.get(), 4), 32, 9, 0.02F));
        }
    }

}