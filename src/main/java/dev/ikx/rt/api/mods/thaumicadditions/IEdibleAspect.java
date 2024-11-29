package dev.ikx.rt.api.mods.thaumicadditions;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import youyihj.zenutils.api.zenscript.SidedZenRegister;
import crafttweaker.api.entity.IEntityLivingBase;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.potions.IPotionEffect;
import dev.ikx.rt.api.mods.thaumcraft.IAspect;
import dev.ikx.rt.api.mods.thaumcraft.IAspectList;

import org.zeith.thaumicadditions.api.EdibleAspect;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;


@SidedZenRegister(modDeps = "thaumadditions")
@ZenClass("mods.randomtweaker.thaumadditions.IEdibleAspect")
public abstract class IEdibleAspect {

    @ZenMethod
    public static void addEatCall(IAspect aspect, EatFunction function) {
        CraftTweakerAPI.apply(new IAction() {
            @Override public void apply() {
                EdibleAspect.addEatCall(aspect.getInternal(), (entity, count) -> {
                    entity.addPotionEffect(CraftTweakerMC.getPotionEffect(function.apply(count)));
                    return true;
                });
            }

            @Override public String describe() {
                return "Adding EatCall -> " + aspect.getName();
            }
        });
    }

    @ZenMethod
    public static void addAdvancedEatCall(IAspect aspect, EatFunctionWithEntity function) {
        CraftTweakerAPI.apply(new IAction() {
            @Override public void apply() {
                EdibleAspect.addEatCall(aspect.getInternal(), (entity, count) -> function.apply(CraftTweakerMC.getIEntityLivingBase(entity), count));
            }

            @Override public String describe() {
                return "Adding EatCall -> " + aspect.getName();
            }
        });
    }

    @ZenMethod
    public static void removeEatCall(IAspect aspect) {
        CraftTweakerAPI.apply(new IAction() {
            @Override public void apply() {
                EdibleAspect.EAT_FUNCTIONS.entrySet().removeIf(entry -> entry.getKey().getTag().equals(aspect.getInternal().getTag()));
            }

            @Override public String describe() {
                return "Removing EatCall -> " + aspect.getName();
            }
        });
    }

    @ZenMethod
    public static IItemStack applyToFoodStack(IItemStack stack, IAspectList aspects) {
        return CraftTweakerMC.getIItemStack(EdibleAspect.applyToFoodStack(CraftTweakerMC.getItemStack(stack), aspects.getInternal()));
    }

    @ZenMethod
    public static IAspectList getSalt(IItemStack stack) {
        return IAspectList.of(EdibleAspect.getSalt(CraftTweakerMC.getItemStack(stack)));
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
