package dev.ikx.rt.impl.mods.contenttweaker.mana.item.tool;

import crafttweaker.api.minecraft.CraftTweakerMC;
import dev.ikx.rt.api.mods.contenttweaker.mana.item.tool.IIsUsesManaItemRepresentation;
import dev.ikx.rt.impl.mods.contenttweaker.mana.item.MCManaItemContent;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.mana.IManaUsingItem;
import youyihj.zenutils.api.cotx.annotation.ExpandContentTweakerEntry;

import java.util.Objects;

@ExpandContentTweakerEntry
public class MCIsUsesManaItemContent extends MCManaItemContent implements IManaUsingItem {

    private final IIsUsesManaItemRepresentation manaUsingItem;

    public MCIsUsesManaItemContent(IIsUsesManaItemRepresentation manaUsingItem) {
        super(manaUsingItem);
        this.manaUsingItem = manaUsingItem;
    }

    @Override
    public boolean usesMana(ItemStack stack) {
        return Objects.isNull(manaUsingItem.usesMana) || manaUsingItem.usesMana.call(CraftTweakerMC.getIItemStack(stack));
    }

}
