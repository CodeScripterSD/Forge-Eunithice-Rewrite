package com.craftminerd.eunithice.screen;

import com.craftminerd.eunithice.Eunithice;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class AsphaltInfuserScreen extends AbstractContainerScreen<AsphaltInfuserMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Eunithice.MODID, "textures/gui/asphalt_infuser_gui.png");

    public AsphaltInfuserScreen(AsphaltInfuserMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
        RenderSystem.setShaderTexture(0,TEXTURE);
        int x = (width-imageWidth)/2;
        int y = (height-imageHeight)/2;

        this.blit(pPoseStack,x,y,0,0,imageWidth,imageHeight);

        if (menu.isCrafting()) {
            blit(pPoseStack, x + 90, y + 32, 176, 0, 8, menu.getScaledProgress());
        }

    }

    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack,mouseX,mouseY,delta);
        renderTooltip(pPoseStack,mouseX,mouseY);
    }
}