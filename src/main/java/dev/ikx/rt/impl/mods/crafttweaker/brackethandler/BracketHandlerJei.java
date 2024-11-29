package dev.ikx.rt.impl.mods.crafttweaker.brackethandler;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.BracketHandler;
import youyihj.zenutils.api.zenscript.SidedZenRegister;
import crafttweaker.zenscript.IBracketHandler;
import dev.ikx.rt.Main;
import dev.ikx.rt.api.mods.jei.core.IJeiPanel;

import dev.ikx.rt.impl.mods.jei.impl.core.MCJeiPanel;
import stanhebben.zenscript.compiler.IEnvironmentGlobal;
import stanhebben.zenscript.expression.ExpressionCallStatic;
import stanhebben.zenscript.expression.ExpressionString;
import stanhebben.zenscript.parser.Token;
import stanhebben.zenscript.symbols.IZenSymbol;
import stanhebben.zenscript.type.natives.IJavaMethod;

import java.util.List;

@SidedZenRegister(modDeps = "jei")
@BracketHandler(priority = 100)

public class BracketHandlerJei implements IBracketHandler {

    public static IJeiPanel getJEIPanel(String name) {
        return Main.JEI_PANEL_SET.stream().filter(p -> ((MCJeiPanel) p).uid.equals(name)).findFirst().orElse(null);
    }

    @Override
    public IZenSymbol resolve(IEnvironmentGlobal environment, List<Token> tokens) {
        if (tokens.size() > 2) {
            if (tokens.get(0).getValue().equals("jei") && tokens.get(1).getValue().equals(":")) {
                return find(environment, tokens);
            }
        }
        return null;
    }

    @Override
    public String getRegexMatchingString() {
        return "jei:.*";
    }

    @Override
    public Class<?> getReturnedClass() {
        return IJeiPanel.class;
    }

    private IZenSymbol find(IEnvironmentGlobal environment, List<Token> tokens) {
        String name = tokens.get(2).getValue();
        if (getJEIPanel(name) != null) {
            IJavaMethod method = CraftTweakerAPI.getJavaMethod(BracketHandlerJei.class, "getJEIPanel", String.class);
            return position -> new ExpressionCallStatic(position, environment, method, new ExpressionString(position, name));
        }
        return null;
    }

}
