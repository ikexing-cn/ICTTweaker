package dev.ikx.rt.api.mods.contenttweaker.mana.item;

import crafttweaker.api.minecraft.CraftTweakerMC;
import dev.ikx.rt.api.mods.contenttweaker.mana.item.tool.CTUsageManaItemRepresentation;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.mana.IManaUsingItem;
import youyihj.zenutils.api.cotx.annotation.ExpandContentTweakerEntry;

import java.util.Objects;

@ExpandContentTweakerEntry
public class CTUsageManaItemContent extends CTManaItemContent implements IManaUsingItem {

    private final CTUsageManaItemRepresentation manaUsingItem;

    public CTUsageManaItemContent(CTUsageManaItemRepresentation manaUsingItem) {
        super(manaUsingItem);
        this.manaUsingItem = manaUsingItem;
    }

    @Override
    public boolean usesMana(ItemStack stack) {
        return Objects.isNull(manaUsingItem.usesMana) || manaUsingItem.usesMana.call(CraftTweakerMC.getIItemStack(stack));
    }

}
