package dev.ikx.rt.api.mods.botania.subtile;

import crafttweaker.api.item.IItemStack;
import crafttweaker.api.oredict.IOreDictEntry;
import dev.ikx.rt.impl.internal.compact.mods.DeprecatedCompact;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

@Deprecated
@SidedZenRegister(modDeps = "botania")
@ZenClass(IOrechid.ZEN_CLASS)
public abstract class IOrechid {

    public static final String ZEN_CLASS = "mods.randomtweaker.botania.IOrechid";
    public static DeprecatedCompact compact = new DeprecatedCompact(ZEN_CLASS, Orechid.ZEN_CLASS);

    @ZenMethod
    public static void addOreRecipe(IItemStack block, IOreDictEntry ore, int weight) {
        compact.callVoid(() -> Orechid.addOreRecipe(block, ore, weight));
    }

    @ZenMethod
    public static void delOreRecipe(IItemStack block) {
       compact.callVoid(() -> Orechid.delOreRecipe(block));
    }

    @ZenMethod
    public static void delOreRecipe(IItemStack block, IOreDictEntry ore) {
       compact.callVoid(() -> Orechid.delOreRecipe(block, ore));
    }

    @ZenMethod
    public static IOreDictEntry[] getOreRecipes(IItemStack block) {
        return compact.call(() -> Orechid.getOreRecipes(block));
    }

}
