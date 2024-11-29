package dev.ikx.rt.api.mods.contenttweaker.subtile;

import crafttweaker.CraftTweakerAPI;
import dev.ikx.rt.Main;
import dev.ikx.rt.api.mods.contenttweaker.function.subtile.*;
import dev.ikx.rt.api.mods.contenttweaker.function.subtile.*;
import org.apache.commons.lang3.tuple.Pair;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenProperty;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipeMiniFlower;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

@SidedZenRegister(modDeps = {"contenttweaker", "botania"})
@ZenClass("mods.randomtweaker.cote.ISubTileEntity")
public abstract class ISubTileEntityRepresentation {

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

    protected ISubTileEntityRepresentation(int color, String unlocalizedName) {
        this.color = color;
        this.unlocalizedName = unlocalizedName;
    }

    @ZenMethod
    public void register(String typeName, boolean hasMini) {
        if (Main.SUB_TILE_GENERATING_MAP.containsKey(unlocalizedName)) {
            CraftTweakerAPI.logError("All SubTileEntity must be unique. Key: contenttweaker:" + unlocalizedName + " is not.",
                    new UnsupportedOperationException());
        } else {
            if (typeName.equals("functional")) {
                Main.SUB_TILE_GENERATING_MAP.put(unlocalizedName, Pair.of(typeName, this));
                if (hasMini)
                    registerMini(this);
            } else {
                Main.SUB_TILE_GENERATING_MAP.put(unlocalizedName, Pair.of(typeName, this));
            }
            BotaniaAPI.subtilesForCreativeMenu.add(unlocalizedName);
        }
    }

    protected void registerMini(ISubTileEntityRepresentation subtile) {
        Main.SUB_TILE_GENERATING_MAP.put(unlocalizedName + "Chibi", Pair.of("functional", this));
        BotaniaAPI.subtilesForCreativeMenu.add(unlocalizedName + "Chibi");
        BotaniaAPI.miniFlowers.put(unlocalizedName, unlocalizedName + "Chibi");

        RecipeMiniFlower recipe = new RecipeMiniFlower(unlocalizedName + "Chibi", unlocalizedName, 2500);
        BotaniaAPI.manaInfusionRecipes.add(recipe);
        BotaniaAPI.miniFlowerRecipes.add(recipe);
    }

}
