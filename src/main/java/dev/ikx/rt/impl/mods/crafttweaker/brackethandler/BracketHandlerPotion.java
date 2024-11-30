package dev.ikx.rt.impl.mods.crafttweaker.brackethandler;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.BracketHandler;
import crafttweaker.zenscript.IBracketHandler;
import dev.ikx.rt.api.mods.contenttweaker.potion.CTPotionRepresentation;
import dev.ikx.rt.api.mods.contenttweaker.potion.IPotionRepresentation;
import dev.ikx.rt.impl.internal.event.EventRegister;
import stanhebben.zenscript.compiler.IEnvironmentGlobal;
import stanhebben.zenscript.expression.ExpressionCallStatic;
import stanhebben.zenscript.expression.ExpressionString;
import stanhebben.zenscript.parser.Token;
import stanhebben.zenscript.symbols.IZenSymbol;
import stanhebben.zenscript.type.natives.IJavaMethod;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import java.util.List;

@SuppressWarnings("ALL")
@SidedZenRegister(modDeps = "contenttweaker")
@BracketHandler(priority = 100)

public class BracketHandlerPotion implements IBracketHandler {

    public static CTPotionRepresentation getPotion(String name) {
        if (EventRegister.POTION_MAP.containsKey(name)) {
            return EventRegister.POTION_MAP.get(name).potionRepresentation;
        }
        return null;
    }

    @Override
    public IZenSymbol resolve(IEnvironmentGlobal environment, List<Token> tokens) {
        if (tokens.size() > 2) {
            if (tokens.get(0).getValue().equals("cotPotion") && tokens.get(1).getValue().equals(":")) {
                return find(environment, tokens);
            }
        }
        return null;
    }

    @Override
    public String getRegexMatchingString() {
        return "cotPotion:.*";
    }

    @Override
    public Class<?> getReturnedClass() {
        return IPotionRepresentation.class;
    }

    private IZenSymbol find(IEnvironmentGlobal environment, List<Token> tokens) {
        String name = tokens.get(2).getValue();
        IJavaMethod method;
        if (getPotion(name) != null) {
            return position -> new ExpressionCallStatic(position, environment, CraftTweakerAPI.getJavaMethod(BracketHandlerPotion.class, "getPotion", String.class), new ExpressionString(position, name));
        }
        return null;
    }

}
