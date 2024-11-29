package dev.ikx.rt.api.mods.contenttweaker.render;

import crafttweaker.api.item.IItemStack;
import crafttweaker.api.player.IPlayer;
import dev.ikx.rt.Main;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * @author superhelo
 */

@SidedZenRegister(modDeps = {"contenttweaker", "botania"})
@ZenClass("mods.randomtweaker.render.IBaubleRenderHelper")
public abstract class IBaubleRenderHelper {

    @ZenMethod
    public static void bindTexture(String resourceLocation) {
        Main.proxy.bindTexture(resourceLocation);
    }

    @ZenMethod
    public static void renderItem(IItemStack renderStack, @Optional(value = "NONE") String transformType) {
        Main.proxy.renderItem(renderStack, transformType);
    }

    @ZenMethod
    public static void rotateIfSneaking(IPlayer player) {
        Main.proxy.rotateIfSneaking(player);
    }

    @ZenMethod
    public static void translateToHeadLevel(IPlayer player) {
        Main.proxy.translateToHeadLevel(player);
    }

    @ZenMethod
    public static void translateToFace() {
        Main.proxy.translateToFace();
    }

    @ZenMethod
    public static void defaultTransforms() {
        Main.proxy.defaultTransforms();
    }

    @ZenMethod
    public static void scale(double x, double y, double z) {
        Main.proxy.scale(x, y, z);
    }

    @ZenMethod
    public static void rotate(float angle, float x, float y, float z) {
        Main.proxy.rotate(angle, x, y, z);
    }

    @ZenMethod
    public static void translate(double x, double y, double z) {
        Main.proxy.translate(x, y, z);
    }

    @ZenMethod
    public static void translateToChest() {
        Main.proxy.translateToChest();
    }

}
