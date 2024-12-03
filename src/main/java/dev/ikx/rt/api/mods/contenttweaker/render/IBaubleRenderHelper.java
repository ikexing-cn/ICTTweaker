package dev.ikx.rt.api.mods.contenttweaker.render;

import crafttweaker.api.item.IItemStack;
import crafttweaker.api.player.IPlayer;
import dev.ikx.rt.impl.internal.compact.mods.DeprecatedCompact;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

/**
 * @author superhelo
 */

@Deprecated
@SidedZenRegister(modDeps = {"contenttweaker", "botania"})
@ZenClass(IBaubleRenderHelper.ZEN_CLASS)
public class IBaubleRenderHelper {

    public static final String ZEN_CLASS = "mods.randomtweaker.render.IBaubleRenderHelper";
    public static DeprecatedCompact compact = new DeprecatedCompact(ZEN_CLASS, BaubleRenderHelper.ZEN_CLASS);

    @ZenMethod
    public static void bindTexture(String resourceLocation) {
        compact.callVoid(() -> BaubleRenderHelper.bindTexture(resourceLocation));
    }

    @ZenMethod
    public static void renderItem(IItemStack renderStack, @Optional(value = "NONE") String transformType) {
        compact.callVoid(() -> BaubleRenderHelper.renderItem(renderStack, transformType));
    }

    @ZenMethod
    public static void rotateIfSneaking(IPlayer player) {
        compact.callVoid(() -> BaubleRenderHelper.rotateIfSneaking(player));
    }

    @ZenMethod
    public static void translateToHeadLevel(IPlayer player) {
        compact.callVoid(() -> BaubleRenderHelper.translateToHeadLevel(player));
    }

    @ZenMethod
    public static void translateToFace() {
        compact.callVoid(BaubleRenderHelper::translateToFace);
    }

    @ZenMethod
    public static void defaultTransforms() {
        compact.callVoid(BaubleRenderHelper::defaultTransforms);
    }

    @ZenMethod
    public static void scale(double x, double y, double z) {
        compact.callVoid(() -> BaubleRenderHelper.scale(x, y, z));
    }

    @ZenMethod
    public static void rotate(float angle, float x, float y, float z) {
        compact.callVoid(() -> BaubleRenderHelper.rotate(angle, x, y, z));
    }

    @ZenMethod
    public static void translate(double x, double y, double z) {
        compact.callVoid(() -> BaubleRenderHelper.translate(x, y, z));
    }

    @ZenMethod
    public static void translateToChest() {
        compact.callVoid(BaubleRenderHelper::translateToChest);
    }

}
