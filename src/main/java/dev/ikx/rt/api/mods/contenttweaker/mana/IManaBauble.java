package dev.ikx.rt.api.mods.contenttweaker.mana;

import crafttweaker.api.entity.IEntityLivingBase;
import crafttweaker.api.item.IItemStack;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * @author superhelo
 */
@SidedZenRegister(modDeps = {"contenttweaker", "botania"})
@ZenClass("mods.randomtweaker.item.IManaBauble")
public interface IManaBauble extends IManaItem {

    @ZenGetter("baubleType")
    String getBaubleType();

    @ZenMethod
    boolean canEquip(IItemStack baubleItem, IEntityLivingBase wearer);

    @ZenMethod
    boolean canUnEquip(IItemStack baubleItem, IEntityLivingBase wearer);

}
