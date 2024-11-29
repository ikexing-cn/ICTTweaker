package dev.ikx.rt.impl.mods.contenttweaker.potion;

import com.teamacronymcoders.contenttweaker.ContentTweaker;
import crafttweaker.CraftTweakerAPI;
import dev.ikx.rt.api.mods.contenttweaker.potion.IPotionRepresentation;
import dev.ikx.rt.api.mods.contenttweaker.potion.IPotionTypeRepresentation;
import dev.ikx.rt.impl.internal.event.EventRegister;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;

import java.text.MessageFormat;

public class MCPotionTypeRepresentation extends IPotionTypeRepresentation {

    public MCPotionTypeRepresentation(String unlocalizedName, IPotionRepresentation potion) {
        super(unlocalizedName, potion);
    }

    @Override
    public void register() {
        if (potion.instant) duration = 0;

        PotionType potionType = new PotionType(ContentTweaker.MOD_ID + "." + unlocalizedName,
                new PotionEffect(potion.getInternal(), duration, amplifier))
                .setRegistryName(ContentTweaker.MOD_ID, unlocalizedName);

        if (!EventRegister.POTION_TYPE_MAP.containsKey(unlocalizedName)) {
            EventRegister.POTION_TYPE_MAP.put(unlocalizedName, potionType);
        } else {
            CraftTweakerAPI.getLogger().logError(MessageFormat.format(
                            "All PotionTypes must be unique. Key: contenttweaker:{0} is not.", unlocalizedName)
                    , new UnsupportedOperationException());
        }
    }

}
