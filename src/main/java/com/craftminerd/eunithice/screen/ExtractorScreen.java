package com.craftminerd.eunithice.screen;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.screen.renderer.FluidTankRenderer;
import com.craftminerd.eunithice.util.MouseUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Optional;

@OnlyIn(Dist.CLIENT)
public class ExtractorScreen extends AbstractContainerScreen<ExtractorMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Eunithice.MODID, "textures/gui/extractor_gui.png");
    private FluidTankRenderer renderer;

    public ExtractorScreen(ExtractorMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        assignFluidRenderer();
    }

    private void assignFluidRenderer() {
        renderer = new FluidTankRenderer(4000, true, 16, 53);
    }

    protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.font.draw(pPoseStack, this.title, (float)this.titleLabelX, (float)this.titleLabelY, 4210752);
        this.font.draw(pPoseStack, this.playerInventoryTitle, (float)this.inventoryLabelX, (float)this.inventoryLabelY, 4210752);
        renderFluidAreaTooltips(pPoseStack, pMouseX, pMouseY, x, y);
    }

    private void renderFluidAreaTooltips(PoseStack pPoseStack, int pMouseX, int pMouseY, int x, int y) {
        if (isMouseAboveArea(pMouseX, pMouseY, x, y, 42, 17)) {
            renderTooltip(pPoseStack, renderer.getTooltip(menu.getFluidStack(), TooltipFlag.Default.NORMAL),
                    Optional.empty(), pMouseX - x, pMouseY - y);
        }
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
        RenderSystem.setShaderTexture(0,TEXTURE);
        int x = (width-imageWidth)/2;
        int y = (height-imageHeight)/2;

        this.blit(pPoseStack, x, y,0,0, imageWidth, imageHeight);

        renderProgress(pPoseStack, x, y);
        renderer.render(pPoseStack, x + 42, y + 17, menu.getFluidStack());

    }

    private void renderProgress(PoseStack poseStack, int x, int y) {
        if (menu.isCrafting()) {
            blit(poseStack, x + 89, y + 39, 176, 0, menu.getScaledProgress(), 16);
        }
    }

    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack,mouseX,mouseY,delta);
        renderTooltip(pPoseStack,mouseX,mouseY);
    }

    private boolean isMouseAboveArea(int mouseX, int mouseY, int x, int y, int offsetX, int offsetY) {
        return MouseUtil.isMouseOver(mouseX, mouseY, x + offsetX, y + offsetY, renderer.getWidth(), renderer.getHeight());
    }
}