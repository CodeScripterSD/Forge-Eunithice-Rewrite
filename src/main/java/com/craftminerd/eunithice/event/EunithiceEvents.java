package com.craftminerd.eunithice.event;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.block.EunithiceBlocks;
import com.craftminerd.eunithice.enchantments.EunithiceEnchantments;
import com.craftminerd.eunithice.item.EunithiceItems;
import com.craftminerd.eunithice.util.EunithiceTags;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.BlockEventData;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = Eunithice.MODID)
public class EunithiceEvents {

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.MASON) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            trades.get(1).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 16), new ItemStack(EunithiceBlocks.ASPHALT.get(), 4), 32, 3, 0.02F));

            trades.get(2).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 16), new ItemStack(EunithiceBlocks.SPEED_INFUSED_ASPHALT.get(), 8), 16, 8, 0.02F));

            trades.get(2).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 16), new ItemStack(EunithiceBlocks.BOUNCE_INFUSED_ASPHALT.get(), 4), 32, 12, 0.02F));

            trades.get(2).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 16), new ItemStack(EunithiceBlocks.HONEY_INFUSED_ASPHALT.get(), 4), 32, 9, 0.02F));
        }
    }

    @SubscribeEvent
    public static void playerRightClickWithItem(PlayerInteractEvent.RightClickItem event) {
        ItemStack stack = event.getItemStack();
        int level = EnchantmentHelper.getItemEnchantmentLevel(EunithiceEnchantments.TELEPORTITIS.get(), stack);

        if (level > 0) {
            event.setCancellationResult(InteractionResult.CONSUME); // *should* make the item dip down in your hand when you teleport, but doesn't for some reason
            Player player = event.getPlayer();
            Vec3 raycast = getPlayerPOVHitPosition(player.getLevel(), player, ClipContext.Fluid.NONE, level + 1);
            if (!player.getCommandSenderWorld().isClientSide()) {
                if (!player.isCreative())
                    stack.hurt(Math.max((level - 1) * 2 + player.getRandom().nextInt(3), 1), player.getRandom(), (ServerPlayer) player);
                player.resetFallDistance();
                if (player instanceof ServerPlayer sp)
                    sp.connection.aboveGroundTickCount = 0; // Prevent kick for "flying"
            }
            player.teleportTo(raycast.x, raycast.y, raycast.z); // Run on both client and server so there isn't position desync
            player.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 1, (player.getRandom().nextFloat() - player.getRandom().nextFloat()) * 0.1F + 1.0F);
            event.setCanceled(true);
        }
    }

    private static Vec3 getPlayerPOVHitPosition(Level pLevel, Player pPlayer, ClipContext.Fluid pFluidMode, double distance) {
        // Copied from Item
        float f = pPlayer.getXRot();
        float f1 = pPlayer.getYRot();
        Vec3 vec3 = pPlayer.getEyePosition();
        float f2 = Mth.cos(-f1 * ((float) Math.PI / 180F) - (float) Math.PI);
        float f3 = Mth.sin(-f1 * ((float) Math.PI / 180F) - (float) Math.PI);
        float f4 = -Mth.cos(-f * ((float) Math.PI / 180F));
        float f5 = Mth.sin(-f * ((float) Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        Vec3 vec31 = vec3.add((double) f6 * distance, (double) f5 * distance, (double) f7 * distance);
        BlockHitResult hit = pLevel.clip(new ClipContext(vec3, vec31, ClipContext.Block.COLLIDER, pFluidMode, pPlayer));

        // My Additions
        hit.getBlockPos().relative(hit.getDirection().getOpposite());
        Vec3 semiFinal = new Vec3(hit.getBlockPos().relative(hit.getDirection()).getX() + 0.5, hit.getBlockPos().relative(hit.getDirection()).getY(), hit.getBlockPos().relative(hit.getDirection()).getZ() + 0.5);
        BlockPos semiFinalPos = new BlockPos(semiFinal.x, semiFinal.y, semiFinal.z);
        BlockState stateAbove = pLevel.getBlockState(semiFinalPos.above());
        BlockState stateBelow = pLevel.getBlockState(semiFinalPos.below());
        if (stateAbove.isAir() || !stateAbove.getFluidState().isEmpty()) return semiFinal;
        else return semiFinal.add(0, -1, 0);
    }

}