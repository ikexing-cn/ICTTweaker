package dev.ikx.rt.api.mods.thaumcraft.expand;

import youyihj.zenutils.api.zenscript.SidedZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import dev.ikx.rt.api.mods.thaumcraft.IAspectList;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethod;
import thaumcraft.api.aspects.AspectHelper;


@SidedZenRegister(modDeps = "thaumcraft")
@ZenExpansion("crafttweaker.item.IItemStack")
@ZenClass("mods.randomtweaker.thaumcraft.IItemStack")
public abstract class ExpandIItemStack {

    @ZenMethod
    public static IAspectList getAspects(IItemStack stack) {
        return IAspectList.of(AspectHelper.getObjectAspects(CraftTweakerMC.getItemStack(stack)).copy());
    }

}
