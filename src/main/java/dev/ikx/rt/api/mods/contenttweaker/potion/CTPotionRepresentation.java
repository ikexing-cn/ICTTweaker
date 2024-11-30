package dev.ikx.rt.api.mods.contenttweaker.potion;

import crafttweaker.CraftTweakerAPI;
import dev.ikx.rt.api.mods.contenttweaker.function.IPotionIsReady;
import dev.ikx.rt.api.mods.contenttweaker.function.IPotionPerformEffect;
import dev.ikx.rt.impl.internal.event.EventRegister;
import dev.ikx.rt.impl.mods.contenttweaker.potion.MCPotionContent;
import net.minecraft.potion.Potion;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenProperty;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import java.text.MessageFormat;

@SidedZenRegister(modDeps = "contenttweaker")
@ZenClass(CTPotionRepresentation.ZEN_CLASS)
public class CTPotionRepresentation {

    public static final String ZEN_CLASS = "mods.randomtweaker.cote.IPotion";

    public final int liquidColor;
    public final String unlocalizedName;

    @ZenProperty
    public boolean instant;
    @ZenProperty
    public boolean badEffectIn;
    @ZenProperty
    public boolean beneficial = true;
    @ZenProperty
    public boolean shouldRender = true;
    @ZenProperty
    public boolean shouldRenderHUD = true;
    @ZenProperty
    public IPotionIsReady isReady = null;
    @ZenProperty
    public IPotionPerformEffect performEffect;
    @ZenProperty
    public IPotionPerformEffect affectEntity;

    public CTPotionRepresentation(int liquidColor, String unlocalizedName) {
        this.liquidColor = liquidColor;
        this.unlocalizedName = unlocalizedName;
    }

    @ZenMethod
    public void register() {
        if (!EventRegister.POTION_MAP.containsKey(unlocalizedName)) {
            EventRegister.POTION_MAP.put(unlocalizedName, new MCPotionContent(this));
        } else {
            CraftTweakerAPI.getLogger().logError(MessageFormat.format(
                            "All Potion must be unique. Key: contenttweaker:{0} is not.", unlocalizedName)
                    , new UnsupportedOperationException());
        }
    }

    public Potion getInternal() {
        return EventRegister.POTION_MAP.get(unlocalizedName);
    }

}
