package dev.ikx.rt.api.mods.thaumcraft.expand;

import youyihj.zenutils.api.zenscript.SidedZenRegister;
import crafttweaker.api.entity.IEntity;
import crafttweaker.api.minecraft.CraftTweakerMC;
import dev.ikx.rt.api.mods.thaumcraft.IAspectList;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethod;
import thaumcraft.api.aspects.AspectHelper;
import thaumcraft.api.aspects.AspectList;


@SidedZenRegister(modDeps = "thaumcraft")
@ZenExpansion("crafttweaker.entity.IEntity")
@ZenClass("mods.randomtweaker.thaumcraft.IEntity")
public abstract class ExpandIEntity {

    @ZenMethod
    public static IAspectList getAspects(IEntity entity) {
        AspectList entityAspects = AspectHelper.getEntityAspects(CraftTweakerMC.getEntity(entity));
        if (entityAspects != null) {
            return IAspectList.of(entityAspects.copy());
        }
        return IAspectList.of();
    }

}
