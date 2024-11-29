package dev.ikx.rt.impl.internal.proxy;

import crafttweaker.api.item.IItemStack;
import crafttweaker.api.player.IPlayer;
import dev.ikx.rt.api.mods.contenttweaker.function.mana.IBaubleRender;

/**
 * @author superhelo
 */
public interface IProxy {

    void onPlayerBaubleRender(IBaubleRender render, IItemStack stack, IPlayer player, String renderType, float partialTicks);

    void rotateIfSneaking(IPlayer player);

    void translateToHeadLevel(IPlayer player);

    void translateToFace();

    void defaultTransforms();

    void translateToChest();

    void renderItem(IItemStack renderStack, String transformType);

    void bindTexture(String resourceLocation);

    void scale(double x, double y, double z);

    void rotate(float angle, float x, float y, float z);

    void translate(double x, double y, double z);

}
