package dev.ikx.rt.api.mods.contenttweaker.subtile.functional;

import dev.ikx.rt.api.mods.contenttweaker.subtile.CTSubTileEntityRepresentation;
import dev.ikx.rt.impl.mods.botania.module.BotaniaManager;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenProperty;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

@SidedZenRegister(modDeps = {"contenttweaker", "botania"})
@ZenClass("mods.randomtweaker.cote.ISubTileEntityFunctional")
public class CTSubTileEntityFunctionalRepresentation extends CTSubTileEntityRepresentation {

    private static final BotaniaManager.SubtileEntityType TYPE_NAME = BotaniaManager.SubtileEntityType.FUNCTIONAL;

    @ZenProperty
    public boolean hasMini;
    @ZenProperty
    public int miniRange = 1;

    public CTSubTileEntityFunctionalRepresentation(int color, String unlocalizedName) {
        super(color, unlocalizedName);
    }

    @ZenMethod
    public void register() {
        this.register(TYPE_NAME, hasMini);
    }

}
