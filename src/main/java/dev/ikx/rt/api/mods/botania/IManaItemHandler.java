package dev.ikx.rt.api.mods.botania;

import crafttweaker.api.item.IItemStack;
import crafttweaker.api.player.IPlayer;
import dev.ikx.rt.impl.internal.compact.mods.DeprecatedCompact;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import java.util.Map;

@Deprecated
@SidedZenRegister(modDeps = "botania")
@ZenClass(IManaItemHandler.ZEN_CLASS)
public abstract class IManaItemHandler {

    public static final String ZEN_CLASS = "mods.randomtweaker.botania.IManaItemHandler";
    public static DeprecatedCompact compact = new DeprecatedCompact(ZEN_CLASS, CTManaItemHandler.ZEN_CLASS);

    @ZenMethod
    public static IItemStack[] getManaItems(IPlayer player) {
        return compact.call(() -> CTManaItemHandler.getManaItems(player));
    }

    @ZenMethod
    public static Map<Integer, IItemStack> getManaBaubles(IPlayer player) {
        return compact.call(() -> CTManaItemHandler.getManaBaubles(player));
    }

    @ZenMethod
    public static int requestMana(IItemStack stack, IPlayer player, int manaToGet, boolean remove) {
        return compact.call(() -> CTManaItemHandler.requestMana(stack, player, manaToGet, remove));
    }

    @ZenMethod
    public static boolean requestManaExact(IItemStack stack, IPlayer player, int manaToGet, boolean remove) {
        return compact.call(() -> CTManaItemHandler.requestManaExact(stack, player, manaToGet, remove));
    }

    @ZenMethod
    public static int requestManaForTool(IItemStack stack, IPlayer player, int manaToGet, boolean remove) {
        return compact.call(() -> CTManaItemHandler.requestManaForTool(stack, player, manaToGet, remove));
    }

    @ZenMethod
    public static boolean requestManaExactForTool(IItemStack stack, IPlayer player, int manaToGet, boolean remove) {
        return compact.call(() -> CTManaItemHandler.requestManaExactForTool(stack, player, manaToGet, remove));
    }

    @ZenMethod
    public static int dispatchMana(IItemStack stack, IPlayer player, int manaToSend, boolean add) {
        return compact.call(() -> CTManaItemHandler.dispatchMana(stack, player, manaToSend, add));
    }

    @ZenMethod
    public static boolean dispatchManaExact(IItemStack stack, IPlayer player, int manaToSend, boolean add) {
        return compact.call(() -> CTManaItemHandler.dispatchManaExact(stack, player, manaToSend, add));
    }

    @ZenMethod
    public static float getFullDiscountForTools(IPlayer player, IItemStack tool) {
        return compact.call(() -> CTManaItemHandler.getFullDiscountForTools(player, tool));
    }

}
