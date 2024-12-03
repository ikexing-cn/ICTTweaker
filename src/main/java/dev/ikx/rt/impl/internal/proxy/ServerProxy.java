package dev.ikx.rt.impl.internal.proxy;

import crafttweaker.api.item.IItemStack;
import crafttweaker.api.player.IPlayer;
import dev.ikx.rt.api.mods.contenttweaker.function.mana.IBaubleRender;

/**
 * @author superhelo
 */
public class ServerProxy implements IProxy {

    @Override
    public void onPlayerBaubleRender(IBaubleRender render, IItemStack stack, IPlayer player, String renderType, float partialTicks) {
    }

    @Override
    public void rotateIfSneaking(IPlayer player) {
    }

    @Override
    public void translateToHeadLevel(IPlayer player) {
    }

    @Override
    public void translateToFace() {
    }

    @Override
    public void defaultTransforms() {
    }

    @Override
    public void translateToChest() {
    }

    @Override
    public void renderItem(IItemStack renderStack, String transformType) {
    }

    @Override
    public void bindTexture(String resourceLocation) {
    }

    @Override
    public void scale(double x, double y, double z) {
    }

    @Override
    public void rotate(float angle, float x, float y, float z) {
    }

    @Override
    public void translate(double x, double y, double z) {
    }

}
