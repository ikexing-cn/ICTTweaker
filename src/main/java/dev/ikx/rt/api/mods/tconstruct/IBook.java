package dev.ikx.rt.api.mods.tconstruct;

import com.google.common.base.Preconditions;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import dev.ikx.rt.Main;
import dev.ikx.rt.impl.internal.config.RTConfig;
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
@ZenClass("mods.randomtweaker.tconstruct.IBook")
public abstract class IBook {

    @ZenMethod
    public static void addHiddenMaterial(String material) {
        checkConfig();
        CraftTweakerAPI.apply(new AddHiddenMaterialAction(material));
    }

    @ZenMethod
    public static void changeMaterialItem(String material, IItemStack item) {
        checkConfig();
        CraftTweakerAPI.apply(new ChangeMaterialItem(material, item));
    }

    @ZenMethod
    public static void setMaterialPriority(String material, int priority) {
        checkConfig();
        CraftTweakerAPI.apply(new SetMaterialPriority(material, priority));
    }

    private static void checkConfig() {
        Preconditions.checkArgument(RTConfig.Tconstruct.iconModification, "Please change Tconstruct iconModification config to true.");
    }

    public static class AddHiddenMaterialAction implements IAction {

        private final String material;

        public AddHiddenMaterialAction(String material) {
            this.material = material;
        }

        @Override
        public void apply() {
            Main.HIDDEN_MATERIAL_LIST.add(this.material);
        }

        @Override
        public String describe() {
            return "Adding material " + this.material + " to hidden materials";
        }

        @Override
        public boolean validate() {
            return RTConfig.Tconstruct.iconModification && !Main.HIDDEN_MATERIAL_LIST.contains(this.material);
        }

        @Override
        public String describeInvalid() {
            if (!RTConfig.Tconstruct.iconModification) {
                return "Change \"iconModification\" to true in the configuration file";
            }
            return "Material " + this.material + " is already exists in hidden materials";
        }
    }

    public static class ChangeMaterialItem implements IAction {

        private final String material;
        private final IItemStack item;

        public ChangeMaterialItem(String material, IItemStack item) {
            this.material = material;
            this.item = item;
        }

        @Override
        public void apply() {
            Main.MATERIAL_SHOW_ITEM_MAP.put(this.material, CraftTweakerMC.getItemStack(this.item));
        }

        @Override
        public String describe() {
            return "Changing material " + this.material + " to item " + this.item.toCommandString();
        }

        @Override
        public boolean validate() {
            return RTConfig.Tconstruct.iconModification && !Main.MATERIAL_SHOW_ITEM_MAP.containsKey(this.material);
        }

        @Override
        public String describeInvalid() {
            if (!RTConfig.Tconstruct.iconModification) {
                return "Change \"iconModification\" to true in the configuration file";
            }
            return "Material " + this.material + " is already changed";
        }
    }

    public static class SetMaterialPriority implements IAction {

        private final String material;
        private final int priority;

        public SetMaterialPriority(String material, int priority) {
            this.material = material;
            this.priority = priority;
        }

        @Override
        public void apply() {
            Main.MATERIAL_PRIORITY_MAP.put(this.material, this.priority);
        }

        @Override
        public String describe() {
            return "Setting material " + this.material + " priority to " + this.priority;
        }

        @Override
        public boolean validate() {
            return RTConfig.Tconstruct.iconModification && !Main.MATERIAL_PRIORITY_MAP.containsKey(this.material);
        }

        @Override
        public String describeInvalid() {
            if (!RTConfig.Tconstruct.iconModification) {
                return "Change \"iconModification\" to true in the configuration file";
            }
            return "Material " + this.material + " is already set priority";
        }
    }

}
