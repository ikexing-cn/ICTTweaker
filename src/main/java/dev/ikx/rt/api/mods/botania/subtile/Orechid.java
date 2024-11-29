package dev.ikx.rt.api.mods.botania.subtile;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.oredict.IOreDictEntry;
import crafttweaker.mc1120.brackets.BracketHandlerOre;
import dev.ikx.rt.impl.internal.utils.InternalUtils;
import dev.ikx.rt.impl.mods.botania.module.SubTileOrechidManager;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import youyihj.zenutils.api.reload.Reloadable;
import youyihj.zenutils.api.util.ReflectionInvoked;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SidedZenRegister(modDeps = "botania")
@ZenClass(Orechid.ZEN_CLASS)
public abstract class Orechid {

    public static final String ZEN_CLASS = "mods.randomtweaker.botania.Orechid";

    @ZenMethod
    public static void addOreRecipe(IItemStack block, IOreDictEntry ore, int weight) {
        CraftTweakerAPI.apply(new ActionAddOrechidRecipe(block, ore, weight));
    }

    @ZenMethod
    public static void delOreRecipe(IItemStack block) {
        for (IOreDictEntry ore : getOreRecipes(block)) {
            delOreRecipe(block, ore);
        }
    }

    @ZenMethod
    public static void delOreRecipe(IItemStack block, IOreDictEntry ore) {
        CraftTweakerAPI.apply(new ActionRemoveOrechidRecipe(block, ore));
    }

    @ZenMethod
    public static IOreDictEntry[] getOreRecipes(IItemStack block) {
        return Arrays.stream(SubTileOrechidManager.getOres(InternalUtils.getStateFromStack(block)))
                .map(BracketHandlerOre::getOre)
                .filter(o -> !o.isEmpty())
                .toArray(IOreDictEntry[]::new);
    }

    @Reloadable
    private static class ActionAddOrechidRecipe implements IAction {

        private final int weight;
        private final IItemStack block;
        private final IOreDictEntry ore;

        public ActionAddOrechidRecipe(IItemStack block, IOreDictEntry ore, int weight) {
            this.ore = ore;
            this.block = block;
            this.weight = weight;
        }

        @ReflectionInvoked
        public void undo() {
            SubTileOrechidManager.delOre(InternalUtils.getStateFromStack(block), ore.getName());
        }

        @Override
        public void apply() {
            SubTileOrechidManager.addOreWeight(InternalUtils.getStateFromStack(block), ore.getName(), weight);
        }

        @Override
        public String describe() {
            return "Adding Orechid Recipe for block: " + block.getDisplayName() + " -> " + ore.getName() + ", weight: " + weight;
        }

        @Override
        public boolean validate() {
            return Objects.nonNull(InternalUtils.getStateFromStack(block)) && !ore.isEmpty();
        }

        @Override
        public String describeInvalid() {
            return "The IItemStack in Orechid recipe is not a block, or the IOreDictEntry is empty.";
        }

    }


    @Reloadable
    private static class ActionRemoveOrechidRecipe implements IAction {

        private final IItemStack block;
        private final IOreDictEntry ore;
        private String describe;

        private List<Integer> _weights;

        public ActionRemoveOrechidRecipe(IItemStack block, IOreDictEntry ore) {
            this.ore = ore;
            this.block = block;
        }

        @ReflectionInvoked
        public void undo() {
            _weights.forEach(weight -> SubTileOrechidManager.addOreWeight(InternalUtils.getStateFromStack(block), ore.getName(), weight));
        }

        @Override
        public void apply() {
            _weights = SubTileOrechidManager.delOre(InternalUtils.getStateFromStack(block), ore.getName());
        }

        @Override
        public String describe() {
            return "Removing IOrechid Recipe for block: " + block.getDisplayName() + " -> " + ore.getName();
        }

        @Override
        public boolean validate() {
            if (Objects.isNull(InternalUtils.getStateFromStack(block))) {
                describe = "The IItemStack is not a block.";
                return false;
            } else if (!SubTileOrechidManager.checkOreExist(InternalUtils.getStateFromStack(block), ore.getName())) {
                describe = "The IOrechid Recipe not exist.";
                return false;
            }
            return true;
        }

        @Override
        public String describeInvalid() {
            return describe;
        }

    }

}
