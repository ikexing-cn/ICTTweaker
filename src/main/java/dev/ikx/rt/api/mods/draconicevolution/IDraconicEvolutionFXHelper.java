package dev.ikx.rt.api.mods.draconicevolution;

import crafttweaker.api.world.IVector3d;
import dev.ikx.rt.impl.internal.compact.DeprecatedCompact;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

/**
 * @author youyihj
 */

@Deprecated
@SidedZenRegister(modDeps = "draconicevolution")
@ZenClass(IDraconicEvolutionFXHelper.ZEN_CLASS)
public class IDraconicEvolutionFXHelper {

    public static final String ZEN_CLASS = "mods.randomtweaker.draconicevolution.IDraconicEvolutionFXHelper";
    public static DeprecatedCompact compact = new DeprecatedCompact(ZEN_CLASS, DraconicEvolutionFXHelper.ZEN_CLASS);

    @ZenMethod
    public static void spawnFX(int particleID, IVector3d pos, IVector3d speed, double viewRange, int... args) {
        compact.callVoid(() -> DraconicEvolutionFXHelper.spawnFX(particleID, pos, speed, viewRange, args));
    }

    @ZenMethod
    public static void spawnFX(int particleID, IVector3d pos, IVector3d speed, int... args) {
        compact.callVoid(() -> DraconicEvolutionFXHelper.spawnFX(particleID, pos, speed, args));
    }

}
