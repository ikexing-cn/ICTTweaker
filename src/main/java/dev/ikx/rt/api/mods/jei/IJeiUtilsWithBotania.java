package dev.ikx.rt.api.mods.jei;

import dev.ikx.rt.api.mods.jei.elements.JEIElements;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethodStatic;
import youyihj.zenutils.api.zenscript.SidedZenRegister;


@SidedZenRegister(modDeps = {"jei", "botania"})
@ZenExpansion("mods.randomtweaker.jei.JEIUtils")
public class IJeiUtilsWithBotania {

    @ZenMethodStatic
    public static JEIElements.JEIElementManaBar createJEIManaBarElement(int x, int y, int mana, @Optional int mode) {
        return new JEIElements.JEIElementManaBar(x, y, mode, mana);
    }

    @ZenMethodStatic
    public static JEIElements.JEIElementManaBar createJEIManaBarElement(String elementName, int x, int y, int mana, @Optional int mode) {
        return new JEIElements.JEIElementManaBar(elementName, x, y, mode, mana);
    }

}
