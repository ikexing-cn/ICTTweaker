package dev.ikx.rt.api.mods.contenttweaker.potion;

import com.teamacronymcoders.contenttweaker.ContentTweaker;
import crafttweaker.CraftTweakerAPI;
import dev.ikx.rt.impl.mods.contenttweaker.potion.PotionRegisterEvent;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenProperty;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

import java.text.MessageFormat;


@SidedZenRegister(modDeps = "contenttweaker")
@ZenClass(CTPotionRepresentation.ZEN_CLASS)
public class CTPotionTypeRepresentation {

    public static final String ZEN_CLASS = "mods.randomtweaker.cote.PotionType";

    public String unlocalizedName;
    public CTPotionRepresentation potion;

    @ZenProperty
    public int duration = 3600;
    @ZenProperty
    public int amplifier = 0;

    public CTPotionTypeRepresentation(String unlocalizedName, CTPotionRepresentation potion) {
        this.unlocalizedName = unlocalizedName;
        this.potion = potion;
    }

    @ZenMethod
    public void register() {
        if (potion.instant)
            duration = 0;

        PotionType potionType = new PotionType(
                ContentTweaker.MOD_ID + "." + unlocalizedName,
                new PotionEffect(potion.getInternal(), duration, amplifier)
        ).setRegistryName(ContentTweaker.MOD_ID, unlocalizedName);

        if (!PotionRegisterEvent.POTION_TYPE_MAP.containsKey(unlocalizedName)) {
            PotionRegisterEvent.POTION_TYPE_MAP.put(unlocalizedName, potionType);
        } else {
            CraftTweakerAPI.getLogger().logError(
                    MessageFormat.format(
                            "All PotionTypes must be unique. Key: contenttweaker:{0} is not.",
                            unlocalizedName
                    ),
                    new UnsupportedOperationException()
            );
        }
    }

}
