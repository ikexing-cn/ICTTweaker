package dev.ikx.rt.api.mods.contenttweaker.mana.item;

import com.teamacronymcoders.base.registrysystem.ItemRegistry;
import com.teamacronymcoders.contenttweaker.ContentTweaker;
import com.teamacronymcoders.contenttweaker.modules.vanilla.items.ItemRepresentation;
import dev.ikx.rt.api.mods.contenttweaker.function.mana.IGetEntityLifeSpan;
import dev.ikx.rt.api.mods.contenttweaker.function.mana.IManaWithItem;
import dev.ikx.rt.api.mods.contenttweaker.function.mana.IManaWithPool;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenProperty;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

/**
 * @author superhelo
 */
@SidedZenRegister(modDeps = {"contenttweaker", "botania"})
@ZenClass("mods.randomtweaker.cote.ManaItem")
public class CTManaItemRepresentation extends ItemRepresentation {

    @ZenProperty
    public boolean hasFull;
    @ZenProperty
    public boolean isNoExport;
    @ZenProperty
    public boolean hasCreative;
    @ZenProperty
    public int maxMana = 500000;
    @ZenProperty
    public int maxItemUseDuration;
    @ZenProperty
    public IManaWithPool canExportManaToPool;
    @ZenProperty
    public IManaWithItem canExportManaToItem;
    @ZenProperty
    public IManaWithPool canReceiveManaFromPool;
    @ZenProperty
    public IManaWithItem canReceiveManaFromItem;
    @ZenProperty
    public IGetEntityLifeSpan entityLifeSpan;

    public CTManaItemRepresentation(String unlocalizedName, int maxMana) {
        this.setUnlocalizedName(unlocalizedName);
        this.setMaxMana(maxMana);
        this.setMaxStackSize(1);
    }

    @ZenMethod
    public IManaWithPool getCanExportManaToPool() {
        return canExportManaToPool;
    }

    @ZenMethod
    public void setCanExportManaToPool(IManaWithPool canExportManaToPool) {
        this.canExportManaToPool = canExportManaToPool;
    }

    @ZenMethod
    public IManaWithItem getCanExportManaToItem() {
        return canExportManaToItem;
    }

    @ZenMethod
    public void setCanExportManaToItem(IManaWithItem canExportManaToItem) {
        this.canExportManaToItem = canExportManaToItem;
    }

    @ZenMethod
    public IManaWithPool getCanReceiveManaFromPool() {
        return canReceiveManaFromPool;
    }

    @ZenMethod
    public void setCanReceiveManaFromPool(IManaWithPool canReceiveManaFromPool) {
        this.canReceiveManaFromPool = canReceiveManaFromPool;
    }

    @ZenMethod
    public IManaWithItem getCanReceiveManaFromItem() {
        return canReceiveManaFromItem;
    }

    @ZenMethod
    public void setCanReceiveManaFromItem(IManaWithItem canReceiveManaFromItem) {
        this.canReceiveManaFromItem = canReceiveManaFromItem;
    }

    @ZenMethod
    public int getMaxItemUseDuration() {
        return maxItemUseDuration;
    }

    @ZenMethod
    public void setMaxItemUseDuration(int maxItemUseDuration) {
        this.maxItemUseDuration = maxItemUseDuration;
    }

    @ZenMethod
    public boolean hasCreative() {
        return hasCreative;
    }

    @ZenMethod
    public void setHasCreative(boolean hasCreative) {
        this.hasCreative = hasCreative;
    }

    @ZenMethod
    public boolean hasFull() {
        return hasFull;
    }

    @ZenMethod
    public void setHasFull(boolean hasFull) {
        this.hasFull = hasFull;
    }

    @ZenMethod
    public int getMaxMana() {
        return maxMana;
    }

    @ZenMethod
    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    @ZenMethod
    public boolean isNoExport() {
        return isNoExport;
    }

    @ZenMethod
    public void setNoExport(boolean isNoExport) {
        this.isNoExport = isNoExport;
    }

    @Override
    public String getTypeName() {
        return "ManaItem";
    }

    @Override
    public void register() {
        ContentTweaker.instance.getRegistry(ItemRegistry.class, "ITEM").register(new CTManaItemContent(this));
    }

}
