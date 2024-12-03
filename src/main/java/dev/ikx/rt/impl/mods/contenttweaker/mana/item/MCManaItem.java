package dev.ikx.rt.impl.mods.contenttweaker.mana.item;

import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.world.IBlockPos;
import crafttweaker.api.world.IWorld;
import dev.ikx.rt.api.mods.contenttweaker.mana.IManaItem;
import dev.ikx.rt.impl.mods.contenttweaker.item.CTManaItemContent;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.mana.ICreativeManaProvider;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.common.core.helper.ItemNBTHelper;

import javax.annotation.Nullable;

/**
 * @author superhelo
 */
public class MCManaItem implements IManaItem {

    protected final ItemStack stack;
    protected final vazkii.botania.api.mana.IManaItem itemIn;

    protected MCManaItem(ItemStack stack) {
        this.stack = stack.copy();
        this.itemIn = (vazkii.botania.api.mana.IManaItem) stack.getItem();
    }

    @Nullable
    public static IManaItem create(ItemStack stack) {
        return stack.getItem() instanceof vazkii.botania.api.mana.IManaItem ? new MCManaItem(stack) : null;
    }

    @Override
    public boolean hasFull() {
        return itemIn instanceof CTManaItemContent && ((CTManaItemContent) itemIn).getRepresentation().hasFull();
    }

    @Override
    public boolean hasCreative() {
        return itemIn instanceof CTManaItemContent && ((CTManaItemContent) itemIn).getRepresentation().hasCreative();
    }

    @Override
    public boolean getUseMana() {
        return itemIn instanceof IManaUsingItem && ((IManaUsingItem) itemIn).usesMana(stack);
    }

    @Override
    public boolean isCreative() {
        return itemIn instanceof ICreativeManaProvider && ((ICreativeManaProvider) itemIn).isCreative(stack);
    }

    @Override
    public boolean isFull() {
        return this.getMana() == this.getMaxMana();
    }

    @Override
    public int getMana() {
        return itemIn.getMana(stack);
    }

    @Override
    public int getMaxMana() {
        return itemIn.getMaxMana(stack);
    }

    @Override
    public boolean isNoExport() {
        return itemIn.isNoExport(stack);
    }

    @Override
    public boolean canExportManaToPool(IWorld world, IBlockPos pos) {
        return itemIn.canExportManaToPool(stack, CraftTweakerMC.getWorld(world).getTileEntity(CraftTweakerMC.getBlockPos(pos)));
    }

    @Override
    public boolean canExportManaToItem(IItemStack otherStack) {
        return itemIn.canExportManaToItem(stack, CraftTweakerMC.getItemStack(otherStack));
    }

    @Override
    public boolean canReceiveManaFromPool(IWorld world, IBlockPos pos) {
        return itemIn.canReceiveManaFromPool(stack, CraftTweakerMC.getWorld(world).getTileEntity(CraftTweakerMC.getBlockPos(pos)));
    }

    @Override
    public boolean canReceiveManaFromItem(IItemStack otherStack) {
        return itemIn.canReceiveManaFromItem(stack, CraftTweakerMC.getItemStack(otherStack));
    }

    @Override
    public int updateMana(int mana) {
        int originalMana = this.getMana();
        itemIn.addMana(stack, mana);

        if (itemIn.getMana(stack) < 0) {
            ItemNBTHelper.setInt(stack, "mana", 0);
            return -originalMana;
        }

        return (this.getMana() - originalMana);
    }

}
