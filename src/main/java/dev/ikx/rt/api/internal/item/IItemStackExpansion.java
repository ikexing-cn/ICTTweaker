package dev.ikx.rt.api.internal.item;

import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethod;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import java.util.Objects;


@SidedZenRegister
@ZenExpansion("crafttweaker.item.IItemStack")
@ZenClass("mods.randomtweaker.vanilla.IItemStack")
public abstract class IItemStackExpansion {

    @ZenMethod
    public static int getTagSize(IItemStack stack) {
        ItemStack mcStack = CraftTweakerMC.getItemStack(stack);

        return Objects.nonNull(mcStack.getTagCompound()) ? mcStack.getTagCompound().getKeySet().size() : 0;
    }

}
