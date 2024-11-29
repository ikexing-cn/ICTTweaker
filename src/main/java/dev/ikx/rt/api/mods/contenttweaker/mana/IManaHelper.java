package dev.ikx.rt.api.mods.contenttweaker.mana;

import baubles.api.IBauble;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.item.IMutableItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import dev.ikx.rt.impl.mods.contenttweaker.mana.bauble.MCManaBauble;
import dev.ikx.rt.impl.mods.contenttweaker.mana.item.MCManaItem;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import javax.annotation.Nullable;
import net.minecraft.item.Item;
import stanhebben.zenscript.annotations.ZenCaster;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * @author superhelo
 */

@SidedZenRegister(modDeps = {"contenttweaker", "botania"})
@ZenExpansion("crafttweaker.item.IItemStack")
@ZenClass("mods.randomtweaker.botania.IItemStack")
public abstract class IManaHelper {

    @ZenMethod
    public static boolean isIManaItem(IItemStack stack) {
        return CraftTweakerMC.getItemStack(stack).getItem() instanceof vazkii.botania.api.mana.IManaItem;
    }

    @ZenMethod
    public static boolean isIManaBauble(IItemStack stack) {
        Item item = CraftTweakerMC.getItemStack(stack).getItem();
        return item instanceof vazkii.botania.api.mana.IManaItem && item instanceof IBauble;
    }

    @Nullable
    @ZenCaster
    @ZenMethod
    public static IManaItem asIManaItem(IItemStack stack) {
        if (stack instanceof IMutableItemStack) {
            return MCManaItem.create(CraftTweakerMC.getItemStack((IMutableItemStack) stack));
        }
        return MCManaItem.create(CraftTweakerMC.getItemStack(stack));
    }

    @Nullable
    @ZenCaster
    @ZenMethod
    public static IManaBauble asIManaBauble(IItemStack stack) {
        if (stack instanceof IMutableItemStack) {
            return MCManaBauble.create(CraftTweakerMC.getItemStack((IMutableItemStack) stack));
        }
        return MCManaBauble.create(CraftTweakerMC.getItemStack(stack));
    }

}
