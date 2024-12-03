package dev.ikx.rt.impl.internal.proxy;

import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.player.IPlayer;
import dev.ikx.rt.api.mods.contenttweaker.function.mana.IBaubleRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.util.ResourceLocation;
import vazkii.botania.api.item.IBaubleRender.Helper;

/**
 * @author superhelo
 */
public class ClientProxy implements IProxy {

    @Override
    public void onPlayerBaubleRender(IBaubleRender render, IItemStack stack, IPlayer player, String renderType, float partialTicks) {
        render.call(stack, player, renderType, partialTicks);
    }

    @Override
    public void rotateIfSneaking(IPlayer player) {
        Helper.rotateIfSneaking(CraftTweakerMC.getPlayer(player));
    }

    @Override
    public void translateToHeadLevel(IPlayer player) {
        Helper.translateToHeadLevel(CraftTweakerMC.getPlayer(player));
    }

    @Override
    public void translateToFace() {
        Helper.translateToFace();
    }

    @Override
    public void defaultTransforms() {
        Helper.defaultTransforms();
    }

    @Override
    public void renderItem(IItemStack renderStack, String transformType) {
        GlStateManager.pushMatrix();
        Minecraft.getMinecraft().getRenderItem().renderItem(CraftTweakerMC.getItemStack(renderStack), ItemCameraTransforms.TransformType.valueOf(transformType));
        GlStateManager.popMatrix();
    }

    @Override
    public void translateToChest() {
        Helper.translateToChest();
    }

    @Override
    public void bindTexture(String resourceLocation) {
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(resourceLocation));
    }

    @Override
    public void scale(double x, double y, double z) {
        GlStateManager.scale(x, y, z);
    }

    @Override
    public void rotate(float angle, float x, float y, float z) {
        GlStateManager.rotate(angle, x, y, z);
    }

    @Override
    public void translate(double x, double y, double z) {
        GlStateManager.translate(x, y, z);
    }

}
