package ink.ikx.rt.api.mods.jei;

import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import ink.ikx.rt.impl.mods.jei.JeiPlugin;
import youyihj.zenutils.api.zenscript.SidedZenRegister;
import ink.ikx.rt.api.mods.jei.core.IJeiPanel;
import ink.ikx.rt.api.mods.jei.core.IJeiRecipe;
import net.minecraft.item.ItemStack;

import ink.ikx.rt.impl.mods.jei.impl.core.MCJeiPanel;
import ink.ikx.rt.impl.mods.jei.impl.core.MCJeiRecipe;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethodStatic;

import java.util.Objects;

@SidedZenRegister(modDeps = "jei")
@ZenExpansion("mods.jei.JEI")
public abstract class JEIExpansion {

    @ZenMethodStatic
    public static IJeiPanel createJei(String uid, String title) {
        return new MCJeiPanel(uid, title);
    }

    @ZenMethodStatic
    public static IJeiRecipe createJeiRecipe(String uid) {
        return new MCJeiRecipe(uid);
    }

    @ZenMethodStatic
    public static void addItemNBTSubtype(IItemStack stack) {
        ItemStack mcStack = CraftTweakerMC.getItemStack(stack);
        Objects.requireNonNull(mcStack.getItem());
        JeiPlugin.subtypesToRegister.add(mcStack.getItem());
    }
}
