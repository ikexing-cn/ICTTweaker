package dev.ikx.rt.impl.mods.contenttweaker.mana.item.tool;

import com.teamacronymcoders.base.registrysystem.ItemRegistry;
import com.teamacronymcoders.contenttweaker.ContentTweaker;
import dev.ikx.rt.api.mods.contenttweaker.mana.item.tool.IIsUsesManaItemRepresentation;

public class MCIsUsesManaItemRepresentation extends IIsUsesManaItemRepresentation {

    public MCIsUsesManaItemRepresentation(String unlocalizedName, int maxMana) {
        this.setUnlocalizedName(unlocalizedName);
        this.setMaxMana(maxMana);
        this.setMaxStackSize(1);
    }

    @Override
    public void register() {
        ContentTweaker.instance.getRegistry(ItemRegistry.class, "ITEM").register(new MCIsUsesManaItemContent(this));
    }
}
