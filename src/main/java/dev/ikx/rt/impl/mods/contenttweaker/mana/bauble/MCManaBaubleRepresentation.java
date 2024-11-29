package dev.ikx.rt.impl.mods.contenttweaker.mana.bauble;

import com.teamacronymcoders.base.registrysystem.ItemRegistry;
import com.teamacronymcoders.contenttweaker.ContentTweaker;
import dev.ikx.rt.api.mods.contenttweaker.mana.bauble.IManaBaubleRepresentation;

public class MCManaBaubleRepresentation extends IManaBaubleRepresentation {

    public MCManaBaubleRepresentation(String unlocalizedName, int maxMana, String baubleType) {
        this.setUnlocalizedName(unlocalizedName);
        this.setMaxMana(maxMana);
        this.setMaxStackSize(1);
        this.setBaubleType(baubleType);
    }

    @Override
    public void register() {
        if (baubleType.equals("TRINKET")) {
            ContentTweaker.instance.getRegistry(ItemRegistry.class, "ITEM").register(new MCManaTrinketContent(this));
        } else {
            ContentTweaker.instance.getRegistry(ItemRegistry.class, "ITEM").register(new MCManaBaubleContent(this));
        }
    }

}
