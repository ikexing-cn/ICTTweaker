package dev.ikx.rt.impl.mods.contenttweaker.mana.bauble;

import baubles.api.IBauble;
import crafttweaker.api.entity.IEntityLivingBase;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import dev.ikx.rt.api.mods.contenttweaker.mana.IManaBauble;
import dev.ikx.rt.api.mods.contenttweaker.mana.bauble.CTManaBaubleContent;
import dev.ikx.rt.impl.mods.contenttweaker.mana.item.MCManaItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

/**
 * @author superhelo
 */
public class MCManaBauble extends MCManaItem implements IManaBauble {

    protected MCManaBauble(ItemStack stack) {
        super(stack);
    }

    @Nullable
    public static IManaBauble create(ItemStack stack) {
        Item item = stack.getItem();

        if (item instanceof vazkii.botania.api.mana.IManaItem && item instanceof IBauble) {
            return new MCManaBauble(stack);
        }

        return null;
    }

    @Override
    public String getBaubleType() {
        return ((CTManaBaubleContent) this.itemIn).getBaubleType(stack).toString();
    }

    @Override
    public boolean canEquip(IItemStack baubleItem, IEntityLivingBase wearer) {
        return ((IBauble) itemIn).canEquip(CraftTweakerMC.getItemStack(baubleItem), CraftTweakerMC.getEntityLivingBase(wearer));
    }

    @Override
    public boolean canUnEquip(IItemStack baubleItem, IEntityLivingBase wearer) {
        return ((IBauble) itemIn).canUnequip(CraftTweakerMC.getItemStack(baubleItem), CraftTweakerMC.getEntityLivingBase(wearer));
    }

}
