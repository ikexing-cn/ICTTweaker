package dev.ikx.rt.api.mods.contenttweaker.subtile.generating;

import dev.ikx.rt.api.mods.contenttweaker.function.subtile.CanGeneratePassively;
import dev.ikx.rt.api.mods.contenttweaker.function.subtile.PopulateDropStackNBTs;
import dev.ikx.rt.api.mods.contenttweaker.subtile.ISubTileEntityRepresentation;
import dev.ikx.rt.impl.mods.botania.module.BotaniaManager;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenProperty;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

@SidedZenRegister(modDeps = {"contenttweaker", "botania"})
@ZenClass("mods.randomtweaker.cote.ISubTileEntityGenerating")
public abstract class ISubTileEntityGeneratingRepresentation extends ISubTileEntityRepresentation {

    private static final BotaniaManager.SubtileEntityType TYPE_NAME = BotaniaManager.SubtileEntityType.GENERATING;

    @ZenProperty
    public boolean passiveFlower;
    @ZenProperty
    public int valueForPassiveGeneration = 1;
    @ZenProperty
    public int delayBetweenPassiveGeneration = 20;
    @ZenProperty
    public boolean shouldSyncPassiveGeneration;
    @ZenProperty
    public PopulateDropStackNBTs populateDropStackNBTs;
    @ZenProperty
    public CanGeneratePassively canGeneratePassively;

    protected ISubTileEntityGeneratingRepresentation(int color, String unlocalizedName) {
        super(color, unlocalizedName);
    }

    @ZenMethod
    public void register() {
        this.register(TYPE_NAME, false);
    }

}
