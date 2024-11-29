package dev.ikx.rt.impl.internal.clazz.mods.astralsorcery;

import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import hellfirepvp.astralsorcery.common.constellation.IConstellation;
import hellfirepvp.astralsorcery.common.constellation.IWeakConstellation;
import hellfirepvp.astralsorcery.common.item.crystal.CrystalProperties;
import hellfirepvp.astralsorcery.common.item.crystal.CrystalPropertyItem;
import hellfirepvp.astralsorcery.common.item.crystal.base.ItemRockCrystalBase;
import hellfirepvp.astralsorcery.common.item.crystal.base.ItemTunedCrystalBase;
import dev.ikx.rt.impl.internal.config.RTConfig;
import dev.ikx.rt.impl.mods.astralsorcery.CustomAttunementRecipe;
import dev.ikx.rt.impl.mods.astralsorcery.event.AttunementRecipeCompleteEvent;
import dev.ikx.rt.impl.mods.astralsorcery.event.AttunementStartEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@SuppressWarnings("unused")
public class AttunementAltarMethodReflections {

    public static void onAttunementStart(Entity entity, IConstellation constellation) {
        if (entity == null) {
            return;
        }
        AttunementStartEvent event = new AttunementStartEvent(entity, entity.getEntityWorld(), constellation);
        event.post();
    }

    public static void onCraftingFinish(ItemStack itemStack, EntityItem original, IConstellation constellation) {
        World world = original.getEntityWorld();
        for (CustomAttunementRecipe recipe : CustomAttunementRecipe.allRecipes) {
            if (recipe.canDoRecipe(constellation, original.getItem())) {
                itemStack.setCount(recipe.getResult().getCount());
            }
        }
        AttunementRecipeCompleteEvent event = new AttunementRecipeCompleteEvent(itemStack, original, world, constellation);
        ItemStack originalStack = original.getItem().copy();
        originalStack.setTagCompound(null);
        boolean eventExec = event.post();

        if (!eventExec) {
            ItemStack output = event.getOutput().copy();
            if(!output.equals(itemStack)){
                EntityItem item = new EntityItem(world,
                        original.posX,
                        original.posY,
                        original.posZ,
                        output);
                resetMotion(item);
                world.spawnEntity(item);
                itemStack.setCount(0);
            }
            for (ItemStack stack : event.getAdditionalOutput()) {
                EntityItem additionalStack = new EntityItem(world,
                    original.posX,
                    original.posY,
                    original.posZ,
                    stack);
                resetMotion(additionalStack);
                additionalStack.setDefaultPickupDelay();
                world.spawnEntity(additionalStack);
            }
        } else {
            EntityItem originalInput = new EntityItem(world,
                original.posX,
                original.posY,
                original.posZ,
                originalStack);

            originalInput.motionX = Math.random() * 0.3;
            originalInput.motionY = Math.random() * 0.2;
            originalInput.motionZ = Math.random() * 0.3;
            originalInput.setDefaultPickupDelay();
            world.spawnEntity(originalInput);
            itemStack.setCount(0);
        }
    }

    private static void resetMotion(EntityItem item) {
        item.motionX = 0.0D;
        item.motionY = 0.0D;
        item.motionZ = 0.0D;
    }

    public static boolean haveRecipe(IConstellation constellation, ItemStack itemStack) {
        for (CustomAttunementRecipe recipe : CustomAttunementRecipe.allRecipes) {
            if (recipe.canDoRecipe(constellation, itemStack)) {
                return true;
            }
        }
        return false;
    }

    public static boolean haveRecipe(IConstellation constellation, Entity entity) {
        if (entity instanceof EntityItem) {
            return haveRecipe(constellation, ((EntityItem) entity).getItem());
        }
        return false;
    }

    public static boolean haveRecipe(IConstellation constellation, EntityItem itemStack) {
        return haveRecipe(constellation, itemStack.getItem());
    }

    public static boolean haveAnyRecipe(Entity entity) {
        if (RTConfig.Astralsorcery.attunementModification && entity instanceof EntityItem) {
            IItemStack itemStack = CraftTweakerMC.getIItemStack(((EntityItem) entity).getItem());
            for (CustomAttunementRecipe recipe : CustomAttunementRecipe.allRecipes) {
                if (recipe.getOriginal().matches(itemStack)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void applyCrystalProperties(ItemStack itemStack, CrystalProperties props) {
        if (itemStack.getItem() instanceof CrystalPropertyItem && props != null) {
            CrystalProperties.applyCrystalProperties(itemStack, props);
        }
    }

    public static boolean logicPatch(boolean A, boolean B, boolean C) {
        return ((A && B) || C);
    }

    public static Item getTunedItemVariant(Item item, EntityItem itemStack, IConstellation constellation) {
        if (item instanceof ItemRockCrystalBase) {
            return ((ItemRockCrystalBase) item).getTunedItemVariant();
        } else {
            //first check all recipe with constellation
            for (CustomAttunementRecipe recipe : CustomAttunementRecipe.allRecipes) {
                if (recipe.getConstellation() != null && recipe.canDoRecipe(constellation, itemStack.getItem())) {
                    return recipe.getResult().getItem();
                }
            }
            //then if there isn't any recipe with constellation, check recipes without constellation
            for (CustomAttunementRecipe recipe : CustomAttunementRecipe.allRecipes) {
                if (recipe.getConstellation() == null && recipe.canDoRecipe(constellation, itemStack.getItem())) {
                    return recipe.getResult().getItem();
                }
            }
        }
        return item;
    }

    public static void applyMainConstellation(ItemStack itemStack, IConstellation constellation) {
        if (itemStack.getItem() instanceof ItemTunedCrystalBase && constellation instanceof IWeakConstellation) {
            ItemTunedCrystalBase.applyMainConstellation(itemStack, (IWeakConstellation) constellation);
        }
    }

}
