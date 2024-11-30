package dev.ikx.rt.api.mods.contenttweaker.subtile.functional;

import dev.ikx.rt.api.mods.contenttweaker.subtile.ISubTileEntityRepresentation;
import dev.ikx.rt.impl.mods.botania.module.BotaniaManager;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenProperty;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

@SidedZenRegister(modDeps = {"contenttweaker", "botania"})
@ZenClass("mods.randomtweaker.cote.ISubTileEntityFunctional")
public abstract class ISubTileEntityFunctionalRepresentation extends ISubTileEntityRepresentation {

    private static final BotaniaManager.SubtileEntityType TYPE_NAME = BotaniaManager.SubtileEntityType.FUNCTIONAL;

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
