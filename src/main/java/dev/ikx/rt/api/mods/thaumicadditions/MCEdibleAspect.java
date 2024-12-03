package dev.ikx.rt.api.mods.thaumicadditions;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.entity.IEntityLivingBase;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.potions.IPotionEffect;
import dev.ikx.rt.api.mods.thaumcraft.IAspect;
import dev.ikx.rt.api.mods.thaumcraft.IAspectList;
import net.minecraft.entity.EntityLivingBase;
import org.zeith.thaumicadditions.api.EdibleAspect;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import youyihj.zenutils.api.reload.Reloadable;
import youyihj.zenutils.api.util.ReflectionInvoked;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import java.util.function.BiFunction;

@Deprecated
@SidedZenRegister(modDeps = "thaumadditions")
@ZenClass(MCEdibleAspect.ZEN_CLASS)
public class MCEdibleAspect {

    public static final String ZEN_CLASS = "mods.randomtweaker.thaumadditions.EdibleAspect";

    @ZenMethod
    public static void addEatCall(IAspect aspect, EatFunction function) {
        CraftTweakerAPI.apply(new AddEatCall(aspect, function));
    }

    @ZenMethod
    public static void addAdvancedEatCall(IAspect aspect, EatFunctionWithEntity function) {
        CraftTweakerAPI.apply(new AddAdvancedEatCall(aspect, function));
    }

    @ZenMethod
    public static void removeEatCall(IAspect aspect) {
        CraftTweakerAPI.apply(new RemoveEatCall(aspect));
    }

    @ZenMethod
    public static IItemStack applyToFoodStack(IItemStack stack, IAspectList aspects) {
        return CraftTweakerMC.getIItemStack(EdibleAspect.applyToFoodStack(CraftTweakerMC.getItemStack(stack), aspects.getInternal()));
    }

    @ZenMethod
    public static IAspectList getSalt(IItemStack stack) {
        return IAspectList.of(EdibleAspect.getSalt(CraftTweakerMC.getItemStack(stack)));
    }

    private static void RemoveIf(IAspect aspect) {
        EdibleAspect.EAT_FUNCTIONS.entrySet().removeIf(entry -> entry.getKey().getTag().equals(aspect.getInternal().getTag()));
    }

    @Reloadable
    public static class AddEatCall implements IAction {

        private final IAspect aspect;
        private final EatFunction function;

        public AddEatCall(IAspect aspect, EatFunction function) {
            this.aspect = aspect;
            this.function = function;
        }

        @ReflectionInvoked
        public void undo() {
            MCEdibleAspect.RemoveIf(this.aspect);
        }

        @Override
        public void apply() {
            EdibleAspect.addEatCall(this.aspect.getInternal(), (entity, count) -> {
                entity.addPotionEffect(CraftTweakerMC.getPotionEffect(function.apply(count)));
                return true;
            });
        }

        @Override
        public String describe() {
            return "Adding EatCall -> " + this.aspect.getName();
        }

    }

    @Reloadable
    public static class AddAdvancedEatCall implements IAction {

        private final IAspect aspect;
        private final EatFunctionWithEntity function;

        public AddAdvancedEatCall(IAspect aspect, EatFunctionWithEntity function) {
            this.aspect = aspect;
            this.function = function;
        }

        @ReflectionInvoked
        public void undo() {
            MCEdibleAspect.RemoveIf(this.aspect);
        }

        @Override
        public void apply() {
            EdibleAspect.addEatCall(this.aspect.getInternal(), (entity, count) -> function.apply(CraftTweakerMC.getIEntityLivingBase(entity), count));
        }

        @Override
        public String describe() {
            return "Adding EatCall -> " + this.aspect.getName();
        }
    }

    @Reloadable
    public static class RemoveEatCall implements IAction {

        private final IAspect aspect;

        private BiFunction<EntityLivingBase, Integer, Boolean> _function;

        public RemoveEatCall(IAspect aspect) {
            this.aspect = aspect;
        }

        @ReflectionInvoked
        public void undo() {
            EdibleAspect.addEatCall(this.aspect.getInternal(), _function);
        }

        @Override
        public void apply() {
            EdibleAspect.EAT_FUNCTIONS.entrySet().removeIf(entry -> {
                boolean equals = entry.getKey().getTag().equals(aspect.getInternal().getTag());
                if (equals) this._function = entry.getValue();
                return equals;
            });
        }

        @Override
        public String describe() {
            return "Removing EatCall -> " + aspect.getName();
        }
    }

    @SidedZenRegister(modDeps = "thaumadditions")
    @ZenClass("mods.randomtweaker.thaumadditions.EatFunction")
    @FunctionalInterface
    public interface EatFunction {
        IPotionEffect apply(int count);
    }


    @SidedZenRegister(modDeps = "thaumadditions")
    @ZenClass("mods.randomtweaker.thaumadditions.EatFunctionWithEntity")
    @FunctionalInterface
    public interface EatFunctionWithEntity {
        boolean apply(IEntityLivingBase entity, int count);
    }
}
