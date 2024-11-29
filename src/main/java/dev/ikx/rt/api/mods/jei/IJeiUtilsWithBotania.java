package dev.ikx.rt.api.mods.jei;

import dev.ikx.rt.api.mods.jei.elements.IJeiElements;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import dev.ikx.rt.impl.mods.jei.impl.elemenet.MCJeiElementManaBar;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethodStatic;


@SidedZenRegister(modDeps = {"jei", "botania"})
@ZenExpansion("mods.randomtweaker.jei.IJeiUtils")
public abstract class IJeiUtilsWithBotania {

    @ZenMethodStatic
    public static IJeiElements.IJeiElementManaBar createJeiManaBarElement(int x, int y, int mana, @Optional int mode) {
        return new MCJeiElementManaBar(x, y, mode, mana);
    }

}
