package dev.ikx.rt.api.mods.tconstruct;

import crafttweaker.api.item.IItemStack;
import dev.ikx.rt.impl.internal.compact.mods.DeprecatedCompact;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

/**
 * Created by IntelliJ IDEA.
 * <p>
 * &#064;Author  : wdcftgg
 * &#064;create  2023/10/3 19:06
 */

@SidedZenRegister(modDeps = "tconstruct")
@ZenClass(IBook.ZEN_CLASS)
@Deprecated
public class IBook {

    public static final String ZEN_CLASS = "mods.randomtweaker.tconstruct.IBook";
    public static DeprecatedCompact compact = new DeprecatedCompact(ZEN_CLASS, Book.ZEN_CLASS);

    @ZenMethod
    public static void addHiddenMaterial(String material) {
        compact.callVoid(() -> Book.addHiddenMaterial(material));
    }

    @ZenMethod
    public static void changeMaterialItem(String material, IItemStack item) {
        compact.callVoid(() -> Book.changeMaterialItem(material, item));
    }

    @ZenMethod
    public static void setMaterialPriority(String material, int priority) {
        compact.callVoid(() -> Book.setMaterialPriority(material, priority));
    }

}
