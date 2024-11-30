package dev.ikx.rt.api.mods.contenttweaker.mana.item.tool;

import com.teamacronymcoders.base.registrysystem.ItemRegistry;
import com.teamacronymcoders.contenttweaker.ContentTweaker;
import dev.ikx.rt.api.mods.contenttweaker.function.mana.IisUsesMana;
import dev.ikx.rt.api.mods.contenttweaker.mana.item.CTManaItemRepresentation;
import dev.ikx.rt.api.mods.contenttweaker.mana.item.CTUsageManaItemContent;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenProperty;
import youyihj.zenutils.api.zenscript.SidedZenRegister;

/**
 * @author superhelo
 */
@SidedZenRegister(modDeps = {"contenttweaker", "botania"})
@ZenClass("mods.randomtweaker.cote.ManaUsingItem")
public class CTUsageManaItemRepresentation extends CTManaItemRepresentation {

    @ZenProperty
    public IisUsesMana usesMana;

    public CTUsageManaItemRepresentation(String unlocalizedName, int maxMana) {
        super(unlocalizedName, maxMana);
        this.setMaxStackSize(1);
    }

    @ZenMethod
    public IisUsesMana getUsesMana() {
        return usesMana;
    }

    @ZenMethod
    public void setUsesMana(IisUsesMana usesMana) {
        this.usesMana = usesMana;
    }

    @Override
    public String getTypeName() {
        return "ManaUsingItem";
    }

    @Override
    public void register() {
        ContentTweaker.instance.getRegistry(ItemRegistry.class, "ITEM").register(new CTUsageManaItemContent(this));
    }

}