package dev.ikx.rt.impl.mods.crafttweaker.brackethandler;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.BracketHandler;
import crafttweaker.zenscript.IBracketHandler;
import dev.ikx.rt.Main;
import dev.ikx.rt.api.mods.contenttweaker.subtile.ISubTileEntityRepresentation;
import dev.ikx.rt.api.mods.contenttweaker.subtile.functional.ISubTileEntityFunctionalRepresentation;
import dev.ikx.rt.api.mods.contenttweaker.subtile.generating.ISubTileEntityGeneratingRepresentation;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import org.apache.commons.lang3.tuple.Pair;
import stanhebben.zenscript.compiler.IEnvironmentGlobal;
import stanhebben.zenscript.expression.ExpressionCallStatic;
import stanhebben.zenscript.expression.ExpressionString;
import stanhebben.zenscript.parser.Token;
import stanhebben.zenscript.symbols.IZenSymbol;
import stanhebben.zenscript.type.natives.IJavaMethod;

import java.util.List;

@SuppressWarnings("ALL")
@BracketHandler(priority = 100)
@SidedZenRegister(modDeps = {"botania", "contenttweaker"})

public class BracketHandlerSubTile implements IBracketHandler {

    public static ISubTileEntityRepresentation getSubTile(String name) {
        if (Main.SUB_TILE_GENERATING_MAP.containsKey(name)) {
            Pair<String, ISubTileEntityRepresentation> subtilePair = Main.SUB_TILE_GENERATING_MAP.get(name);
            return subtilePair.getValue();
        }
        return null;
    }

    public static ISubTileEntityFunctionalRepresentation getSubTileF(String name) {
        Pair<String, ISubTileEntityRepresentation> subtilePair = Main.SUB_TILE_GENERATING_MAP.get(name);
        return (ISubTileEntityFunctionalRepresentation) subtilePair.getValue();
    }

    public static ISubTileEntityGeneratingRepresentation getSubTileG(String name) {
        Pair<String, ISubTileEntityRepresentation> subtilePair = Main.SUB_TILE_GENERATING_MAP.get(name);
        return (ISubTileEntityGeneratingRepresentation) subtilePair.getValue();
    }

    @Override
    public IZenSymbol resolve(IEnvironmentGlobal environment, List<Token> tokens) {
        if (tokens.size() > 2) {
            if (tokens.get(0).getValue().equals("cotSubTile") && tokens.get(1).getValue().equals(":")) {
                return find(environment, tokens);
            }
        }
        return null;
    }

    @Override
    public String getRegexMatchingString() {
        return "cotSubTile:.*";
    }

    @Override
    public Class<?> getReturnedClass() {
        return ISubTileEntityRepresentation.class;
    }

    private IZenSymbol find(IEnvironmentGlobal environment, List<Token> tokens) {
        String name = tokens.get(2).getValue();
        IJavaMethod method;
        if (getSubTile(name) instanceof ISubTileEntityGeneratingRepresentation) {
            method = CraftTweakerAPI.getJavaMethod(BracketHandlerSubTile.class, "getSubTileG", String.class);
        } else if (getSubTile(name) instanceof ISubTileEntityFunctionalRepresentation) {
            method = CraftTweakerAPI.getJavaMethod(BracketHandlerSubTile.class, "getSubTileF", String.class);
        } else {
            method = null;
        }
        return position -> new ExpressionCallStatic(position, environment, method, new ExpressionString(position, name));
    }

}
