package dev.ikx.rt.api.mods.contenttweaker.subtile;

import crafttweaker.CraftTweakerAPI;
import dev.ikx.rt.api.mods.contenttweaker.function.subtile.*;
import dev.ikx.rt.impl.mods.botania.module.BotaniaManager;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenProperty;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipeMiniFlower;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

@SidedZenRegister(modDeps = {"contenttweaker", "botania"})
@ZenClass("mods.randomtweaker.cote.ISubTileEntity")
public class CTSubTileEntityRepresentation {

    @ZenProperty
    public int color;
    @ZenProperty
    public String unlocalizedName;
    @ZenProperty
    public int range = 1;
    @ZenProperty
    public int maxMana = 1000;
    @ZenProperty
    public boolean acceptsRedstone;
    @ZenProperty
    public boolean overgrowthAffected = true;
    @ZenProperty
    public Update onUpdate;
    @ZenProperty
    public CanSelect canSelect;
    @ZenProperty
    public BlockAdded onBlockAdded;
    @ZenProperty
    public BlockPlacedBy onBlockPlaceBy;
    @ZenProperty
    public BlockHarvested onBlockHarvested;
    @ZenProperty
    public BlockActivated onBlockActivated;

    protected CTSubTileEntityRepresentation(int color, String unlocalizedName) {
        this.color = color;
        this.unlocalizedName = unlocalizedName;
    }

    @ZenMethod
    public void register(BotaniaManager.SubtileEntityType typeName, boolean hasMini) {
        if (BotaniaManager.INSTANCE.getSubTileEntityMap().containsKey(unlocalizedName)) {
            CraftTweakerAPI.logError("All SubTileEntity must be unique. Key: contenttweaker:" + unlocalizedName + " is not.",
                    new UnsupportedOperationException());
        } else {
            if (typeName == BotaniaManager.SubtileEntityType.FUNCTIONAL && hasMini)
                registerMini(this);
            BotaniaAPI.subtilesForCreativeMenu.add(unlocalizedName);
            BotaniaManager.INSTANCE.registerSubtileEntity(unlocalizedName, typeName, this);
        }
    }

    protected void registerMini(CTSubTileEntityRepresentation subtile) {
        BotaniaManager.INSTANCE.registerSubtileEntity(unlocalizedName + "Chibi", BotaniaManager.SubtileEntityType.FUNCTIONAL, subtile);

        BotaniaAPI.subtilesForCreativeMenu.add(unlocalizedName + "Chibi");
        BotaniaAPI.miniFlowers.put(unlocalizedName, unlocalizedName + "Chibi");

        RecipeMiniFlower recipe = new RecipeMiniFlower(unlocalizedName + "Chibi", unlocalizedName, 2500);
        BotaniaAPI.miniFlowerRecipes.add(recipe);
        BotaniaAPI.manaInfusionRecipes.add(recipe);
    }

}
