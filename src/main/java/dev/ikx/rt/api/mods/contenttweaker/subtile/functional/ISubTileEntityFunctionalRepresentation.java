package dev.ikx.rt.api.mods.contenttweaker.subtile.functional;

import dev.ikx.rt.api.mods.contenttweaker.subtile.ISubTileEntityRepresentation;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenProperty;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

@SidedZenRegister(modDeps = {"contenttweaker", "botania"})
@ZenClass("mods.randomtweaker.cote.ISubTileEntityFunctional")
public abstract class ISubTileEntityFunctionalRepresentation extends ISubTileEntityRepresentation {

    private static final String TYPE_NAME = "functional";

    @ZenProperty
    public boolean hasMini;
    @ZenProperty
    public int miniRange = 1;

    protected ISubTileEntityFunctionalRepresentation(int color, String unlocalizedName) {
        super(color, unlocalizedName);
    }

    @ZenMethod
    public void register() {
        this.register(TYPE_NAME, hasMini);
    }

}
